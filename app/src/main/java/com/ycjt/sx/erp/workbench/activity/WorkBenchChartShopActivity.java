package com.ycjt.sx.erp.workbench.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.ycjt.sx.R;
import com.ycjt.sx.base.BaseActivity;
import com.ycjt.sx.erp.workbench.bean.WorkBenchUrlBean;
import com.ycjt.sx.erp.workbench.bean.WorkItemJson;
import com.ycjt.sx.erp.workbench.chart.ChartUtils;
import com.ycjt.sx.erp.workbench.chartmanager.ChartLineDataManager;
import com.ycjt.sx.erp.workbench.chartmanager.ChartPieDataManager;
import com.ycjt.sx.erp.workbench.chartmanager.LineChartManager;
import com.ycjt.sx.erp.workbench.chartmanager.PieChartManager;
import com.ycjt.sx.erp.workbench.fragment.ISimple;
import com.ycjt.sx.erp.workbench.presenter.WorkBenchPersenter;
import com.ycjt.sx.widget.fonts.FontTextView;
import com.ycjt.sx.widget.spinner.AbstractSpinerAdapter;
import com.ycjt.sx.widget.spinner.CustemObject;
import com.ycjt.sx.widget.spinner.CustemSpinerAdapter;
import com.ycjt.sx.widget.spinner.SpinerPopWindow;
import com.ycjt.sx.widget.view.CircularProgress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ycjt.sx.utils.Utils.getNormal;
import static com.ycjt.sx.widget.animal.AnimationUtils.setRotateAnimationButton;

/**
 * 采购饼状图
 * http://192.168.103.247:8011/Service1.svc/GetSellBillManage/
 * 99D7E378-0BE0-4703-B157-E88C100C5DF1/DEVICEID/04f12beb-d99d-43df-ac9a-3042957d6bda/2017/08
 * <p>
 * 采购线形图
 * <p>
 * http://192.168.103.247:8011/Service1.svc/GetSellBillManage/
 * 99D7E378-0BE0-4703-B157-E88C100C5DF1/DEVICEID/00000000-0000-0000-0000-000000000000/2017/00
 */
public class WorkBenchChartShopActivity extends BaseActivity implements ISimple, OnChartValueSelectedListener {
    private PieChart mChart;
    private BarChart barChart;
    private FontTextView tvM;
    private FontTextView tvMy;
    private FontTextView tvM2;
    private FontTextView tvM3;
    private FontTextView tvM4;
    private FontTextView tvM5;
    private FontTextView tvM6;
    private Button s4;
    private Button s5;
    private Button s3;
    private Button s6;
    private AbstractSpinerAdapter mAdapters3;
    private AbstractSpinerAdapter mAdapters4;
    private AbstractSpinerAdapter mAdapters5;
    private AbstractSpinerAdapter mAdapters6;
    private SpinerPopWindow mSpinerPopWindows3;
    private SpinerPopWindow mSpinerPopWindows4;
    private SpinerPopWindow mSpinerPopWindows5;
    private SpinerPopWindow mSpinerPopWindows6;
    private TextView tvSaleCount;
    private double countMoney = 0.00;//设置总额
    private PieChartManager manager;
    private WorkBenchPersenter persenter;
    private LineChartManager lineChartManager;
    private ChartUtils chartUtils;
    private List<CustemObject> year;
    private List<CustemObject> month;
    private List<String> mList;
    private CircularProgress cp;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_work_bench_chart;
    }

    @Override
    protected void initView() {
        tvTopModdel.setText("采购统计");
        ivFalse.setVisibility(View.VISIBLE);
        ibTopLeft.setOnClickListener(view -> finish());
        ibTopRight.setVisibility(View.GONE);
        persenter = new WorkBenchPersenter(this, this);
        chartUtils = new ChartUtils(this);
        year = chartUtils.getYear();
        month = chartUtils.getMonth();
        cp = (CircularProgress) findViewById(R.id.cp);
        findview();
    }

    /**
     * 初始化年的按钮
     */
    private void getPopupWindowS3() {
        s3 = (Button) findViewById(R.id.s3);
        s3.setOnClickListener(view -> mSpinerPopWindows3.showAsDropDown(s3));
        mAdapters3 = new CustemSpinerAdapter(this);
        mAdapters3.refreshData(year, 0);
        mSpinerPopWindows3 = new SpinerPopWindow(this, year, 1);
        mSpinerPopWindows3.setAdatper(mAdapters3);
        mSpinerPopWindows3.setItemListener(pos -> setHeros3(pos));
        String currentYear = chartUtils.setCurrentYearButtonText();
        if (currentYear != null) {
            s3.setText(currentYear);//设置当前年份
        } else {
            s3.setText(year.get(0).data);
        }
    }

    /**
     * 初始化月的按钮
     */
    private void getPopupWindowS4() {
        s4 = (Button) findViewById(R.id.s4);
        s4.setOnClickListener(view -> mSpinerPopWindows4.showAsDropDown(s4));
        mAdapters4 = new CustemSpinerAdapter(this);
        mAdapters4.refreshData(month, 0);
        mSpinerPopWindows4 = new SpinerPopWindow(this, month, 1);
        mSpinerPopWindows4.setAdatper(mAdapters4);
        mSpinerPopWindows4.setItemListener(pos -> setHeros4(pos));
        String currentMonth = chartUtils.setCurrentMonthButtonText();
        if (currentMonth != null) {
            s4.setText(currentMonth);//设置当前年份
        } else {
            s4.setText(month.get(0).data);//没有 设置默认值
        }
    }

    /**
     * 初始化Name的按钮
     */
    private void getPopupWindowS5() {
        s5 = (Button) findViewById(R.id.s5);
        s5.setOnClickListener(view -> mSpinerPopWindows5.showAsDropDown(s5));
        mAdapters5 = new CustemSpinerAdapter(this);
        mSpinerPopWindows5 = new SpinerPopWindow(this, new ArrayList<>(), 1);
        mSpinerPopWindows5.setAdatper(mAdapters5);
    }


    private void getPopupWindowS6() {
        s6 = (Button) findViewById(R.id.s6);
        s6.setOnClickListener(view -> {
            mSpinerPopWindows6.showAsDropDown(s6);
            chartUtils.setS6Animal(view);
        });
        mAdapters6 = new CustemSpinerAdapter(this);
        mAdapters6.refreshData(new ArrayList(), -1);//初始化的时候, 由于没有数据,此时传递一个空的集合
        mSpinerPopWindows6 = new SpinerPopWindow(this, new ArrayList<>(), 1);
        mSpinerPopWindows6.setAdatper(mAdapters6);

    }


    private void findview() {
        tvM = (FontTextView) findViewById(R.id.tv_work_money);
        tvMy = (FontTextView) findViewById(R.id.tv_work_money_year);
        //本月
        tvM2 = (FontTextView) findViewById(R.id.tv_work_month);
        tvM3 = (FontTextView) findViewById(R.id.tv_work_mounth);
        tvM4 = (FontTextView) findViewById(R.id.tv_work_mounth_year);
        tvM5 = (FontTextView) findViewById(R.id.tv_work_up);
        tvM6 = (FontTextView) findViewById(R.id.tv_work_up_year);
        tvSaleCount = (FontTextView) findViewById(R.id.tv_sale_count);
        barChart = (BarChart) findViewById(R.id.chart2);//线形图
        mChart = (PieChart) findViewById(R.id.chart1);//饼状图
        getPopupWindowS3();
        getPopupWindowS4();
        getPopupWindowS5();
        getPopupWindowS6();

        mList = chartUtils.setTime(s3.getText().toString().trim());
        lineChartManager = new LineChartManager(this, barChart, mList, "销售统计");
        lineChartManager.setBarChartCommmon();
        lineChartManager.setFormatter();
        lineChartManager.setLineLegend();
        lineChartManager.setPieAnimal(3000, 3000);
        manager = new PieChartManager(this, mChart, "销售统计");//饼状图管理器
        manager.setCircle(0f, 0f, false);
        manager.setLegend(false, false, false);
        manager.setPieAnimal(3000, 3000);
        manager.setTouchRotation(true, true);
        getHttpData(s3, s4);//进入到主页 获取所有饼状图
        getHttpLineData(s3);//线形图 获取一年的数据
        barChart.setOnChartValueSelectedListener(this);
    }

    /**
     * 获取饼状图数据
     *
     * @param btn1
     * @param btn2
     */
    private void getHttpData(Button btn1, Button btn2) {
        WorkBenchUrlBean bean = new WorkBenchUrlBean(this, btn1.getText().toString().trim(), btn2.getText().toString().trim());//获取采购统计数据
        persenter.getWorkBenchSellData(bean);
    }

    public void getHttpLineData(Button btn1) {
        WorkBenchUrlBean bean = new WorkBenchUrlBean(this, btn1.getText().toString().trim(), "00");//获取采购统计数据
        persenter.getWorkBenchSellDataLine(bean);
    }

    private void setHeros5(int pos, ChartLineDataManager mapManager) {
        List<CustemObject> list = mapManager.getSpinnerButtonList();
        if (pos >= 0 && pos <= list.size()) {
            CustemObject value = list.get(pos);
            s5.setText(value.toString());
            Double companyCountMoney = mapManager.getCompanyCountMoney(value.data, s3.getText().toString());
            tvM.setText(getNormal(companyCountMoney));
            Map<String, Map<String, Double>> map = mapManager.setLineChartData(mList, value.toString(), s3.getText().toString());
            lineChartManager.setLineData(map, companyCountMoney, 100);
        } else {
            CustemObject value2 = new CustemObject();
            value2.data = "";
            s5.setText("无");
            lineChartManager.setLineData(new HashMap<>(), 0.1, 100);
            tvM.setText(getNormal(0.0));
        }
    }

    private void setHeros4(int pos) {
        if (pos >= 0 && pos <= month.size()) {
            s4.setText(month.get(pos).toString());
            getHttpData(s3, s4);
        }
    }

    private void setHeros3(int pos) {
        if (pos >= 0 && pos <= year.size()) {
            s3.setText(year.get(pos).toString());
            getHttpData(s3, s4);
            mList.clear();
            mList = chartUtils.setTime(s3.getText().toString().trim());
            getHttpLineData(s3);
        }
    }


    @Override
    public void successedShop(List<WorkItemJson> workItemJsons) {
        ChartPieDataManager pieDataManager = new ChartPieDataManager(this, workItemJsons);//集合数据管理类
        tvSaleCount.setText(getNormal(pieDataManager.getCountMoney()));//获取总额
        Map<String, Double> companyNameMoney = pieDataManager.getCompanyNameMoney();
        manager.setmChart(companyNameMoney, 100, countMoney);
        List<String> companyNameList = pieDataManager.getCompanyNameList();
        //获取数据,刷新数据
        mSpinerPopWindows6.refreshData(companyNameList, 0);//将数据传递给popupwindow刷新
        mSpinerPopWindows6.setOnDismissListener(() -> setRotateAnimationButton(s6, 180, 360, 1000));
        mAdapters6.notifyDataSetInvalidated();//再次调用刷新
    }

    @Override
    public void failed(String s) {
        showToast(s, TOAST_SHORT);
        mSpinerPopWindows6.refreshData(new ArrayList<>(), -1);//将数据传递给popupwindow刷新
        mSpinerPopWindows6.setOnDismissListener(() -> setRotateAnimationButton(s6, 180, 360, 1000));
        mAdapters6.notifyDataSetInvalidated();//再次调用刷新
        Map<String, Double> map = new HashMap<>();
        map.put("无数据,请重新查询......", 1.0);
        manager.setmChart(map, 100, 1);//给图表设置数据
        tvSaleCount.setText("无");//获取总额
    }


    @Override
    public void successedShopLine(List<WorkItemJson> workItemJsons) {
        ChartLineDataManager mapManager = new ChartLineDataManager(this, workItemJsons);
        //获取公司名称
        List<CustemObject> spinner5 = mapManager.getSpinnerButtonList();
        mAdapters5.refreshData(spinner5, 0);
        mSpinerPopWindows5.refreshData(spinner5, 0);
        mSpinerPopWindows5.setItemListener(pos -> setHeros5(pos, mapManager));//设置文字
    }

    @Override
    public void failedLine(String s) {
        showToast(s, TOAST_SHORT);
        mSpinerPopWindows5.refreshData(new ArrayList<>(), -1);//将数据传递给popupwindow刷新
        mAdapters5.notifyDataSetInvalidated();//再次调用刷新
        lineChartManager.setLineData(new HashMap<>(), 0.1, 100);
        tvM.setText("无");//获取总额
        s5.setText(new CustemObject("无...").data.toString());
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null) {
            return;
        }
        if (chartUtils.getMonthCurrent() == (int) e.getX()) {
            tvM2.setText("本月");
        } else {
            tvM2.setText(((int) e.getX() + 1) + "月");
        }
    }

    @Override
    public void onNothingSelected() {
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

    @Override
    public void successedSaleLine(List<WorkItemJson> workItemJsons) {
    }

    @Override
    public void successedSale(List<WorkItemJson> workItemJsons) {
    }
}
