package com.ycjt.sx.erp.workbench.chartmanager;

import android.content.Context;

import com.ycjt.sx.erp.workbench.bean.WorkItemJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者 : 刘朝,
 * on 2017/8/3,
 * GitHub : https://github.com/liuzhao1006
 */

public class ChartPieDataManager {

    private Context context;
    private List<WorkItemJson> workItemJsons;

    public ChartPieDataManager(Context context, List<WorkItemJson> workItemJsons) {
        this.context = context;
        this.workItemJsons = workItemJsons;
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
     * 根据年和月获取所有公司以及一个int类型的标记,这个标记用来设置颜色的
     *
     * @return
     */
    public Map<String, Integer> getCompanyNameMap() {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 1; i < workItemJsons.size(); i++) {
            map.put(workItemJsons.get(i).getName(), i);
        }
        return map;
    }

    /**
     * 根据年和月获取所有公司以及一个int类型的标记,这个标记用来设置颜色的
     *
     * @return
     */
    public List<String> getCompanyNameList() {
        List<String> mList = new ArrayList<>();
        for (int i = 1; i < workItemJsons.size(); i++) {
            mList.add(workItemJsons.get(i).getName());
        }
        return mList;
    }

    /**
     * 根据公司名称获取金额,存放在map里面
     *
     * @return
     */
    public Map<String, Double> getCompanyNameMoney() {
        Map<String, Double> map = new HashMap<>();
        for (int i = 1; i < workItemJsons.size(); i++) {
            map.put(workItemJsons.get(i).getName(), workItemJsons.get(i).getBillTotalAmountCount());
        }
        return map;
    }
}
