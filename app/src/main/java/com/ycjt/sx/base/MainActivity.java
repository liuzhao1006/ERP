package com.ycjt.sx.base;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.R;
import com.ycjt.sx.receiver.MyBroadCastRecever;
import com.ycjt.sx.utils.Utils;
import com.ycjt.sx.widget.NoScrollViewPager;

import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private NoScrollViewPager mViewPager;
    private RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;
    private MyMainAdapter mAdapter;

    private Handler handler;
    private MyBroadCastRecever receiver;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private QBadgeView badgeView;

    @Override
    protected void initView() {
        initReceiver();
        ibTopLeft.setVisibility(View.GONE);
        ibTopRight.setVisibility(View.GONE);
        ivFalse.setVisibility(View.GONE);
        
        tvTopModdel.setText("登录");

        badgeView = new QBadgeView(this);

        mViewPager = (NoScrollViewPager) findViewById(R.id.viewPager);
        radioGroup = (RadioGroup) findViewById(R.id.rg_group);
        rb1 = (RadioButton) findViewById(R.id.rb_1);
        rb2 = (RadioButton) findViewById(R.id.rb_2);
        rb3 = (RadioButton) findViewById(R.id.rb_3);
        rb4 = (RadioButton) findViewById(R.id.rb_4);
        rb5 = (RadioButton) findViewById(R.id.rb_5);


        btn1 = (Button) findViewById(R.id.btn_agency_1);
        btn2 = (Button) findViewById(R.id.btn_message_2);
        btn3 = (Button) findViewById(R.id.btn_workbench_3);
        btn4 = (Button) findViewById(R.id.btn_maillist_4);
        btn5 = (Button) findViewById(R.id.btn_personal_5);
        mAdapter = new MyMainAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        radioGroup.check(R.id.rb_1);
        tvTopModdel.setText("待办");
        radioGroup.setOnCheckedChangeListener(this);
//        mViewPager.setOffscreenPageLimit(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {

                // TODO Auto-generated method stub
                switch (arg0) {
                    case 0:
                        rb1.setChecked(true);
                        radioGroup.check(R.id.rb_1);
                        if (count == 0) {
                            tvTopModdel.setText("待办");
                        } else {
                            tvTopModdel.setText("待办(" + count + ")");
                        }

                        ibTopLeft.setVisibility(View.GONE);
                        ibTopRight.setVisibility(View.GONE);
                        LogUtils.i(arg0 + "---MainActivity");
                        break;
                    case 1:
                        rb2.setChecked(true);
                        radioGroup.check(R.id.rb_2);
                        tvTopModdel.setText("消息");
                        ibTopLeft.setVisibility(View.GONE);
                        ibTopRight.setVisibility(View.GONE);
                        LogUtils.i(arg0 + "---MainActivity");
                        break;
                    case 2:
                        rb3.setChecked(true);
                        radioGroup.check(R.id.rb_3);
                        tvTopModdel.setText("工作台");
                        ibTopLeft.setVisibility(View.GONE);
                        ibTopRight.setVisibility(View.GONE);
                        ibTopRight.setBackgroundResource(R.drawable.setting);
                        LogUtils.i(arg0 + "---MainActivity");
                        break;
                    case 3:
                        rb4.setChecked(true);
                        radioGroup.check(R.id.rb_4);
                        tvTopModdel.setText("通讯录");
                        ibTopLeft.setVisibility(View.GONE);
                        ibTopRight.setVisibility(View.GONE);
                        LogUtils.i(arg0 + "---MainActivity");

                        break;
                    case 4:
                        rb5.setChecked(true);
                        radioGroup.check(R.id.rb_5);
                        tvTopModdel.setText("我的");
                        ibTopLeft.setVisibility(View.GONE);
                        ibTopRight.setVisibility(View.GONE);
                        LogUtils.i(arg0 + "---MainActivity");

                        break;
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
                LogUtils.i(arg0 + "---" + arg1 + "---" + arg2 + "---");
                i = arg0;

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });


    }

    private int i = 0;//标记当前页面是哪个.

    private int count = 0;//标记当前待办未处理数量

    private void initReceiver() {

        // 使用handler将广播接收到的数据显示出来
        handler = new Handler() {
            //重写handler的方法
            @Override
            public void handleMessage(Message msg) {
                //判断what的值
                switch (msg.what) {
                    case 100:
                        int msgs = (int) msg.obj;
                        count = msgs;
                        if (i == 0) {
                            tvTopModdel.setText("待办(" + msgs + ")");
                            badgeView.bindTarget(btn1).setBadgeNumber(msgs);
                        }
                        break;
                }
            }
        };

        // 动态创建意图过滤器
        IntentFilter filter = new IntentFilter();
        // 设置发送方的广播频率
        filter.addAction("send.count");
        // 创建 广播类
        receiver = new MyBroadCastRecever(handler);
        // 注册广播
        this.registerReceiver(receiver, filter);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(receiver);//注销广播
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_1:
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.rb_2:
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.rb_3:
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.rb_4:
                mViewPager.setCurrentItem(3, false);
                break;
            case R.id.rb_5:
                mViewPager.setCurrentItem(4, false);
                break;
            default:
                break;
        }

    }

    class MyMainAdapter extends FragmentPagerAdapter {

        private String[] mTabNames;

        public MyMainAdapter(FragmentManager fm) {
            super(fm);
            mTabNames = new String[]{"待办", "消息", "工作台", "通讯录", "我"};
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabNames[position];
        }

        @Override
        public Fragment getItem(int position) {
            BaseFragment fragment = FragmentFactory.getFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return mTabNames.length;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            home(this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public static void home(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(intent);
    }
}
