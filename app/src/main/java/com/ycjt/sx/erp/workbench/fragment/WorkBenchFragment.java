package com.ycjt.sx.erp.workbench.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.WindowManager;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ycjt.sx.R;
import com.ycjt.sx.base.BaseFragment;
import com.ycjt.sx.base.FragmentFactory;
import com.ycjt.sx.erp.workbench.adapter.WorkBenchBusinessAdapter;
import com.ycjt.sx.utils.Utils;
import com.ycjt.sx.widget.fonts.FontTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuchao on 2017/6/19.
 */

public class WorkBenchFragment extends BaseFragment implements OnTabSelectListener {

    private String[] mTitles = {"销售统计", "采购统计"};
    private ViewPager vp;
    private FontTextView tvWorkBenchMore;
    private ArrayList<Fragment> mFragments;
    private String[] str = {"采购入库", "借用归还", "销售出库", "预留管理", "预留管理", "物料预览", "库存预览", "终端档案"};
    private String[] str1 = {"采购入库", "借用归还", "销售出库", "预留管理", "预留管理", "物料预览", "库存预览", "终端档案"};
    private SlidingTabLayout tabPager;


    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {


        String[] stringArray = Utils.getStringArray(mActivity, R.array.tab_workbench);
        //物控业务
        initBusiness();
        /** 字体加粗,大写 */
        tvWorkBenchMore = (FontTextView) mRootView.findViewById(R.id.tv_workbench_more);


        WindowManager systemService = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
        tabPager = (SlidingTabLayout) mRootView.findViewById(R.id.tl_3);
        tabPager.setTabWidth(Utils.px2dp(mActivity, systemService.getDefaultDisplay().getWidth() / stringArray.length));
        tabPager.setIndicatorWidth(Utils.px2dp(mActivity, systemService.getDefaultDisplay().getWidth() / stringArray.length));

        vp = (ViewPager) mRootView.findViewById(R.id.vp);

        vp.setAdapter(new MyAdapter(getChildFragmentManager()));
        tabPager.setViewPager(vp);// 将pagerTab和ViewPager进行绑定

        vp.setCurrentItem(0, false);
    }

    /**
     * 物控业务列表
     */
    private void initBusiness() {

        List<String> mList = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            mList.add(str[i]);
        }

        List<String> wList = new ArrayList<>();
        for (int j = 0; j < str1.length; j++) {
            wList.add(str1[j]);
        }
        RecyclerView rlvWorkBenchBusiness = (RecyclerView) mRootView.findViewById(R.id.rlv_work_bench_business);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        rlvWorkBenchBusiness.setLayoutManager(manager);
        WorkBenchBusinessAdapter adapter = new WorkBenchBusinessAdapter(getActivity(), mList);
        rlvWorkBenchBusiness.setAdapter(adapter);

        RecyclerView rlvWorkWork = (RecyclerView) mRootView.findViewById(R.id.rlv_work_bench_work);
        StaggeredGridLayoutManager manager2 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);

        rlvWorkWork.setLayoutManager(manager2);
        WorkBenchBusinessAdapter adapterw = new WorkBenchBusinessAdapter(getActivity(), wList);
        rlvWorkWork.setAdapter(adapterw);

    }


    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }


    class MyAdapter extends FragmentPagerAdapter {

        private String[] mTabNames;

        public MyAdapter(FragmentManager fm) {
            super(fm);
            mTabNames = Utils.getStringArray(mActivity, R.array.tab_workbench);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabNames[position];
        }

        @Override
        public Fragment getItem(int position) {
            BaseFragment fragment = FragmentFactory.getChildFragmentWorkBench(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return mTabNames.length;
        }

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_workbench;
    }


}
