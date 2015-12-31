package com.shine.haoqiba.networks.ApiUtils;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.shine.haoqiba.application.App;
import com.shine.haoqiba.application.AppData;
import com.shine.haoqiba.bean.User;
import com.shine.haoqiba.networks.VolleyRequestUtils;
import com.shine.haoqiba.networks.VolleyResponse;
import com.shine.haoqiba.networks.domain.HttpParams;
import com.shine.haoqiba.utils.string.StringUtils;

import java.util.Date;

/**
 * 用户请求
 * Created by hmy on 2015/10/30.
 */
public class UserRequestUtils {

    /**
     * 用户登录
     *
     * @param activity
     * @param reqPageName
     * @param username
     * @param password
     * @param volleyResponse
     */
    public static void doUserLogin(Activity activity, String reqPageName, String username, String password, VolleyResponse volleyResponse) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(StringUtils.getMD5Str(password));

        String json = JSON.toJSONString(user);

        HttpParams httpParams = new HttpParams(reqPageName, AppData.USER_REQ_DOUSERLOGIN);
        VolleyRequestUtils.httpPost(activity, httpParams, json, volleyResponse);
    }

    /**
     * 用户注册/重置密码
     *
     * @param activity
     * @param reqPageName
     * @param phoneNumber
     * @param password
     * @param volleyResponse
     */
    public static void doUserRegisterOrResetPassword(Activity activity, String reqPageName, String phoneNumber, String password, VolleyResponse volleyResponse) {

        User user = new User();
        user.setId(App.USER.getId());
        user.setUsername(phoneNumber);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(StringUtils.getMD5Str(password));
        user.setNickName(phoneNumber);

        String json = JSON.toJSONString(user);

        HttpParams httpParams = new HttpParams(reqPageName, AppData.USER_REQ_DOUSERREGISTERORRESETPASSWORD);
        VolleyRequestUtils.httpPost(activity, httpParams, json, volleyResponse);
    }

    /**
     * 用户信息修改
     *
     * @param activity
     * @param reqPageName
     * @param id
     * @param nickName
     * @param realName
     * @param gender
     * @param age
     * @param marriageDate
     * @param hometown
     * @param signature
     * @param volleyResponse
     */
    public static void doUserInfoUpdate(Activity activity, String reqPageName, Integer id, String nickName, String realName, Integer gender, Integer age, Date marriageDate, String hometown, String signature, VolleyResponse volleyResponse) {

        User user = App.USER;
        if (user == null) {
            user = new User();
        }
        user.setId(id);
        user.setNickName(nickName);
        user.setRealName(realName);
        user.setGender(gender);
        user.setAge(age);
        user.setMarriageDate(marriageDate);
        user.setHometown(hometown);
        user.setSignature(signature);

        String json = JSON.toJSONString(user);

        HttpParams httpParams = new HttpParams(reqPageName, AppData.USER_REQ_DOUPDATEUSERINFO);
        VolleyRequestUtils.httpPost(activity, httpParams, json, volleyResponse);
    }

    /**
     * 通过id获取用户信息
     *
     * @param activity
     * @param reqPageName
     * @param id
     * @param volleyResponse
     */
    public static void doGetUserInfo(Activity activity, String reqPageName, Integer id, VolleyResponse volleyResponse) {

        String json = JSON.toJSONString(id);

        HttpParams httpParams = new HttpParams(reqPageName, AppData.USER_REQ_DOGETUSERINFO);
        VolleyRequestUtils.httpPost(activity, httpParams, json, volleyResponse);
    }

    /**
     * 通过username获取用户信息
     *
     * @param activity
     * @param reqPageName
     * @param username
     * @param volleyResponse
     */
    public static void doGetUserByUsername(Activity activity, String reqPageName, String username, VolleyResponse volleyResponse) {

        String json = JSON.toJSONString(username);

        HttpParams httpParams = new HttpParams(reqPageName, AppData.USER_REQ_DOGETUSERBYUSERNAME);
        VolleyRequestUtils.httpPost(activity, httpParams, json, volleyResponse);
    }

}
