package com.shine.haoqiba.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.shine.haoqiba.R;
import com.shine.haoqiba.ui.fragment.CardViewPagerFragment;
import com.special.ResideMenu.ResideMenu;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    //侧滑菜单
    ResideMenu resideMenu;
    //主页
    CardViewPagerFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // attach to current activity;
        View view = View.inflate(this, R.layout.slide_leftmenu, null);
        resideMenu = new ResideMenu(this, R.layout.slide_leftmenu);
        resideMenu.attachToActivity(this);
        resideMenu.setUse3D(true);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        resideMenu.setShadowVisible(false);
        resideMenu.setBackgroundColor(getResources().getColor(R.color.blue));
        // add gesture operation's ignored views
        View ignored_view =  (View.inflate(this,R.layout.fragment_niceapp, null)).findViewById(R.id.pager);
        resideMenu.addIgnoredView(ignored_view);

        fragment = CardViewPagerFragment.getInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, fragment);
        transaction.commit();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.e("手势移动", ev.getX()+"");
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN: {
//                if (ev.getX() < 20) {
//                    return resideMenu.dispatchTouchEvent(ev);
//                } else {
//                    return super.dispatchTouchEvent(ev);
//                }
//            }
//        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
    }

    public ResideMenu getResideMenu() {
        return resideMenu;
    }

}
