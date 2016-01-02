package com.shine.haoqiba.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.shine.haoqiba.R;
import com.shine.haoqiba.bean.MResult;
import com.shine.haoqiba.networks.domain.HttpParams;
import com.shine.haoqiba.ui.activity.BaseFragmentActivity;
import com.special.ResideMenu.ResideMenu;

/**
 * Created by Administrator on 2015/12/31.
 */
public class CuriosityCommunityFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2<ListView> {

    @ViewInject(R.id.xlv_curiosity_community)
    private PullToRefreshListView xListView;
    @ViewInject(R.id.btn_side_menu_or_back)
    private Button btn_side_menu_or_back;

    @Override
    protected View initViews(LayoutInflater paramLayoutInflater) {
        return paramLayoutInflater.inflate(R.layout.activity_curiosity_community, null);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void enhanceOnResponse(String Tag, String json, MResult result, HttpParams params) {

    }

    @Override
    protected void initActions(View view) {
        ViewUtils.inject(this, view);
        //initSlideMenu();
        xListView.setOnRefreshListener(this);
        xListView.setAdapter(new MyListAdapter());
        //getResideMenu().addIgnoredView(xListView);
        //getResideMenu().setShadowVisible(false);

        btn_side_menu_or_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_side_menu_or_back) {
            ((BaseFragmentActivity)getActivity()).getResideMenu().openMenu(ResideMenu.DIRECTION_LEFT);
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        Toast.makeText(getActivity(), "onPullDownToRefresh", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        Toast.makeText(getActivity(), "onPullUpToRefresh", Toast.LENGTH_SHORT).show();
    }

    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return "aaaa";
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.list_item_curiosity_community, null);
            }
            return convertView;
        }
    }


}
