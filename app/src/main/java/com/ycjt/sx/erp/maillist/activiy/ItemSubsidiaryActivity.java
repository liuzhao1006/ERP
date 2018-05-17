package com.ycjt.sx.erp.maillist.activiy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.R;
import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.base.BaseActivity;
import com.ycjt.sx.erp.maillist.bean.json.ItemMaillistOrgDataJson;
import com.ycjt.sx.erp.maillist.bean.json.ItemMaillistOrgDataJson.OUserListBean;
import com.ycjt.sx.erp.maillist.bean.json.MaillistDataJson;
import com.ycjt.sx.erp.maillist.presenter.ItemSubsidaryOrganizationPresenter;
import com.ycjt.sx.utils.PrefUtils;
import com.ycjt.sx.widget.listviewadapter.ExpandListViewAdapter;
import com.ycjt.sx.widget.listviewadapter.Node;
import com.ycjt.sx.widget.listviewadapter.Staff;
import com.ycjt.sx.widget.listviewadapter.StaffListViewAdapter;
import com.ycjt.sx.widget.listviewadapter.StaffListViewAdapter.OnCall;

import java.util.ArrayList;
import java.util.List;

import static com.ycjt.sx.app.InterfaceConfig.APP_IP;
import static com.ycjt.sx.app.InterfaceConfig.APP_POST;
import static com.ycjt.sx.app.InterfaceConfig.HTTP;
import static com.ycjt.sx.app.InterfaceConfig.MAILLIST_ORG;

public class ItemSubsidiaryActivity extends BaseActivity implements IItemSubsidiary, OnCall {

    /**
     * 负责人列表
     */
    private RecyclerView rlvItemSubsidiaryLeader;

    /**
     * 下级部门列表
     */
    private ListView elvList;
    private ExpandListViewAdapter mAdapter;

    @Override
    protected void initView() {
        ibTopLeft.setVisibility(View.VISIBLE);
        ivFalse.setVisibility(View.VISIBLE);
        ibTopRight.setVisibility(View.GONE);
        ibTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //获取到跳转过来的内容
        Bundle bundle = (Bundle) getIntent().getExtras().get("bundle");
        MaillistDataJson userBean = (MaillistDataJson) bundle.getSerializable("List");
        LogUtils.i(userBean.toString());
        String id = userBean.getID();

        //组织机构人员信息
        String MAILLIST_COMMON = HTTP + APP_IP + ":" + APP_POST + MAILLIST_ORG + PrefUtils.getString(this, GlobalConstants.PERSONAL_USERID, null) + "/" + PrefUtils.getString(this, GlobalConstants.PERSONAL_DEVICEID, null) + "/" + id + "/" + 1;
        ItemSubsidaryOrganizationPresenter presenter = new ItemSubsidaryOrganizationPresenter(this);
        presenter.getItemData(MAILLIST_COMMON);
        tvTopModdel.setText(userBean.getName());
        //组织架构列表
        rlvItemSubsidiaryLeader = (RecyclerView) findViewById(R.id.rlv_item_subsidiary_leader);
        rlvItemSubsidiaryLeader.setLayoutManager(new LinearLayoutManager(this));
        //下级部门列表
        elvList = (ListView) findViewById(R.id.elv_list);
        initDatas();
    }

    /**
     * 模拟数据
     */
    private void initDatas() {


    }

    /**
     * 下级部门 适配器获取
     */


    private void setData(List<ItemMaillistOrgDataJson> dataList) {
        ArrayList<Staff> staffList = new ArrayList<Staff>();
        int x = 0;
        for (int i = 0; i < dataList.size(); i++) {
            //int employeeId, int superiorId, String img, String tel, String positio
            ItemMaillistOrgDataJson itemMaillistOrgDataJson = dataList.get(i);
            staffList.add(new Staff(x += 1, itemMaillistOrgDataJson.getOrganizeName(), 0));
            if (itemMaillistOrgDataJson.getOUserList() != null && itemMaillistOrgDataJson.getOUserList().size() > 0) {
                for (int j = 0; j < itemMaillistOrgDataJson.getOUserList().size(); j++) {
                    List<OUserListBean> oUserList = itemMaillistOrgDataJson.getOUserList();
                    OUserListBean oUserListBean = oUserList.get(j);
                    //下级部门的孩子,内容封装到字符串中 然后解析
                    String s = oUserListBean.getUserName() + "::" + oUserListBean.getPhone() + "::" + oUserListBean.getDeptPosition() + "::" + oUserListBean.getDeptUserId();
                    staffList.add(new Staff(x += 1, s, i + 1));
                }
            }


        }

        try {
            mAdapter = new StaffListViewAdapter(elvList, this, staffList, 0);
            mAdapter.setOnExpandNodeClickListener((node, visibleNodes, view, position) -> {
                if (node.isLeaft()) {
                    for (Node n : visibleNodes) {
                        n.setSelected(false);
                    }
                    node.setSelected(true);
                }
                mAdapter.notifyDataSetChanged();
            });
            elvList.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            Toast.makeText(this, "实例化适配器失败", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_item_subsidiary;
    }


    /**
     * 设置列表,组织架构
     */
    private void setCommonContract(RecyclerView rlv, List<MaillistDataJson> dataList) {


//        MaillistCommonContractAdapter adapter = new MaillistCommonContractAdapter(this, commonList);
//        rlvItemSubsidiaryLeader.setAdapter(adapter);
//        rlvItemSubsidiaryLeader.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        adapter.setOnItemClickListener(new MaillistCommonContractAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(ItemSubsidiaryActivity.this, PersonalSettingActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("List", commonList.get(position));
//                intent.putExtra("bundle", bundle);
//                startActivity(intent);
//            }
//        });
    }


    @Override
    public void successed(List<ItemMaillistOrgDataJson> dataList) {
        setData(dataList);
    }

    @Override
    public void call(String s) {
        //跳到打电话页面
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);//跳到拨打电话界面
        intent.setData(Uri.parse("tel:" + s));
        startActivity(intent);
    }


}
