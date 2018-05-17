package com.ycjt.sx.login.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ycjt.sx.R;
import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.app.YCJTApplication;
import com.ycjt.sx.base.BaseActivity;
import com.ycjt.sx.base.MainActivity;
import com.ycjt.sx.login.adapter.OrganizationSelectorAdapter;
import com.ycjt.sx.widget.recycleline.DividerItemDecoration;
import com.ycjt.sx.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ycjt.sx.app.InterfaceConfig.APP_ORG_ID;

/**
 * 组织机构选择页
 */
public class OrganizationActivity extends BaseActivity {

    private RecyclerView rlvOrganizationSelector;

    @Override
    protected void initView() {
        tvTopModdel.setText("选择组织机构");
        ibTopRight.setVisibility(View.GONE);
        ivFalse.setVisibility(View.VISIBLE);
        ibTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefUtils.putString(OrganizationActivity.this, GlobalConstants.USER_NAME, "");
                PrefUtils.putString(OrganizationActivity.this, GlobalConstants.PASS_WORD, "");
                Intent intent = new Intent(OrganizationActivity.this, LoginActivity.class);
                YCJTApplication.exit();
                startActivity(intent);
                finish();
            }
        });
        ArrayList<String> mList = getIntent().getStringArrayListExtra(GlobalConstants.SELECTOR_ORG);
        rlvOrganizationSelector = (RecyclerView) findViewById(R.id.rlv_organization_selector);
        setData(mList);

    }

    /**
     * 设置数据
     */
    private void setData(List<String> mList) {
        rlvOrganizationSelector.setLayoutManager(new LinearLayoutManager(this));
        OrganizationSelectorAdapter adapter = new OrganizationSelectorAdapter(this, mList);
        rlvOrganizationSelector.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rlvOrganizationSelector.setAdapter(adapter);
        adapter.setOnItemClickListener(new OrganizationSelectorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //请求服务器,获取首页数据.获取到数据,直接将数据传递给首页
                String item = mList.get(position);
                String[] split = item.split("::");
                PrefUtils.putString(OrganizationActivity.this, APP_ORG_ID, split[1]);
                PrefUtils.putString(OrganizationActivity.this, GlobalConstants.PERSONAL_CENTER, split[0]);
                Intent intent = new Intent(OrganizationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_organization;
    }
}
