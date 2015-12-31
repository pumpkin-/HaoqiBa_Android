package com.shine.haoqiba.networks;

import com.alibaba.fastjson.JSON;
import com.shine.haoqiba.bean.MResult;
import com.shine.haoqiba.networks.domain.HttpData;
import com.shine.haoqiba.networks.domain.HttpParams;

import org.json.JSONObject;

/**
 * Created by hmy on 2015/10/31.
 */
public class VolleyResponseUtils {

    /**
     * 获取onResponse中返回的HttpData
     *
     * @param jsonObject
     * @return
     */
    public static HttpData getHttpData(JSONObject jsonObject) {
        HttpData data = JSON.parseObject(jsonObject.toString(), HttpData.class);
        return data;
    }

    /**
     * 解析HttpData中的json
     *
     * @param jsonObject
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getObject(JSONObject jsonObject, Class<T> clazz) {
        HttpData data = JSON.parseObject(jsonObject.toString(), HttpData.class);
        T t = JSON.parseObject(data.getJson(), clazz);
        return t;
    }

    /**
     * 解析json
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getObject(String json, Class<T> clazz) {
        T data = JSON.parseObject(json, clazz);
        return data;
    }

    /**
     * 获取HttpData中的HttpParams
     *
     * @param jsonObject
     * @return
     */
    public static HttpParams getHttpParams(JSONObject jsonObject) {
        HttpData data = JSON.parseObject(jsonObject.toString(), HttpData.class);
        return data.getHttpParams();
    }

    /**
     *获取HttpData中的MResult
     *
     * @param jsonObject
     * @return
     */
    public static MResult getMResult(JSONObject jsonObject) {
        HttpData data = JSON.parseObject(jsonObject.toString(), HttpData.class);
        return data.getmResult();
    }

    /**
     * 获取HttpParams中的methodTag
     *
     * @param jsonObject
     */
    public static String getTag(JSONObject jsonObject) {
        HttpData data = JSON.parseObject(jsonObject.toString(), HttpData.class);
        return data.getHttpParams().methodTag;
    }
}
