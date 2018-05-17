package com.ycjt.sx.erp.agency.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.ycjt.sx.R;
import com.ycjt.sx.base.BaseFragment;
import com.ycjt.sx.base.FragmentFactory;
import com.ycjt.sx.erp.agency.activity.DetailsActivity;
import com.ycjt.sx.erp.agency.adapter.AgencySearchAdapter;
import com.ycjt.sx.erp.agency.bean.AgencyUrlBean;
import com.ycjt.sx.erp.agency.bean.json.AgencyJson.AgencyDataJson;
import com.ycjt.sx.erp.agency.bean.json.HomePageParseJson;
import com.ycjt.sx.erp.agency.presenter.AgencySearchMvpPresenter;
import com.ycjt.sx.receiver.MyBroadCastRecever;
import com.ycjt.sx.utils.Utils;
import com.ycjt.sx.widget.IconCenterEditText;
import com.ycjt.sx.widget.ListViewDecoration;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import static com.ycjt.sx.utils.Utils.toURLEncoded;

/**
 * Created by liuchao on 2017/6/19.
 */

public class AgencyFragment extends BaseFragment implements OnClickListener, IAgencySearch, OnTabSelectListener {
    private IconCenterEditText et;
    private Button btnSearch;
    private FrameLayout flAgencySearchContent;
    private FrameLayout flAgencyContent;
    private LinearLayout llAgencySearch;
    private SwipeMenuRecyclerView rlvAgencySearch;
    private SlidingTabLayout pagerTab;
    private ViewPager viewPager;
    private AgencySearchAdapter adapter;
    private ArrayList<AgencyDataJson> mLists;
    private SwipeRefreshLayout srfl;


    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {
        initReceiver();//广播接收待办数量
        mLists = new ArrayList<>();
        String[] stringArray = Utils.getStringArray(mActivity, R.array.tab_names);
        /*========搜索框========*/
        llAgencySearch = (LinearLayout) mRootView.findViewById(R.id.ll_agency_search);
        et = (IconCenterEditText) mRootView.findViewById(R.id.et_fragment_agency_search);
        et.setImeOptions(EditorInfo.IME_ACTION_SEND);
        MyEditTextOnListener listener = new MyEditTextOnListener();
        et.setOnEditorActionListener(listener);
        et.setOnSearchClickListener(new IconCenterEditText.OnSearchClickListener() {
            @Override
            public void onSearchClick(View view) {

            }
        });


        srfl = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_layout);
        srfl.setOnRefreshListener(mOnRefreshListener);
        rlvAgencySearch = (SwipeMenuRecyclerView) mRootView.findViewById(R.id.rlv_agency_search_answer);
        flAgencyContent = (FrameLayout) mRootView.findViewById(R.id.fl_agency_content);
        flAgencySearchContent = (FrameLayout) mRootView.findViewById(R.id.fl_agency_search_content);
        flAgencySearchContent.setVisibility(View.GONE);
//        updataIcon(et);//设置输入框样式.
        btnSearch = (Button) mRootView.findViewById(R.id.btn_agency_search);
        btnSearch.setOnClickListener(this);
        et.setOnFocusChangeListener((view, b) -> {
            if (b) {
                btnSearch.setVisibility(View.VISIBLE);
            } else {
                btnSearch.setVisibility(View.GONE);
            }
        });


        rlvAgencySearch.setLayoutManager(new LinearLayoutManager(mActivity));// 布局管理器。
        rlvAgencySearch.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlvAgencySearch.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlvAgencySearch.addItemDecoration(new ListViewDecoration());// 添加分割线。
        // 添加滚动监听。
        rlvAgencySearch.addOnScrollListener(mOnScrollListener);
        /*==========状态数据==========*/
        pagerTab = (SlidingTabLayout) mRootView.findViewById(R.id.pagerTab);
        WindowManager systemService = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
        pagerTab.setTabWidth(Utils.px2dp(mActivity, systemService.getDefaultDisplay().getWidth() / stringArray.length));
        pagerTab.setIndicatorWidth(Utils.px2dp(mActivity, systemService.getDefaultDisplay().getWidth() / stringArray.length));
        viewPager = (ViewPager) mRootView.findViewById(R.id.viewPager);
        // 给ViewPager填充数据
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        pagerTab.setViewPager(viewPager);// 将pagerTab和ViewPager进行绑定
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_agency_search:
                et.clearFocus();
                llAgencySearch.setFocusable(true);
                llAgencySearch.setFocusableInTouchMode(true);
                //取消搜索
                et.setText("");
                //显示flAgencyContent,隐藏flAgencySearchContent
                flAgencySearchContent.setVisibility(View.GONE);
                flAgencyContent.setVisibility(View.VISIBLE);
                btnSearch.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void failed(String s) {
        showToast(s, TOAST_SHORT);

    }

    @Override
    public void successedSearch(HomePageParseJson parseJson) {
        mLists.clear();//每次查询 都要清除集合数据
        ArrayList<AgencyDataJson> agencyDataJsons = parseJson.parseAgency();
        for (int j = 0; j < agencyDataJsons.size(); j++) {
            mLists.add(agencyDataJsons.get(j));
        }
        setCommonContract(rlvAgencySearch, mLists);
        LogUtils.i(agencyDataJsons.toString() + "------------successedSearch");

    }

    @Override
    public void startDialog() {

        srfl.setRefreshing(true);
    }

    @Override
    public void stopDialog() {
        srfl.setRefreshing(false);
    }

    @Override
    public void successedSearchMore(HomePageParseJson parseJson) {

        ArrayList<AgencyDataJson> agencyDataJsons = parseJson.parseAgency();
        for (int j = 0; j < agencyDataJsons.size(); j++) {
            mLists.add(agencyDataJsons.get(j));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

        LogUtils.i(position + "-------");
    }

    /**
     * @author 刘朝
     * @ClassName: MyAdapter
     * @Description: Tab适配器
     * @date 2017年6月15日 下午1:29:33
     */
    class MyAdapter extends FragmentPagerAdapter {

        private String[] mTabNames;

        public MyAdapter(FragmentManager fm) {
            super(fm);
            mTabNames = Utils.getStringArray(mActivity, R.array.tab_names);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabNames[position];
        }

        @Override
        public Fragment getItem(int position) {
            BaseFragment fragment = FragmentFactory.getChildFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return mTabNames.length;
        }

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_agency;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            //参数是固定写法
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String search;
    private AgencySearchMvpPresenter searchMvpPresenter;

    class MyEditTextOnListener implements TextView.OnEditorActionListener {


        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == EditorInfo.IME_ACTION_SEND
                    || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN)) {
                String searchContent = et.getText().toString().trim();
                if (TextUtils.isEmpty(searchContent)) {
                    return true;
                }
                search = toURLEncoded(searchContent);
                //获取到输入框内容后,将其当做参数传递到服务器
                searchMvpPresenter = new AgencySearchMvpPresenter(mActivity, AgencyFragment.this);
                searchMvpPresenter.getSearchData(new AgencyUrlBean(mActivity, "0", 0, search));
                llAgencySearch.setFocusable(true);
                llAgencySearch.setFocusableInTouchMode(true);
                //  隐藏flAgencyContent,显示flAgencySearchContent
                flAgencyContent.setVisibility(View.GONE);
                flAgencySearchContent.setVisibility(View.VISIBLE);
                return true;
            }
            return false;
        }
    }


    /**
     * 给RecycleView添加数据
     *
     * @param testList
     */
    private void setCommonContract(SwipeMenuRecyclerView rlv, List<AgencyDataJson> testList) {

        adapter = new AgencySearchAdapter(mActivity, testList);
        rlv.setAdapter(adapter);
        adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(mActivity, DetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("AgencyDataJson.class", testList.get(position));
            intent.putExtra("AgencyDataJson", bundle);
            LogUtils.i(testList.get(position));
            startActivity(intent);
        });
    }

    /**
     * 刷新监听。
     */
    private OnRefreshListener mOnRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            searchMvpPresenter.getSearchData(new AgencyUrlBean(mActivity, "0", 0, search));

        }
    };

    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
                searchMvpPresenter.getSearchMoreData(new AgencyUrlBean(mActivity, "0", mLists.size(), search));

            }
        }
    };

    private Handler handler;
    private MyBroadCastRecever receiver;

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
                        pagerTab.showMsg(0, msgs);
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
        mActivity.registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity.unregisterReceiver(receiver);
    }
}
