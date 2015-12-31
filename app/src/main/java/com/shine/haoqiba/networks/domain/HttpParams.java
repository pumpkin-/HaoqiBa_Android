package com.shine.haoqiba.networks.domain;

import java.io.Serializable;

/**
 * Created by hmy on 2015/10/30.
 */
public class HttpParams implements Serializable {

    public String reqPageName;//当前请求fragment或activity的simpleName
    public String methodTag;//当前请求方法的tag

    public HttpParams() {
    }

    /**
     * @param reqPageName 当前请求fragment或activity的simpleName
     * @param methodTag   当前请求方法的tag
     */
    public HttpParams(String reqPageName, String methodTag) {
        this.reqPageName = reqPageName;
        this.methodTag = methodTag;
    }
}
