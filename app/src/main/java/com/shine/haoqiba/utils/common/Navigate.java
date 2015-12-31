package com.shine.haoqiba.utils.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.shine.haoqiba.R;
import com.shine.haoqiba.application.AppData;
import com.shine.haoqiba.bean.CuriosityCard;
import com.shine.haoqiba.ui.activity.CuriosityDetailsActivity;
import com.shine.haoqiba.ui.activity.MainActivity;
import com.shine.haoqiba.ui.fragment.BaseFragment;
import com.shine.haoqiba.ui.fragment.DailyCuriosityFragment;

import java.io.Serializable;


/**
 * Created by hmy on 2015/11/16.
 */
public class Navigate {

    //从Activity置换容器
    public static void startFragmentFromActivity(FragmentActivity fromActivity, BaseFragment toFragment, int layout_id, Serializable fragment_data) {
        if(fragment_data != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppData.EXTRAS_DATA, fragment_data);
            toFragment.setArguments(bundle);
        }
        FragmentManager fm = fromActivity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(toFragment.getClass().getSimpleName());
        if (fragment == null) {
            fm.beginTransaction()
                    .setCustomAnimations(R.anim.faded_in, R.anim.faded_out).
                    add(layout_id, toFragment, toFragment.getClass().getSimpleName())
                    .commit();
        }
    }

    //从Fragment置换容器
    public static void startFragmentFromActivity(BaseFragment fromFragment, BaseFragment toFragment, int layout_id, Serializable fragment_data) {
        if(fragment_data != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppData.EXTRAS_DATA, fragment_data);
            toFragment.setArguments(bundle);
        }
        FragmentManager fm = fromFragment.getActivity().getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(toFragment.getClass().getSimpleName());
        if (fragment == null) {
            fm.beginTransaction()
                    .setCustomAnimations(R.anim.faded_in, R.anim.faded_out).
                    add(layout_id, toFragment, toFragment.getClass().getSimpleName()).
                    addToBackStack(fromFragment.getClass().getSimpleName())
                    .commit();
        }
    }

    //从Activity跳转到Activity
    public static void startActivityFromActivity(FragmentActivity fromActivity, Class toActivity, Serializable data) {
        Intent intent = new Intent(fromActivity, toActivity);
        if(data != null) {
            intent.putExtra(AppData.EXTRAS_DATA, data);
        }
       fromActivity.startActivity(intent);
    }

    //跳转至首页
    public static void startMain(Activity fromActivity) {
        Intent intent = new Intent(fromActivity, MainActivity.class);
        fromActivity.startActivity(intent);
    }

    public static void startMain(BaseFragment fromFragment) {
        Intent intent = new Intent(fromFragment.getActivity(), MainActivity.class);
        fromFragment.startActivity(intent);
    }

    //跳转每日一奇页
    public static void startDailyCuriosityFragment(FragmentActivity fromFragment) {
        DailyCuriosityFragment toFragment = new DailyCuriosityFragment();
        if (fromFragment instanceof  MainActivity) {
            //startActivityFromActivity(fromFragment, MainActivity.class, null);
            startFragmentFromActivity(fromFragment, toFragment, R.id.frameLayout, null);
        } else {
            startActivityFromActivity(fromFragment, MainActivity.class, null);
        }
    }

    //跳转每日一奇详情页
    public static void startCuriosityDetailsActivity(FragmentActivity fromActivity, CuriosityCard curiosityCard) {
        startActivityFromActivity(fromActivity, CuriosityDetailsActivity.class, curiosityCard);
    }

//    public static void startMicroClassActivity(BaseFragment fromFragment) {
//        Intent intent = new Intent(fromFragment.getActivity(), MicroClassActivity.class);
//        fromFragment.startActivity(intent);
//    }




}
