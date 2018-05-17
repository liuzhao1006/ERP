package com.ycjt.sx.erp.maillist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.R;
import com.ycjt.sx.base.mvpbase.BaseViewHolder;
import com.ycjt.sx.erp.maillist.bean.json.ItemMaillistOrgDataJson;
import com.ycjt.sx.erp.maillist.bean.json.ItemMaillistOrgDataJson.OUserListBean;
import com.ycjt.sx.erp.maillist.holder.ItemClickListener;
import com.ycjt.sx.erp.maillist.holder.ItemSubsidiaryChildViewHolder;
import com.ycjt.sx.erp.maillist.holder.ItemSubsidiaryParentViewHolder;

import java.util.List;

/**
 * Created by liuchao on 2017/6/26.
 */

public class ItemSubsidiaryAdapter extends RecyclerView.Adapter<BaseViewHolder> implements ItemSubsidiaryParentViewHolder.getPositionitem {

    private Context context;//对象
    private LayoutInflater inflater;//布局管理器
    private OnScrollListener scrollListener;//滑动监听
    private List<ItemMaillistOrgDataJson> itemSubsidiaryBeans;//Javabean集合, 数据集合

    public ItemSubsidiaryAdapter(Context context, List<ItemMaillistOrgDataJson> itemSubsidiaryBeans) {
        this.context = context;
        this.itemSubsidiaryBeans = itemSubsidiaryBeans;
        this.inflater = LayoutInflater.from(context);

    }


    public void parseOUserList(List<ItemMaillistOrgDataJson> dataList) {

        for (int i = 0; i < dataList.size(); i++) {
            ItemMaillistOrgDataJson maillistDataJson = dataList.get(i);
            String organizeName = maillistDataJson.getOrganizeName();
            List<OUserListBean> oUserList = maillistDataJson.getOUserList();
            if (oUserList != null) {
                for (int j = 0; j < oUserList.size(); j++) {
                    OUserListBean oUserListBean = oUserList.get(j);

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


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = null;
        switch (i) {
            case ItemMaillistOrgDataJson.PARENT_ITEM:
                view = inflater.inflate(R.layout.item_maillist_subsidiary_parent, viewGroup, false);
                return new ItemSubsidiaryParentViewHolder(context, view);

            case ItemMaillistOrgDataJson.CHILD_ITEM:
                view = inflater.inflate(R.layout.item_maillist_subsidiary_child, viewGroup, false);
                return new ItemSubsidiaryChildViewHolder(context, view);
            default:
                view = inflater.inflate(R.layout.item_maillist_subsidiary_parent, viewGroup, false);
                return new ItemSubsidiaryParentViewHolder(context, view);
        }
    }

    /**
     * 根据不同的类型绑定View
     *
     * @param baseViewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
        final ItemMaillistOrgDataJson itemMaillistOrgDataJson = itemSubsidiaryBeans.get(i);
        switch (i) {
            case ItemMaillistOrgDataJson.PARENT_ITEM:

                ItemSubsidiaryParentViewHolder parentViewHolder = (ItemSubsidiaryParentViewHolder) baseViewHolder;
                parentViewHolder.bindView(itemMaillistOrgDataJson, i, itemClickListener);

                break;

            case ItemMaillistOrgDataJson.CHILD_ITEM:

                List<OUserListBean> oUserList = itemMaillistOrgDataJson.getOUserList();
                ItemSubsidiaryChildViewHolder childViewHolder = (ItemSubsidiaryChildViewHolder) baseViewHolder;
                if (oUserList != null) {
                    for (int j = 0; j < oUserList.size(); j++) {
                        OUserListBean oUserListBean = oUserList.get(j);

                        if (oUserListBean != null) {

                            String deptOrganizeId = oUserListBean.getDeptOrganizeId();
                            Object deptPosition = oUserListBean.getDeptPosition();
                            String deptUserId = oUserListBean.getDeptUserId();
                            Object phone = oUserListBean.getPhone();
                            Object photo = oUserListBean.getPhoto();
                            String userName = oUserListBean.getUserName();

                            LogUtils.i(deptOrganizeId + "/" + deptPosition + "/" + deptUserId + "/" + phone + "/" + photo + "/" + userName + "/");
                            childViewHolder.bindView(oUserListBean, i);
                            if (mListener != null) {
                                childViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mListener.OnClickListener(view, i);
                                    }
                                });
                            }
                        }
                    }
                }
                break;
        }


    }

    @Override
    public int getItemCount() {
        return itemSubsidiaryBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return itemSubsidiaryBeans.get(position).getType();
    }

    /**
     * 确定当前点击的item位置并返回
     *
     * @param uuid
     * @return
     */
    protected int getCurrentPosition(String uuid) {
        for (int i = 0; i < itemSubsidiaryBeans.size(); i++) {
            if (uuid.equalsIgnoreCase(itemSubsidiaryBeans.get(i).getOUserList().size() + "")) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 在父布局下方插入一条数据
     *
     * @param bean
     * @param position
     */
    public void add(ItemMaillistOrgDataJson bean, int position) {
        itemSubsidiaryBeans.add(position, bean);
        notifyItemInserted(position);
    }


    /**
     * 移除子布局数据
     *
     * @param position
     */
    protected void remove(int position) {
        itemSubsidiaryBeans.remove(position);
        notifyItemRemoved(position);
    }

    private int x;
    @Override
    public int getpos(int s) {
        return x;
    }

    /**
     * 滚动监听接口
     */
    public interface OnScrollListener {
        void scrollTo(int pos);
    }

    public void setOnScrollListener(OnScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    /**
     * 封装子布局数据对象并返回
     * 注意，此处只是重新封装一个DataBean对象，为了标注Type为子布局数据，进而展开，展示数据
     * 要和onHideChildren方法里的getChildBean()区分开来
     *
     * @param bean
     * @return
     */
    private OUserListBean getChildItemSubsidiaryBean(ItemMaillistOrgDataJson bean) {
        OUserListBean child = new OUserListBean();
        child.setType(bean.getOUserList().size());
        for (int i = 0; i < bean.getOUserList().size(); i++) {

            child.setDeptUserId(bean.getOUserList().get(i).getDeptUserId());
            child.setDeptPosition(bean.getOUserList().get(i).getDeptPosition());
            child.setUserName(bean.getOUserList().get(i).getUserName());
            child.setDeptUserId(bean.getOUserList().get(i).getDeptUserId());
            child.setDeptOrganizeId(bean.getOUserList().get(i).getDeptOrganizeId());
            child.setPhone(bean.getOUserList().get(i).getPhone());
            child.setPhoto(bean.getOUserList().get(i).getPhoto());
        }
        return child;
    }

    /**
     * 父控件item设置监听
     */
    private ItemClickListener itemClickListener = new ItemClickListener() {
        @Override
        public void onExpandChildren(ItemMaillistOrgDataJson bean) {

//            int position = getCurrentPosition(x + "");//确定当前点击的item位置
            OUserListBean children = getChildItemSubsidiaryBean(bean);//获取要展示的子布局数据对象,区分
            if (children == null) return;
            add(bean, x + 1);
            if (x == itemSubsidiaryBeans.size() - 2 && scrollListener != null) {//如果点击的item为最后一个
                scrollListener.scrollTo(x + 1);//向下滚动 使子布局完全展示

            }
        }

        @Override
        public void onHideChildren(ItemMaillistOrgDataJson bean) {
//            int position = getCurrentPosition(x + "");//确定当前点击的item位置
            OUserListBean children = getChildItemSubsidiaryBean(bean);//获取要展示的子布局数据对象,区分
            if (children == null) return;
            remove(x + 1);//删除
            if (scrollListener != null) {//如果点击的item为最后一个
                scrollListener.scrollTo(x);

            }
        }


    };

    //子控件item设置监听
    public interface OnChildItemClickListener {
        void OnClickListener(View view, int position);
    }

    private OnChildItemClickListener mListener;

    public void setOnChildItemClickListener(OnChildItemClickListener mListener) {
        this.mListener = mListener;

    }

    private void itemListener(View view, int position) {
        if (mListener != null) {
            mListener.OnClickListener(view, position);
        }
    }

}
