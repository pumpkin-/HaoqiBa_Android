package com.shine.haoqiba.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.shine.haoqiba.bean.CuriosityCard;
import com.shine.haoqiba.interfaces.ViewPagerClickListener;
import com.shine.haoqiba.ui.fragment.CardFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardPagerAdapter extends FragmentStatePagerAdapter {
    private List<CuriosityCard> mPostList;
    private List<Fragment> mFragments = new ArrayList();
    private ViewPagerClickListener viewPagerClickListener;
    public CardPagerAdapter(FragmentManager paramFragmentManager, List<CuriosityCard> paramList,ViewPagerClickListener viewPagerClickListener) {
        super(paramFragmentManager);
        this.viewPagerClickListener = viewPagerClickListener;
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
            CuriosityCard localAppModel = (CuriosityCard) localIterator.next();
            this.mFragments.add(CardFragment.getInstance(localAppModel, viewPagerClickListener));
        }
        this.mPostList = paramList;
    }

    public void addCardList(List<CuriosityCard> curiosityCardList) {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = curiosityCardList.iterator();
        while (localIterator.hasNext())
            localArrayList.add(CardFragment.getInstance((CuriosityCard) localIterator.next(), viewPagerClickListener));
        if (this.mFragments == null)
            this.mFragments = new ArrayList();
        this.mFragments.addAll(localArrayList);
        this.mPostList.addAll(curiosityCardList);
    }

    public List<CuriosityCard> getCardList() {
        return this.mPostList;
    }

    public int getCount() {
        return this.mFragments.size();
    }

    public List<Fragment> getFragments() {
        return this.mFragments;
    }

    public Fragment getItem(int paramInt) {
        return this.mFragments.get(paramInt);
    }

    public void setCardList(List<CuriosityCard> curiosityCardList) {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = curiosityCardList.iterator();
        while (localIterator.hasNext())
            localArrayList.add(CardFragment.getInstance((CuriosityCard) localIterator.next(),viewPagerClickListener));
        this.mFragments = localArrayList;
        this.mPostList = curiosityCardList;
    }

    public void setFragments(List<Fragment> paramList) {
        this.mFragments = paramList;
    }
}