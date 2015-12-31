package com.shine.haoqiba.ui.activity;

import android.view.View;

import com.shine.haoqiba.R;
import com.shine.haoqiba.utils.common.Navigate;
import com.special.ResideMenu.ResideMenu;


public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {

    //主页
    @Override
    protected View initView() {
        return View.inflate(this, R.layout.activity_main, null);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initActions(View view) {
        initSlideMenu();
        Navigate.startDailyCuriosityFragment(this);
    }

    @Override
    public void onClick(View v) {
    }

    public ResideMenu getResideMenu() {
        return resideMenu;
    }

}
