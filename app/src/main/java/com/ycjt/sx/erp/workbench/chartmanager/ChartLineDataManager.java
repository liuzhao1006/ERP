package com.ycjt.sx.erp.workbench.chartmanager;

import android.content.Context;

import com.ycjt.sx.erp.workbench.bean.WorkItemJson;
import com.ycjt.sx.widget.customrecycle.adapter.LogUtils;
import com.ycjt.sx.widget.spinner.CustemObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 作者 : 刘朝,
 * on 2017/8/3,
 * GitHub : https://github.com/liuzhao1006
 */

public class ChartLineDataManager {

    //{'ID':'00000000-0000-0000-0000-000000000000','Name':'总额','BillTotalAmountCount':140000.000000,'Year':'2017','Month':'07'},
// {'ID':'59eaabbb-e0a0-44f0-8f6a-0c65edf33876','Name':'车联网有限公司','BillTotalAmountCount':null,'Year':null,'Month':null},
// {'ID':'7a07ff1e-a172-46a5-89e5-15262106960b','Name':'战略决策委员会','BillTotalAmountCount':null,'Year':null,'Month':null},
// {'ID':'04f12beb-d99d-43df-ac9a-3042957d6bda','Name':'广州亿程总部','BillTotalAmountCount':140000.000000,'Year':'2017','Month':'07'},
// {'ID':'2717059e-9f05-4193-94e1-3365734a3310','Name':'财务中心','BillTotalAmountCount':null,'Year':null,'Month':null},
// {'ID':'791cd940-50ac-4691-82b7-43d4479bca92','Name':'产品中心','BillTotalAmountCount':null,'Year':null,'Month':null},
// {'ID':'30e9290d-7b87-4963-8335-5131dff315c8','Name':'广州研发中心','BillTotalAmountCount':null,'Year':null,'Month':null},
// {'ID':'a28decc8-acbc-40ff-bd09-60718f55697d','Name':'贵州亿程公司','BillTotalAmountCount':null,'Year':null,'Month':null},
// {'ID':'c0fc5723-e0cb-4ea0-a8dc-b5be3c55a500','Name':'广东公司','BillTotalAmountCount':null,'Year':null,'Month':null},
// {'ID':'20faca53-9b62-4040-bce3-e5b490b56dbd','Name':'供应链中心','BillTotalAmountCount':null,'Year':null,'Month':null},
// {'ID':'b7f28a38-2717-47e5-afcc-fc6bc7da9581','Name':'人力行政中心','BillTotalAmountCount':null,'Year':null,'Month':null}

    private Context context;
    private List<WorkItemJson> workItemJsons;
    public Set<String> companyName;
    private boolean isYear;
    Map<String, Double> map;

    public ChartLineDataManager(Context context, List<WorkItemJson> workItemJsons) {
        this.context = context;
        this.workItemJsons = workItemJsons;

        companyName = getCompanyName();
    }

    /**
     * 获取总额
     *
     * @return
     */
    public Double getCountMoney() {
        return workItemJsons.get(0).getBillTotalAmountCount();
    }

    /**
     * 假数据
     *
     * @return
     */
    public List<WorkItemJson> getData() {
        workItemJsons = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            for (int k = 2000; k < 2010; k++) {
                for (int j = 1; j < 13; j++) {
                    WorkItemJson workItemJson = new WorkItemJson();
                    workItemJson.setBillTotalAmountCount((300 + i * 250));
                    workItemJson.setID("b7f28a38-2717-47e5-afcc-fc6bc7da9581");
                    if (i == 3 || i == 5) {
                        workItemJson.setName("大清生物");

                    } else {
                        workItemJson.setName("大清生物" + i);
                        workItemJson.setYear(k + "");
                    }

                    workItemJson.setMonth((j) + "");

                    workItemJsons.add(workItemJson);
                    LogUtils.i(workItemJson.toString() + "~~~假数据");
                }
            }
        }
        return workItemJsons;

    }

    /**
     * 获取集合中所有名称,重复名称过滤掉 专门给公司名称提供数据
     *
     * @return
     */
    public Set<String> getCompanyName() {
        Set<String> setName = new HashSet<>();
        for (WorkItemJson workItemJson : workItemJsons) {
            setName.add(workItemJson.getName());
        }
        if (setName.size() < 0 || setName.size() == 0) {
            throw new IndexOutOfBoundsException();
        }
        return setName;
    }

    /**
     * 获取每个Name下,年的数据
     *
     * @param name 年的名称
     * @return
     */
    public Set<String> getCompanyNameToYear(String name) {
        Set<String> setYear = new TreeSet<>();
        for (WorkItemJson workItemJson : workItemJsons) {
            if (name.equals(workItemJson.getName())) {
                if (workItemJson.getYear() != null) {//年有可能为null
                    setYear.add(workItemJson.getYear());
                }
            }
        }
        return setYear;
    }

    /**
     * 获取当前公司,当前年份的12个月的金额, 如果这个月没有数据,返回null
     *
     * @param name
     * @param year
     * @return
     */
    public Map<String, Double> getCompanyNameAndYearToMonth(String name, String year) {
        Map<String, Double> map = new HashMap<>();
        for (WorkItemJson workItemJson : workItemJsons) {
            if (name.equals(workItemJson.getName()) && year.equals(workItemJson.getYear())) {
                map.put(workItemJson.getMonth(), workItemJson.getBillTotalAmountCount());
            }
        }
        return map;
    }

    public Double getCompanyCountMoney(String company, String year) {
        double d = 0.0;
        for (WorkItemJson workItemJson : workItemJsons) {
            if (company.equals(workItemJson.getName()) && year.equals(workItemJson.getYear())) {
                d += workItemJson.getBillTotalAmountCount();
            }
        }
        return d;
    }


    /**
     * 获取当前公司,当前年的上一年,当前月的金额.
     *
     * @param name
     * @param year
     * @param month
     * @return
     */
    public Double getLastYearMoney(String name, String year, String month) {
        for (WorkItemJson workItemJson : workItemJsons) {
            if (name.equals(workItemJson.getName()) && year.equals(workItemJson.getYear()) && month.equals(workItemJson.getMonth())) {
                return workItemJson.getBillTotalAmountCount();
            }
        }
        return 0.0;
    }

    /**
     * 获取当前公司,当前年份,当前月份和去年同期差值
     *
     * @param name  当前公司
     * @param year  当前年
     * @param month 当前月
     * @return 当前月与去年同期金额的差值 如果返回0.0 说明没有数据
     */
    public Double getComparedLastYear(double money, String name, String year, String month) {
        //查看上一年是否存在
        isYear = false;
        Set<String> companyNameToYear = getCompanyNameToYear(name);//获取数据中所有年
        for (String allYear : companyNameToYear) {//查询年
            if ((Integer.parseInt(year) - 1) == Integer.parseInt(allYear)) {//当前年是有上一年
                isYear = true;
                break;
            }
        }
        if (isYear) {
            String lastYear = (Integer.parseInt(year) - 1) + "";
            Double lastYearMoney = getLastYearMoney(name, lastYear, month);
            return money - lastYearMoney;
        }
        return 0.0;
    }

    /**
     * 将公司名称添加给Spinner
     *
     * @return
     */
    public List<CustemObject> getSpinnerButtonList() {
        List<CustemObject> mList = new ArrayList<>();
        for (String str : companyName) {
            mList.add(new CustemObject(str));
        }
        return mList;
    }


    /**
     * 将数据存到map中Map<String , Map<String , Double>> lineDataMap = new HashMap<>();
     */
    public Map<String, Map<String, Double>> setLineChartData(List<String> mList, String name, String year) {
        Map<String, Map<String, Double>> lineDataMap = new HashMap<>();
        for (WorkItemJson workItemJson : workItemJsons) {//获取到有数据的map集合
            if (name.equals(workItemJson.getName()) && year.equals(workItemJson.getYear())) {//比较年和月是否一致
                for (String x : mList) {
                    String s = x.split("/")[1];
                    if (map == null)
                        map = new HashMap<>();
                    if (s.equals(workItemJson.getMonth())) {//集合中的月份和数据中月份相同,添加到集合
                        map.put(x, workItemJson.getBillTotalAmountCount());
                        lineDataMap.put(x, map);
                        com.apkfuns.logutils.LogUtils.i(x+"~~~~~~将数据存到map中Map<String , Map<String , Double>> lineDataMap = new HashMap<>();");
                    }
                }
            }
        }
        //上面是获取到当前公司, 当前年的说有数据,
        // 存放到map中,但是UI需要的是12个月的数据,
        // 下面的方法是把没有数据的月份添加一个默认值
        // 其实如果服务器把所有数据都能给,那就不用那么麻烦了,
        // 但是从服务器返回来的数据,是只要有的话才返回来.
        for (int i = 0; i < mList.size(); i++) {

            String s = mList.get(i).split("/")[1];
            if (!lineDataMap.containsKey(s)) {
                if (map == null)
                    map = new HashMap<>();
                map.put(mList.get(i), 0.0);
                lineDataMap.put(mList.get(i), map);
                com.apkfuns.logutils.LogUtils.i(mList.get(i)+"~~~~~~上面是获取到当前公司, 当前年的说有数据");
            }
        }
        com.apkfuns.logutils.LogUtils.i(lineDataMap.toString()+"~~~~~~上面是获取到当前公司, 当前年的说有数据");


        return lineDataMap;
    }
}
