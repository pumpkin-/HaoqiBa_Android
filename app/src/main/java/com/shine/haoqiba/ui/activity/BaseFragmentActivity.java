package com.shine.haoqiba.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.shine.haoqiba.R;
import com.shine.haoqiba.utils.common.Navigate;
import com.special.ResideMenu.ResideMenu;

/**
 * Created by hmy on 2015/10/27.
 * Update By Byron on 2015/10/31
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener {
    //侧滑菜单
    ResideMenu resideMenu;

    //每日一奇,互奇社区,收藏,设置,加入我们
    private LinearLayout ll_daily_surprise;
    private LinearLayout ll_community;
    private LinearLayout ll_favouriate;
    private LinearLayout ll_setting;
    private LinearLayout ll_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View view = initView();
        setContentView(view);
        initData();
        initActions(view);
    }
    protected abstract View initView();
    protected abstract void initData();
    protected abstract void initActions(View view);

    //初始化侧滑菜单
    protected void initSlideMenu() {
        // attach to current activity;
        View view = View.inflate(this, R.layout.slide_leftmenu, null);
        resideMenu = new ResideMenu(this, R.layout.slide_leftmenu);
        resideMenu.attachToActivity(this);
        resideMenu.setUse3D(true);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        resideMenu.setShadowVisible(true);
        resideMenu.setBackgroundColor(getResources().getColor(R.color.blue));
        // add gesture operation's ignored views
        View ignored_view = (View.inflate(this, R.layout.fragment_haoqiba, null)).findViewById(R.id.pager);
        resideMenu.addIgnoredView(ignored_view);
        resideMenu.setOnClickListener(this);

        //初始化侧滑菜单的控件
        //每日一奇
        ll_daily_surprise = (LinearLayout) resideMenu.getLeftMenuView().findViewById(R.id.ll_daily_surprise);
        ll_daily_surprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.closeMenu();
                Navigate.startDailyCuriosityFragment(BaseFragmentActivity.this);
            }
        });
        //互奇社区
        ll_community = (LinearLayout) resideMenu.getLeftMenuView().findViewById(R.id.ll_community);
        ll_community.setOnClickListener(this);
        //我的收藏
        ll_favouriate = (LinearLayout) resideMenu.getLeftMenuView().findViewById(R.id.ll_favouriate);
        ll_favouriate.setOnClickListener(this);
        //设置
        ll_setting = (LinearLayout) resideMenu.getLeftMenuView().findViewById(R.id.ll_setting);
        ll_setting.setOnClickListener(this);
        //加入我们
        ll_join = (LinearLayout) resideMenu.getLeftMenuView().findViewById(R.id.ll_join);
        ll_join.setOnClickListener(this);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

//    @Override
//    public void overridePendingTransition(int enterAnim, int exitAnim) {
//        super.overridePendingTransition(R.anim.faded_in, R.anim.faded_out);
//    }
}
