package com.shine.haoqiba.ui.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shine.haoqiba.R;
import com.shine.haoqiba.bean.CuriosityCard;
import com.shine.haoqiba.bean.MResult;
import com.shine.haoqiba.interfaces.ViewPagerClickListener;
import com.shine.haoqiba.networks.domain.HttpParams;
import com.shine.haoqiba.ui.activity.MainActivity;
import com.shine.haoqiba.utils.AppUtils;

public class CardFragment extends BaseFragment {
    protected CuriosityCard mCuriosityCard;

    protected TextView mAuthorText;
    protected ImageView mBottomEdgeImageView;
    protected TextView mBravoNumText;
    protected RelativeLayout mCardLayout;
    protected ImageView mCoverImageView;
    protected TextView mDigestText;
    protected TextView mSubTitleText;
    protected TextView mTitleText;

    public static CardFragment getInstance(CuriosityCard curiosityCard, ViewPagerClickListener viewPagerClickListener) {
        CardFragment localCardFragment = new CardFragment();
        localCardFragment.setViewPagerClickListener(viewPagerClickListener);
        Bundle localBundle = new Bundle();
        localBundle.putSerializable("curiosityCard", curiosityCard);
        localCardFragment.setArguments(localBundle);
        return localCardFragment;
    }


    protected View initViews(LayoutInflater paramLayoutInflater) {
        View view = paramLayoutInflater.inflate(R.layout.fragment_card, null);
        mCardLayout = ((RelativeLayout) view.findViewById(R.id.box_card));
        mBottomEdgeImageView = ((ImageView) view.findViewById(R.id.image_bottom_edge));
        mCoverImageView = ((ImageView) view.findViewById(R.id.image_cover));
        mCoverImageView.setOnClickListener(this);
        mTitleText = ((TextView) view.findViewById(R.id.text_title));
        mSubTitleText = ((TextView) view.findViewById(R.id.text_subtitle));
        mDigestText = ((TextView) view.findViewById(R.id.text_digest));
        mDigestText.setOnClickListener(this);
        mAuthorText = ((TextView) view.findViewById(R.id.text_author));
        mBravoNumText = ((TextView) view.findViewById(R.id.text_bravos));

        mTitleText.setText(this.mCuriosityCard.getTitle());
        mSubTitleText.setText(this.mCuriosityCard.getSubTitle());
        this.mBravoNumText.setText("  " + this.mCuriosityCard.getUpNum());
        this.mDigestText.setText(mCuriosityCard.getDigest());
        this.mAuthorText.setText(Html.fromHtml("<B>" + this.mCuriosityCard.getAuthorName() + "</B>"));
        this.mAuthorText.setOnClickListener(this);
        if (getActivity() instanceof MainActivity) {
            resideMenu = ((MainActivity) getActivity()).getResideMenu();
            resideMenu.addIgnoredView(mDigestText);
            resideMenu.addIgnoredView(mAuthorText);
        }
        initAndDisplayCoverImage();

        return view;
    }

    @Override
    public void enhanceOnResponse(String Tag, String json, MResult result, HttpParams params) {

    }

    protected void initAndDisplayCoverImage() {
        int coverWidth = AppUtils.getScreenDisplayMetrics(getActivity()).widthPixels - 2 * getResources().getDimensionPixelSize(R.dimen.card_margin);
        int coverHeight = (int) (180.0F * (coverWidth / 320.0F));
        ViewGroup.LayoutParams localLayoutParams = this.mCoverImageView.getLayoutParams();
        localLayoutParams.height = Float.valueOf(coverHeight).intValue();
        //加载图片 TODO
        int picResource = AppUtils.getDrawableIdByName(getActivity(), mCuriosityCard.getCoverImgerUrl());
        mCoverImageView.setBackgroundResource(picResource);
    }

    protected void initData() {
        this.mCuriosityCard = (CuriosityCard) getArguments().getSerializable("curiosityCard");
    }

    protected void initActions(View paramView) {
    }

    public void onDestroy() {
        this.mCoverImageView.setImageBitmap(null);
        super.onDestroy();
    }

    public void onDestroyView() {
        this.mCoverImageView.setImageBitmap(null);
        super.onDestroyView();
    }

    private ViewPagerClickListener viewPagerClickListener;

    public void setViewPagerClickListener(ViewPagerClickListener viewPagerClickListener) {
        this.viewPagerClickListener = viewPagerClickListener;
    }

    @Override
    public void onClick(View v) {
        if (v == mDigestText || v == mCoverImageView || v == mAuthorText) {
            //Toast.makeText(getActivity(), "digest", Toast.LENGTH_SHORT).show();
            viewPagerClickListener.onPagerClick();
        }
    }
}