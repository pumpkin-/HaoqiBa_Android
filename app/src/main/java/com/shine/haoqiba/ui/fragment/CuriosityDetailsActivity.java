package com.shine.haoqiba.ui.fragment;

import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.pulltozoomview.PullToZoomScrollViewEx;
import com.shine.haoqiba.R;
import com.shine.haoqiba.application.AppData;
import com.shine.haoqiba.bean.CuriosityCard;
import com.shine.haoqiba.ui.activity.BaseFragmentActivity;
import com.shine.haoqiba.utils.AppUtils;

/**
 * Created by Administrator on 2015/12/30.
 */
public class CuriosityDetailsActivity extends BaseFragmentActivity {

    //控件
    private PullToZoomScrollViewEx webview_PullToZoomScrollView;
    private ImageView iv_introduce_img;
    private WebView webview_curiosity_detail;
    private ProgressBar pb_loading_state;

    private ImageView iv_back;

    //数据
    private CuriosityCard curiosityCard;

    @Override
    protected View initView() {
        View view = View.inflate(this,R.layout.fragment_curiosity_pulltozoomscrollviewex, null);
        iv_back = (ImageView) view.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        webview_PullToZoomScrollView = (PullToZoomScrollViewEx) view.findViewById(R.id.webview_PullToZoomScrollViewEx);
        return view;
    }

    @Override
    protected void initData() {
        curiosityCard = (CuriosityCard)getIntent().getExtras().getSerializable(AppData.EXTRAS_DATA);
        // Toast.makeText(getActivity(), curiosityCard.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initActions(View paramView) {
        initZoomScrollView();
        initWebView();
    }

    //初始化zoom ScrollView
    private void initZoomScrollView() {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
       getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        webview_PullToZoomScrollView.setHeaderLayoutParams(localObject);

        View zoomView = View.inflate(this, R.layout.layout_pulltozoomscrollviewex_zoom, null);
        iv_introduce_img = (ImageView) zoomView.findViewById(R.id.iv_introduce_img);
        //ImageLoader.getInstance().displayImage(curiosityCard.getCoverImgerUrl(), iv_introduce_img); TODO
        iv_introduce_img.setImageResource(AppUtils.getDrawableIdByName(this, curiosityCard.getCoverImgerUrl()));
        webview_PullToZoomScrollView.setZoomView(zoomView);

        View contentView = View.inflate(this, R.layout.layout_pulltozoomscrollviewex_content, null);
        webview_curiosity_detail = (WebView) contentView.findViewById(R.id.webview_curiosity_detail);
        pb_loading_state = (ProgressBar) contentView.findViewById(R.id.pb_loading_state);
        webview_PullToZoomScrollView.setScrollContentView(contentView);

        //View headerView = View.inflate(this, R.layout.layout_pulltozoomscrollviewex_header, null);
        //webview_PullToZoomScrollView.setHeaderView(headerView);

    }

    private void initWebView() {
        //进度条
        webview_curiosity_detail.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    pb_loading_state.setVisibility(View.GONE);
                } else {
                    if (View.INVISIBLE == pb_loading_state.getVisibility()) {
                        pb_loading_state.setVisibility(View.VISIBLE);
                    }

                    pb_loading_state.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

        //启用支持javascript
        WebSettings settings = webview_curiosity_detail.getSettings();
        settings.setJavaScriptEnabled(true);
        //TODO
        //Map<String, String> map = new HashMap<String, String>();
        //map.put("id", action + "");
        //webview_curiosity_detail.loadUrl(AppData.BASE_URL + AppData.MICROCLASS_REQ_DOARTICLEDETAIL, map);

        //WebView加载web资源
        webview_curiosity_detail.loadUrl("https://www.baidu.com/");

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webview_curiosity_detail.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        //回退
        if (v == iv_back) {
            //Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
            finish();
            //就Navigate.startDailyCuriosityFragment(getActivity());
        }
    }


}
