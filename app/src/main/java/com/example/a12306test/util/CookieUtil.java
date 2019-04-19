package com.example.a12306test.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by 昭阳 on 2019/4/17.
 */
public class CookieUtil  {

    public static void saveKind_cookies(Context context, String key, String string) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("cookie1", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, string);
        edit.commit();
    }

    public static Map<String,String> getCookie(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("cookie1", Context.MODE_PRIVATE);
//        i("CookieUtil_saveCookie", "" + sharedPreferences.getString("set_cookie", "无cookie"));
        return (Map<String, String>) sharedPreferences.getAll();
    }
}
