package com.shine.haoqiba.application;

/**
 * 全局常量
 * Created by hmy on 2015/11/6.
 */
public class AppData {

    //fragment bundle�еĲ���
    public static final String EXTRAS_DATA = "extras_data";

    //自动登录
    public static final int AUTOLOGIN_NO = 0;
    public static final int AUTOLOGIN_YES = 1;

    //注册
    public static final int REGISTER = 2;
    //重置密码
    public static final int RESETPASSWORD = 3;

    //请求路径
    public static final String BASE_URL = "http://192.168.0.106:8080/wedding";

    /**
     * 用户请求相关----------------------------------------------------
     */
    public static final String USER_REQ_DOUSERLOGIN = "/user/operation/userLogin.action";
    public static final String USER_REQ_DOUSERREGISTERORRESETPASSWORD = "/user/operation/userRegisterOrResetPassword.action";
    public static final String USER_REQ_DOUPDATEUSERINFO = "/user/operation//updateUserInfo.action";
    public static final String USER_REQ_DOGETUSERINFO = "/user/operation/getUserInfo.action";
    public static final String USER_REQ_DOGETUSERBYUSERNAME = "/user/operation/getUserByUsername.action";

    /**
     * 文件上传相关-----------------------------------------------------
     */
    public static final String USER_REQ_DOAVATARUPLOAD = "/file/operation/avatarUpload.action";

}
