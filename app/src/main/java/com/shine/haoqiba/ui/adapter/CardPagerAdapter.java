package com.shine.haoqiba.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.shine.haoqiba.bean.Card;
import com.shine.haoqiba.interfaces.ViewPagerClickListener;
import com.shine.haoqiba.ui.fragment.CardFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardPagerAdapter extends FragmentStatePagerAdapter {
    private List<Card> mPostList;
    private List<Fragment> mFragments = new ArrayList();
    private ViewPagerClickListener viewPagerClickListener;
    public CardPagerAdapter(FragmentManager paramFragmentManager, List<Card> paramList,ViewPagerClickListener viewPagerClickListener) {
        super(paramFragmentManager);
        this.viewPagerClickListener = viewPagerClickListener;
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
            Card localAppModel = (Card) localIterator.next();
            this.mFragments.add(CardFragment.getInstance(localAppModel, viewPagerClickListener));
        }
        this.mPostList = paramList;
    }

    public void addCardList(List<Card> cardList) {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = cardList.iterator();
        while (localIterator.hasNext())
            localArrayList.add(CardFragment.getInstance((Card) localIterator.next(), viewPagerClickListener));
        if (this.mFragments == null)
            this.mFragments = new ArrayList();
        this.mFragments.addAll(localArrayList);
        this.mPostList.addAll(cardList);
    }

    public List<Card> getCardList() {
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

    public void setCardList(List<Card> cardList) {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = cardList.iterator();
        while (localIterator.hasNext())
            localArrayList.add(CardFragment.getInstance((Card) localIterator.next(),viewPagerClickListener));
        this.mFragments = localArrayList;
        this.mPostList = cardList;
    }

    public void setFragments(List<Fragment> paramList) {
        this.mFragments = paramList;
    }
}