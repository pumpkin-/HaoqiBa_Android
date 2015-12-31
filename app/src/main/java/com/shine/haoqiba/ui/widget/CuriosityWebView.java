package com.shine.haoqiba.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by Administrator on 2015/12/30.
 */
public class CuriosityWebView extends WebView {

    /**
     * 重新webview
     *
     * @author paoyx
     */
    ScrollInterface web;

    public CuriosityWebView(Context context) {
        super(context);
    // TODO Auto-generated constructor stub
    }

    public CuriosityWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CuriosityWebView(Context context, AttributeSet attrs) {

        super(context, attrs);
    // TODO Auto-generated constructor stub
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //Log.e("hhah",""+l+" "+t+" "+oldl+" "+oldt);
        web.onScrollChanged(l, t, oldl, oldt);
    }

    public void setOnCustomScroolChangeListener(ScrollInterface t) {
        this.web = t;
    }

    /**
     * 定义滑动接口
     */
    public interface ScrollInterface {
        public void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}
