package com.shine.haoqiba.networks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shine.haoqiba.application.AppData;
import com.shine.haoqiba.networks.domain.HttpData;
import com.shine.haoqiba.networks.domain.HttpParams;
import com.shine.haoqiba.ui.activity.MainActivity;
import com.shine.haoqiba.utils.log.SLog;
import com.shine.haoqiba.utils.string.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by hmy on 2015/10/29.
 * Update by Byron on 2015/11/1.
 */
public class VolleyRequestUtils {
    /**
     * volley请求队列
     * 懒汉式单例设计模式
     */
    private static RequestQueue mQueue = null;

    public synchronized static RequestQueue obtainRequestQueue(Activity activity) {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(activity);
        }
        return mQueue;
    }

    /**
     * Map —— key：页面  value：一个页面中所有请求方法的链表
     * 贪汉式单例设计模式
     */
    private static Map<String, List<String>> requestQueueMap = new HashMap<String, List<String>>();

    public static Map<String, List<String>> obtainRequestQueueMap() {
        return requestQueueMap;
    }

    //HTTP的POST请求
    public static void httpPost(final Activity activity, HttpParams httpParams, String json, VolleyResponse volleyResponse) {
        final String url = AppData.BASE_URL + httpParams.methodTag;

        //当前网络不可用
        if (!isNetworkAvailable(activity)) {
            return;
        }

        Map<String, List<String>> requestMap = VolleyRequestUtils.obtainRequestQueueMap();

        //防止方法重复提交
        if (!StringUtils.isEmpty(httpParams.reqPageName)) {
            List<String> reqList = requestMap.get(httpParams.reqPageName);
            //请求方法链表不为空且当前请求方法存在，直接返回
            if (reqList != null && reqList.contains(httpParams.methodTag)) {
                return;
            }
            //请求方法链表为空，新建请求列表并存入当前请求方法
            if (reqList == null) {
                List<String> reqs = new LinkedList<String>();
                reqs.add(httpParams.methodTag);
                requestMap.put(httpParams.reqPageName, reqs);
            } else {
                List<String> reqs = requestMap.get(httpParams.reqPageName);
                reqs.add(httpParams.methodTag);
                requestMap.put(httpParams.reqPageName, reqs);
            }
            // LoadingUtils.showLoadingDialog(activity);
        }

        HttpData httpData = new HttpData();
        httpData.setHttpParams(httpParams);
        httpData.setJson(json);

        final String jsonString = JSON.toJSONString(httpData);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mQueue = obtainRequestQueue(activity);
        Request request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, volleyResponse, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.e(activity.getClass().getSimpleName(), "response : " + volleyError.toString());

                //SLog.dissmissLoadingDialog();
                requestQueueMap.clear();
                if (activity.getClass().getSimpleName().equals("SplashActivity")) {
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                }
                SLog.w(url);
                SLog.w(jsonString);
                Toast.makeText(activity, "服务器异常", Toast.LENGTH_SHORT).show();
            }
        });
        SLog.w(url);
        SLog.w(jsonString);
        request.setRetryPolicy(new DefaultRetryPolicy(5 * 1000, 1, 1.0f));
        mQueue.add(request);
        // mQueue.start();
    }

    /**
     * 给jsonObject设置请求参数(支持Integer,String,Boolean,Double,Map类型)
     *
     * @param jsonObject
     */
    public static JSONObject putHttpParamsToJSONObject(JSONObject jsonObject, HttpParams httpParams) {
        Field[] fields = httpParams.getClass().getFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                Class<?> type = field.getType();
                if (type == Integer.class) {
                    jsonObject.put(fieldName, (Integer) field.get(httpParams));
                }
                if (type == String.class) {
                    jsonObject.put(fieldName, (String) field.get(httpParams));
                }
                if (type == Boolean.class) {
                    jsonObject.put(fieldName, (Boolean) field.get(httpParams));
                }
                if (type == Double.class) {
                    jsonObject.put(fieldName, (Double) field.get(httpParams));
                }
                if (type == Map.class) {
                    com.alibaba.fastjson.JSONObject jsonObj = JSON.parseObject(jsonObject.toString());
                    jsonObj.putAll((Map) field.get(httpParams));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    /**
     * 判断当前网络状况
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null && cm.getActiveNetworkInfo() != null) {
            return cm.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }
}