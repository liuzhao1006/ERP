package com.ycjt.sx.erp.maillist.presenter;

import android.graphics.Bitmap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.base.BaseModel;
import com.ycjt.sx.erp.maillist.activiy.IItemSubsidiary;
import com.ycjt.sx.erp.maillist.bean.json.ItemMaillistOrgDataJson;
import com.ycjt.sx.erp.maillist.bean.json.ItemMaillistOrgJson;
import com.ycjt.sx.erp.maillist.bean.json.MaillistDataJson;
import com.ycjt.sx.erp.maillist.bean.json.MaillistJson;
import com.ycjt.sx.erp.maillist.fragment.IMaillistFragment;
import com.ycjt.sx.erp.maillist.model.ItemSubsidiaryModel;

import java.util.List;

/**
 * Created by liuchao on 2017/6/28.
 */

public class ItemSubsidaryOrganizationPresenter {

    private IItemSubsidiary itemSubsidiary;

    public ItemSubsidaryOrganizationPresenter(IItemSubsidiary itemSubsidiary) {
        this.itemSubsidiary = itemSubsidiary;
    }


    public void getItemData(String url) {

        ItemSubsidiaryModel model = new ItemSubsidiaryModel(url);
        model.getData();
        model.setOnAddRequest(new BaseModel.OnAddRequest() {
            @Override
            public void successed(String s) {

                //先解析出第一层
                JSONObject jsonObject = JSON.parseObject(s);
                ItemMaillistOrgJson orgJson = JSON.parseObject(s, ItemMaillistOrgJson.class);
                String code = orgJson.getCode();
                String data = orgJson.getData();
                String message = orgJson.getMessage();
                if (!code.equalsIgnoreCase("0") ||
                        !message.equalsIgnoreCase("获取组织机构成功")) {//登录失败
                    //获取信息不正确
//                    return;
                }
                List<ItemMaillistOrgDataJson> dataList = JSON.parseArray(data, ItemMaillistOrgDataJson.class);
                LogUtils.i("code :" + code + ",data : " + dataList.toString() + ",message :" + message);

                parseOUserList(dataList);
                itemSubsidiary.successed(dataList);
            }

            @Override
            public void failed(String s) {

            }

            @Override
            public void startDialog() {

            }

            @Override
            public void stopDialog() {

            }
        });
    }
    public void parseOUserList(List<ItemMaillistOrgDataJson> dataList) {

        for (int i = 0; i < dataList.size(); i++) {
            ItemMaillistOrgDataJson maillistDataJson = dataList.get(i);
            String organizeName = maillistDataJson.getOrganizeName();
            List<ItemMaillistOrgDataJson.OUserListBean> oUserList = maillistDataJson.getOUserList();
            if (oUserList != null) {
                for (int j = 0; j < oUserList.size(); j++) {
                    ItemMaillistOrgDataJson.OUserListBean oUserListBean = oUserList.get(j);

                    if (oUserListBean != null) {

                        String deptOrganizeId = oUserListBean.getDeptOrganizeId();
                        Object deptPosition = oUserListBean.getDeptPosition();
                        String deptUserId = oUserListBean.getDeptUserId();
                        Object phone = oUserListBean.getPhone();
                        Object photo = oUserListBean.getPhoto();
                        String userName = oUserListBean.getUserName();

                        LogUtils.i(deptOrganizeId + "/" + deptPosition + "/" + deptUserId + "/" + phone + "/" + photo + "/" + userName + "/");
                    }
                }
            }

        }

    }


}
