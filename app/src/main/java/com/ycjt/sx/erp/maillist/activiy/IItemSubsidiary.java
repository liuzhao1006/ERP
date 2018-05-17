package com.ycjt.sx.erp.maillist.activiy;

import com.ycjt.sx.erp.maillist.bean.json.ItemMaillistOrgDataJson;
import com.ycjt.sx.erp.maillist.bean.json.MaillistDataJson;

import java.util.List;

/**
 * Created by liuchao on 2017/6/28.
 */

public interface IItemSubsidiary {
    void successed(List<ItemMaillistOrgDataJson> dataList);
}
