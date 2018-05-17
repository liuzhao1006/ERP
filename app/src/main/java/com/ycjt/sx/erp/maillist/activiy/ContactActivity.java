package com.ycjt.sx.erp.maillist.activiy;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ycjt.sx.R;
import com.ycjt.sx.base.BaseActivity;
import com.ycjt.sx.erp.maillist.adapter.ContactRecyclerAdapter;
import com.ycjt.sx.erp.maillist.adapter.NormalRecyclerAdapter;
import com.ycjt.sx.erp.maillist.bean.MaillistUrlbean;
import com.ycjt.sx.widget.customrecycle.XRefreshView;
import com.ycjt.sx.widget.customrecycle.adapter.LogUtils;
import com.ycjt.sx.widget.recycleline.DividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactActivity extends BaseActivity {

    private XRefreshView contactRefresh;
    private RecyclerView rlvContactList;
    private LinearLayoutManager manager;
    private ArrayList<HashMap<String, String>> list;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_contact;
    }

    @Override
    protected void initView() {

        contactRefresh = (XRefreshView) findViewById(R.id.contact_refresh);
        rlvContactList = (RecyclerView) findViewById(R.id.rlv_contact_list);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            //申请权限  第二个参数是一个 数组 说明可以同时申请多个权限
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS}, 1);
        } else {//已授权
            obtionContacts();
        }

        //titleBar
        ibTopLeft.setVisibility(View.VISIBLE);
        ibTopRight.setVisibility(View.GONE);
        ibTopLeft.setOnClickListener(view -> {
            finish();
        });


        rlvContactList.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        rlvContactList.setLayoutManager(manager);
        contactRefresh.setPinnedTime(1000);
        contactRefresh.setMoveForHorizontal(true);
        contactRefresh.setPullLoadEnable(true);

        rlvContactList.setItemAnimator(new DefaultItemAnimator());
        rlvContactList.addItemDecoration(new DividerItemDecoration(this, 1));
        contactRefresh.setAutoLoadMore(false);
        contactRefresh.enableReleaseToLoadMore(true);
        contactRefresh.enableRecyclerViewPullUp(true);
        contactRefresh.enablePullUpWhenLoadCompleted(true);
        contactRefresh.setPullRefreshEnable(true);
        refresh();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限申请成功
                obtionContacts();
            } else {
                showToast("获取联系人的权限失败", TOAST_SHORT);
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 授权后获取联系人数据
     * <p>
     * 获取手机联系人数据
     * 注意:权限申请成功
     */

    private void obtionContacts() {
        new Thread() {
            @Override
            public void run() {

                if (isRefresh == false)
                    contactRefresh.startRefresh();
                Uri rawUri = Uri.parse("content://com.android.contacts/raw_contacts");
                Uri dataUri = Uri.parse("content://com.android.contacts/data");
                ContentResolver resolver = ContactActivity.this.getContentResolver();
                Cursor cursor = resolver.query(rawUri, new String[]{"contact_id"}, null, null, null);
                if (cursor != null) {

                    list = new ArrayList<HashMap<String, String>>();
                    while (cursor.moveToNext()) {//遍历所有联系人
                        String contactId = cursor.getString(0);
                        //查询data表
                        //视图, 是虚表,是对特定几张表的关联 view_data = data + mimetype
                        Cursor dataCursor = getContentResolver().query(dataUri,
                                new String[]{"data1", "mimetype"},
                                "raw_contact_id=?", new String[]{contactId}, null);

                        if (dataCursor != null) {
                            //遍历data, 获取当前contact_id下的所有数据(联系人名称+电话号码)
                            //通过hashmap集合来保存某个联系人的所有数据
                            HashMap<String, String> map = new HashMap<String, String>();
                            while (dataCursor.moveToNext()) {//遍历某个联系人的所有数据
                                String data = dataCursor.getString(0);
                                String type = dataCursor.getString(1);

                                if ("vnd.android.cursor.item/phone_v2".equals(type)) {
                                    //电话类型
                                    map.put("phone", data);
                                } else if ("vnd.android.cursor.item/name".equals(type)) {
                                    //联系人名称
                                    map.put("name", data);
                                }

                            }

                            dataCursor.close();

                            //将当前联系人的所有数据添加到集合中
                            list.add(map);
                        }
                    }
                    cursor.close();
                    isRefresh = true;
                }

                if (list != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (isRefresh == true) {
                                contactRefresh.stopRefresh();
                            }

                            ContactRecyclerAdapter adapter = new ContactRecyclerAdapter(list, ContactActivity.this);
                            rlvContactList.setAdapter(adapter);
                        }
                    });
                } else {
                    contactRefresh.stopRefresh();
                    showToast("获取联系人失败", TOAST_SHORT);
                }
            }
        }.start();


    }

    private boolean isRefresh = false;

    /**
     * recycleview 刷新数据
     * <p>
     * contactRefresh.startRefresh();开始刷新
     * contactRefresh.stopRefresh();结束刷新
     */
    private void refresh() {
        contactRefresh.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                com.apkfuns.logutils.LogUtils.i(isPullDown);
                isRefresh = false;
                //刷新请求数据,下拉刷新
                if (ContextCompat.checkSelfPermission(ContactActivity.this, android.Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请权限  第二个参数是一个 数组 说明可以同时申请多个权限
                    ActivityCompat.requestPermissions(ContactActivity.this, new String[]{android.Manifest.permission.READ_CONTACTS}, 1);
                } else {//已授权
                    obtionContacts();
                }
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                //加载更多,上划加载
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        // 刷新完成必须调用此方法停止加载
                        contactRefresh.stopLoadMore(true);
                        //当数据加载失败 不需要隐藏footerview时，可以调用以下方法，传入false，不传默认为true
                        // 同时在Footerview的onStateFinish(boolean hideFooter)，可以在hideFooter为false时，显示数据加载失败的ui
//                            xRefreshView1.stopLoadMore(false);
                    }
                }, 1000);
            }
        });
    }

    /**
     * 获取手机联系人数据
     * 注意:权限申请
     */
    private void getContactList() {

    }


}
