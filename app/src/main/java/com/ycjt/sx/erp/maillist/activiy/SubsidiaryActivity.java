package com.ycjt.sx.erp.maillist.activiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.ycjt.sx.R;
import com.ycjt.sx.base.BaseActivity;

public class SubsidiaryActivity extends BaseActivity {


    private RecyclerView rlv_subsidiary;

    @Override
    protected void initView() {

        rlv_subsidiary = (RecyclerView) findViewById(R.id.rlv_subsidiary);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_subsidiary;
    }
}
