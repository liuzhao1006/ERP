package com.ycjt.sx.erp.maillist.holder;

import com.ycjt.sx.erp.maillist.bean.ItemSubsidiaryBean;
import com.ycjt.sx.erp.maillist.bean.json.ItemMaillistOrgDataJson;

public interface ItemClickListener {
    /**
     * 展开子Item
     *
     * @param bean
     */
    void onExpandChildren(ItemMaillistOrgDataJson bean);

    /**
     * 隐藏子Item
     *
     * @param bean
     */
    void onHideChildren(ItemMaillistOrgDataJson bean);

}