package com.ycjt.sx.base;

import android.support.v4.util.SparseArrayCompat;

import com.ycjt.sx.erp.agency.fragment.AgencyFragment;
import com.ycjt.sx.erp.agency.fragment.childfragment.ChildCompleteFragment;
import com.ycjt.sx.erp.agency.fragment.childfragment.ChildSendFragment;
import com.ycjt.sx.erp.agency.fragment.childfragment.ChildWaitFragment;
import com.ycjt.sx.erp.maillist.fragment.MaillistFragment;
import com.ycjt.sx.erp.message.fragment.MessageFragment;
import com.ycjt.sx.erp.personal.fragment.PersonalFragment;
import com.ycjt.sx.erp.workbench.fragment.WorkBenchFragment;
import com.ycjt.sx.erp.workbench.fragment.WorkBenchPurchasingStatistics;
import com.ycjt.sx.erp.workbench.fragment.WorkBenchSalesStatistics;

public class FragmentFactory {
    private static final String TAG = "FragmentFactory";

    static SparseArrayCompat<BaseFragment> map = new SparseArrayCompat<BaseFragment>();//
    static SparseArrayCompat<BaseFragment> mapChild = new SparseArrayCompat<BaseFragment>();
    static SparseArrayCompat<BaseFragment> mapChildWorkBench = new SparseArrayCompat<BaseFragment>();

    /**
     * 主页的标签
     *
     * @param position
     * @return
     */
    public static BaseFragment getFragment(int position) {

        BaseFragment fragment = map.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new AgencyFragment();
                    break;
                case 1:
                    fragment = new MessageFragment();
                    break;
                case 2:
                    fragment = new WorkBenchFragment();
                    break;
                case 3:
                    fragment = new MaillistFragment();
                    break;
                case 4:
                    fragment = new PersonalFragment();
                    break;
                default:
                    break;
            }
            map.put(position, fragment);
        }

        return fragment;

    }

    public static BaseFragment getChildFragment(int position) {
        BaseFragment fragmentChild = mapChild.get(position);
        if (fragmentChild == null) {
            switch (position) {
                case 0:
                    fragmentChild = new ChildWaitFragment();
                    break;
                case 1:
                    fragmentChild = new ChildCompleteFragment();
                    break;
                case 2:
                    fragmentChild = new ChildSendFragment();
                    break;

                default:
                    break;
            }
            mapChild.put(position, fragmentChild);
        }

        return fragmentChild;

    }

    public static BaseFragment getChildFragmentWorkBench(int position) {
        BaseFragment fragmentChild = mapChildWorkBench.get(position);
        if (fragmentChild == null) {
            switch (position) {
                case 0:
                    fragmentChild = new WorkBenchSalesStatistics();
                    break;
                case 1:
                    fragmentChild = new WorkBenchPurchasingStatistics();
                    break;

                default:
                    break;
            }
            mapChildWorkBench.put(position, fragmentChild);
        }

        return fragmentChild;
    }

    // 移除所有
    public static void removeAllFragment() {
        if (map != null && map.size() > 0) {
            map.clear();
        }
        if (mapChild != null && mapChild.size() > 0) {
            mapChild.clear();
        }
        if (mapChildWorkBench != null && mapChildWorkBench.size() > 0) {
            mapChildWorkBench.clear();
        }
    }

    // 拿到列表
    public static SparseArrayCompat<BaseFragment> getListFragment() {
        if (map != null && map.size() > 0) {
            return map;
        }

        return null;
    }

    public static SparseArrayCompat<BaseFragment> getChildListFragment() {

        if (mapChild != null && mapChild.size() > 0) {
            return mapChild;
        }
        return null;
    }

    public static SparseArrayCompat<BaseFragment> getChildListFragmentWorkBench() {

        if (mapChildWorkBench != null && mapChildWorkBench.size() > 0) {
            return mapChildWorkBench;
        }
        return null;
    }
}
