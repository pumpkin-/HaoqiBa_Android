package com.shine.haoqiba.utils.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.shine.haoqiba.R;
import com.shine.haoqiba.application.AppData;
import com.shine.haoqiba.bean.CuriosityCard;
import com.shine.haoqiba.ui.fragment.BaseFragment;
import com.shine.haoqiba.ui.fragment.CuriosityCommunityFragment;
import com.shine.haoqiba.ui.fragment.CuriosityDetailsActivity;
import com.shine.haoqiba.ui.fragment.DailyCuriosityFragment;

import java.io.Serializable;


/**
 * Created by hmy on 2015/11/16.
 */
public class Navigate {

    //当前可视fragment
    private static Fragment currVisiableFragment = null;

    //跳转每日一奇页
    public static void startDailyCuriosityFragment(FragmentActivity fromFragment) {
        DailyCuriosityFragment toFragment = new DailyCuriosityFragment();
        //startActivityFromActivity(fromFragment, MainActivity.class, null);
        startFragmentFromActivity(fromFragment, toFragment, R.id.frameLayout, null);

    }

    //跳转每日一奇详情页
    public static void startCuriosityDetailsActivity(FragmentActivity fromFragment, CuriosityCard curiosityCard) {
        startActivityFromActivity(fromFragment, CuriosityDetailsActivity.class, curiosityCard);
    }

    //跳转互奇社区
    public static void startCuriosityCommunityFragment(FragmentActivity fromActivity) {
        CuriosityCommunityFragment toFragment = new CuriosityCommunityFragment();
        startFragmentFromActivity(fromActivity, toFragment, R.id.frameLayout, null);
    }

    //从Activity跳转到fragment
    public static void startFragmentFromActivity(FragmentActivity fromActivity, BaseFragment toFragment, int layout_id, Serializable fragment_data,
                                                 boolean isAddToBackTrace, boolean isAnimation) {
        if (fragment_data != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppData.EXTRAS_DATA, fragment_data);
            toFragment.setArguments(bundle);
        }
        FragmentManager fm = fromActivity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragmentByTag = fm.findFragmentByTag(toFragment.getClass().getSimpleName());
        if(isAddToBackTrace) {
            transaction.addToBackStack(null);
        }
        if (isAnimation) {
            transaction.setCustomAnimations(R.anim.faded_in, R.anim.faded_in, R.anim.faded_out, R.anim.faded_out);
        }
        //没有添加
        if (fragmentByTag == null) {
            if (currVisiableFragment != null) {
                transaction.hide(currVisiableFragment);
            }
            //setCustomAnimations(R.anim.faded_in, R.anim.faded_out).
            //.addToBackStack(lastVisiableFragment.getClass().getSimpleName())
            transaction.add(layout_id, toFragment, toFragment.getClass().getSimpleName())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            currVisiableFragment = toFragment;
        } else {
            if (currVisiableFragment != null && !currVisiableFragment.getClass().getSimpleName().equals(toFragment.getClass().getSimpleName()))
                transaction.hide(currVisiableFragment).show(fragmentByTag).commit();
            currVisiableFragment = fragmentByTag;
        }
    }
    //从Activity跳转到fragment
    public static void startFragmentFromActivity(FragmentActivity fromActivity, BaseFragment toFragment, int layout_id, Serializable fragment_data) {
        if (fragment_data != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppData.EXTRAS_DATA, fragment_data);
            toFragment.setArguments(bundle);
        }
        FragmentManager fm = fromActivity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragmentByTag = fm.findFragmentByTag(toFragment.getClass().getSimpleName());
        //没有添加
        if (fragmentByTag == null) {
            if (currVisiableFragment != null) {
                transaction.hide(currVisiableFragment);
            }
            //setCustomAnimations(R.anim.faded_in, R.anim.faded_out).
            //.addToBackStack(lastVisiableFragment.getClass().getSimpleName())
            transaction.add(layout_id, toFragment, toFragment.getClass().getSimpleName())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            currVisiableFragment = toFragment;
        } else {
            if (currVisiableFragment != null && !currVisiableFragment.getClass().getSimpleName().equals(toFragment.getClass().getSimpleName()))
                transaction.hide(currVisiableFragment).show(fragmentByTag).commit();
            currVisiableFragment = fragmentByTag;
        }

    }
    //从Activity跳转到Activity
    public static void startActivityFromActivity(FragmentActivity fromActivity, Class toActivity, Serializable data) {
        Intent intent = new Intent(fromActivity, toActivity);
        if (data != null) {
            intent.putExtra(AppData.EXTRAS_DATA, data);
        }
        fromActivity.startActivity(intent);
    }
}
