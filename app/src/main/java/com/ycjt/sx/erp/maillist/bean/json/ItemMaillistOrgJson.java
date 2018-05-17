package com.ycjt.sx.erp.maillist.bean.json;

import com.ycjt.sx.base.BaseBean;

import java.util.List;

/**
 * Created by liuchao on 2017/6/28.
 */

public class ItemMaillistOrgJson extends BaseBean {


    /**
     * Code : 0
     * Data : [
     *
     *      {"organizeName":"西安驻点","OUserList":[
     *
     *              {"userName":"周荣昌","photo":null,"phone":null,"deptPosition":null,"deptUserId":"251f8bf1-b900-442a-b5ce-85a5e4a082a8","deptOrganizeId":"4a2663da-92a2-4852-bce6-575cc2025bb5"}
     *
     *             ]},
     *
     *      {"organizeName":"汉中驻点","OUserList":null},
     *
     *
     *      {"organizeName":"榆林驻点","OUserList":null},
     *
     *
     *      {"organizeName":"开发部","OUserList":[
     *
     *              {"userName":"test","photo":null,"phone":null,"deptPosition":null,"deptUserId":"19be8927-d918-410e-a822-2180e1a74a0f","deptOrganizeId":"884f493b-653a-4e25-a582-9d565d1ca192"}
     *
     *              ]},
     *
     *      {"organizeName":"技术部","OUserList":null},
     *
     *
     *      {"organizeName":"宝鸡驻点","OUserList":null},
     *
     *
     *      {"organizeName":"安康驻点","OUserList":null}]
     * message : 获取人员信息成功
     */





    private String Code;
    private String Data;
    private String message;
    private List<ItemMaillistOrgDataJson> dataJsons;

    public List<ItemMaillistOrgDataJson> getDataJsons() {
        return dataJsons;
    }

    public void setDataJsons(List<ItemMaillistOrgDataJson> dataJsons) {
        this.dataJsons = dataJsons;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
