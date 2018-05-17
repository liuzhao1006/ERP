package com.ycjt.sx.base.inter;

/**
 * 2017年07月14日10:34:31
 */
public interface IStringModel {

    /**
     * 链接成功,拿到数据
     *
     * @param data
     * @return
     */
    void successed(int what, String data);

    /**
     * 链接失败,拿到失败信息
     *
     * @param message
     * @return
     */
    void failed(int what, String message);

    /**
     * 添加开始进度条
     */
    void startDialog();

    /**
     * 停止进度条
     */
    void stopDialog();
}
