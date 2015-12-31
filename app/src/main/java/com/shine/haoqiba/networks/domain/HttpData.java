package com.shine.haoqiba.networks.domain;


import com.shine.haoqiba.bean.MResult;

import java.io.Serializable;

/**
 * Created by hmy on 2015/10/30.
 */
public class HttpData implements Serializable {

    private String json;
    private MResult mResult;
    private HttpParams httpParams;

    public HttpData() {
    }

    public HttpData(String json, HttpParams httpParams) {
        this.json = json;
        this.httpParams = httpParams;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public MResult getmResult() {
        return mResult;
    }

    public void setmResult(MResult mResult) {
        this.mResult = mResult;
    }

    public HttpParams getHttpParams() {
        return httpParams;
    }

    public void setHttpParams(HttpParams httpParams) {
        this.httpParams = httpParams;
    }
}
