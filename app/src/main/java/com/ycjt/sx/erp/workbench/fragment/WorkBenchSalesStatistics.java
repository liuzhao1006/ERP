package com.ycjt.sx.erp.workbench.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.ycjt.sx.R;
import com.ycjt.sx.base.BaseFragment;
import com.ycjt.sx.erp.workbench.activity.WorkBenchChartSaleActivity;
import com.ycjt.sx.erp.workbench.bean.WorkBenchUrlBean;
import com.ycjt.sx.erp.workbench.bean.WorkItemJson;
import com.ycjt.sx.erp.workbench.chart.ChartUtils;
import com.ycjt.sx.erp.workbench.chartmanager.ChartPieDataManager;
import com.ycjt.sx.erp.workbench.chartmanager.PieChartManager;
import com.ycjt.sx.erp.workbench.presenter.WorkBenchPersenter;
import com.ycjt.sx.widget.fonts.FontTextView;
import com.ycjt.sx.widget.spinner.AbstractSpinerAdapter;
import com.ycjt.sx.widget.spinner.CustemObject;
import com.ycjt.sx.widget.spinner.CustemSpinerAdapter;
import com.ycjt.sx.widget.spinner.SpinerPopWindow;
import com.ycjt.sx.widget.view.CircularProgress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ycjt.sx.utils.Utils.getNormal;

/**
 * Created by liuchao on 2017/7/7.
 * <p>
 * http://192.168.103.247:8011/Service1.svc/
 * GetSellBillManage/99D7E378-0BE0-4703-B157-E88C100C5DF1/DEVICEID/04f12beb-d99d-43df-ac9a-3042957d6bda/2017/07
 */
@SuppressLint("ValidFragment")
public class WorkBenchSalesStatistics extends BaseFragment implements OnChartValueSelectedListener, ISimple {
    private PieChart mChart;
    private LinearLayout llWorkBenchChartMore;
    private Button s1;
    private Button s2;
    private SpinerPopWindow s1SPW;
    private SpinerPopWindow s2SPW;
    private FontTextView tvCount;
    private double countMoney = 0.00;//设置总额
    private PieChartManager manager;
    private WorkBenchPersenter persenter;
    private ChartUtils chartUtils;
    private List<CustemObject> year;
    private List<CustemObject> month;
    private AbstractSpinerAdapter mAdapters1;
    private AbstractSpinerAdapter mAdapters2;
    private CircularProgress cp;

    @Override
    public int getLayoutId() {
        return R.layout.fr_simple_card;
    }

    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {
        chartUtils = new ChartUtils(mActivity);
        year = chartUtils.getYear();
        month = chartUtils.getMonth();

        cp = (CircularProgress) mRootView.findViewById(R.id.cp);
        setYearPopupWindow();
        setMonthPopupWindow();
        llWorkBenchChartMore = (LinearLayout) mRootView.findViewById(R.id.ll_workbench_chart_more);
        llWorkBenchChartMore.setOnClickListener(view -> {
            Intent i = new Intent(mActivity, WorkBenchChartSaleActivity.class);
            startActivity(i);
        });
        //获取数据 饼状图
        persenter = new WorkBenchPersenter(mActivity, this);
        //获取销售订单
        init(mRootView);
    }


    /**
     * 设置年,弹窗
     */
    private void setYearPopupWindow() {
        s1 = (Button) mRootView.findViewById(R.id.s1);
        s1SPW = new SpinerPopWindow(mActivity, year, 1);
        s1SPW.setItemListener(pos -> setHeros1(pos));
        s1.setOnClickListener(view -> s1SPW.showAsDropDown(s1));
        mAdapters1 = new CustemSpinerAdapter(mActivity);
        s1SPW.setAdatper(mAdapters1);
        mAdapters1.refreshData(year, 0);
        s1SPW.setItemListener(pos -> setHeros1(pos));
        String currentYear = chartUtils.setCurrentYearButtonText();
        if (currentYear != null) {
            s1.setText(currentYear);//设置当前年份
        } else {
            s1.setText(year.get(0).data);
        }
    }

    /**
     * 设置月,弹窗
     */
    private void setMonthPopupWindow() {
        s2 = (Button) mRootView.findViewById(R.id.s2);
        s2SPW = new SpinerPopWindow(getActivity(), month, 1);
        mAdapters2 = new CustemSpinerAdapter(mActivity);
        s2SPW.setAdatper(mAdapters2);
        s2SPW.setItemListener(pos -> setHeros2(pos));
        mAdapters2.refreshData(month, 0);
        s2.setOnClickListener(view -> s2SPW.showAsDropDown(s2));
        String currentMonth = chartUtils.setCurrentMonthButtonText();
        if (currentMonth != null) {
            s2.setText(currentMonth);//设置当前年份
        } else {
            s2.setText(month.get(0).data);//没有 设置默认值
        }
    }

    private void init(View view) {
        mChart = (PieChart) view.findViewById(R.id.chart1);
        tvCount = (FontTextView) view.findViewById(R.id.tv_simple_card_count);
        getHttpData();
        //初始化时 创建图表管理器
        manager = new PieChartManager(mActivity, mChart, "月销售统计");
        manager.setCircle(0f, 0f, false);
        manager.setLegend(false, false, false);
        manager.setPieAnimal(3000, 3000);
        manager.setTouchRotation(true, true);

    }

    /**
     * 请求网络参数
     */
    private void getHttpData() {
        //获取销售统计数据
        WorkBenchUrlBean bean = new WorkBenchUrlBean(mActivity, s1.getText().toString().trim(), s2.getText().toString().trim());
        persenter.getWorkBenchSellData(bean);
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void successedShop(List<WorkItemJson> workItemJsons) {

    }

    @Override
    public void successedSale(List<WorkItemJson> workItemJsons) {
        ChartPieDataManager pieDataManager = new ChartPieDataManager(mActivity, workItemJsons);//集合数据管理类
        tvCount.setText(getNormal(pieDataManager.getCountMoney()));
        Map<String, Double> companyNameMoney = pieDataManager.getCompanyNameMoney();
        manager.setmChart(companyNameMoney, 100, countMoney);

    }

    @Override
    public void successedSaleLine(List<WorkItemJson> workItemJsons) {

    }

    @Override
    public void successedShopLine(List<WorkItemJson> workItemJsons) {

    }

    @Override
    public void failed(String s) {
        showToast(s, TOAST_SHORT);
        Map<String, Double> map = new HashMap<>();
        map.put("无数据,请重新查询......", 1.0);
        manager.setmChart(map, 100, 1);//给图表设置数据
        tvCount.setText("无");
    }

    @Override
    public void failedLine(String s) {

    }

    private void setHeros1(int pos) {
        if (pos >= 0 && pos <= year.size()) {
            CustemObject value = year.get(pos);
            s1.setText(value.toString());
            //获取销售统计数据
            getHttpData();
        }
    }

    private void setHeros2(int pos) {
        if (pos >= 0 && pos <= month.size()) {
            CustemObject value = month.get(pos);
            s2.setText(value.toString());
            getHttpData();
        }
    }

    @Override
    public void startDialog() {
        cp.setVisibility(View.VISIBLE);
        mChart.setVisibility(View.GONE);
    }

    @Override
    public void stopDialog() {
        cp.setVisibility(View.GONE);
        mChart.setVisibility(View.VISIBLE);
    }
}
