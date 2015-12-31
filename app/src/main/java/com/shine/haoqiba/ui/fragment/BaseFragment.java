package com.shine.haoqiba.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shine.haoqiba.bean.MResult;
import com.shine.haoqiba.networks.VolleyRequestUtils;
import com.shine.haoqiba.networks.VolleyResponse;
import com.shine.haoqiba.networks.VolleyResponseUtils;
import com.shine.haoqiba.networks.domain.HttpParams;
import com.shine.haoqiba.utils.log.SLog;
import com.shine.haoqiba.utils.string.StringUtils;
import com.special.ResideMenu.ResideMenu;

import org.json.JSONObject;

import java.util.List;

/**
 * User: huangruimin
 * Date: 2014-12-13
 * Time: 19:43
 * Description:
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener, VolleyResponse {

    //侧滑菜单
    ResideMenu resideMenu;

    protected Handler mHandler = new Handler();

    //初始化动作
    protected abstract void initActions(View paramView);

    //初始化数据
    protected abstract void initData();

    //初始化布局
    protected abstract View initViews(LayoutInflater paramLayoutInflater);

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
        View localView = initViews(paramLayoutInflater);
        initActions(localView);
        return localView;
    }

    public void finish() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    /**
     * 增强onResponse方法
     *
     * @param Tag
     * @param json
     * @param result
     * @param params
     */
    public abstract void enhanceOnResponse(String Tag, String json, MResult result, HttpParams params);

    /**
     * 服务器响应
     *
     * @param jsonObject
     */
    @Override
    public void onResponse(JSONObject jsonObject) {
        SLog.w(jsonObject.toString());
        if (jsonObject == null) {
            Toast.makeText(getActivity(), "请求服务器失败", Toast.LENGTH_SHORT).show();
        } else {
            //缓冲圈处理
            HttpParams httpParams = VolleyResponseUtils.getHttpParams(jsonObject);
            MResult mResult = VolleyResponseUtils.getMResult(jsonObject);
            //若请求页面不为空
            if (!StringUtils.isEmpty(httpParams.reqPageName)) {
                //将当前请求页面的请求方法链表中的当前请求方法移除
                List<String> reqlist = VolleyRequestUtils.obtainRequestQueueMap().get(httpParams.reqPageName);
                reqlist.remove(httpParams.methodTag);
                //若当前请求页面的请求方法链表为空则隐藏缓冲圈
                if (reqlist.size() <= 0) {
                    //LoadingUtils.dissmissLoadingDialog();
                } else {
                    VolleyRequestUtils.obtainRequestQueueMap().put(httpParams.reqPageName, reqlist);
                }
            }
            //回调
            enhanceOnResponse(VolleyResponseUtils.getTag(jsonObject),
                    VolleyResponseUtils.getHttpData(jsonObject).getJson(),
                    mResult,
                    httpParams);
        }
    }

}
