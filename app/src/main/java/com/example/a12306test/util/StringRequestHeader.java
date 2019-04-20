package com.example.a12306test.util;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.util.Log.i;

/**
 * Created by 昭阳 on 2019/4/19.
 */
public class StringRequestHeader extends StringRequest {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        StringRequestHeader.context = context;
    }

    public StringRequestHeader(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public StringRequestHeader(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        Map<String, String> headers = response.headers;
        for (String key : headers.keySet()) {
            System.out.println(key + ">>>>>go>" + headers.get(key));
        }
        if (headers.keySet().contains("Set-Cookie")) {
            String cookie = headers.get("Set-Cookie");
            String cookie_1=headers.get("Set-Cookie");
            String zhengre = "(\\w|\\=|\\.)+;";
            Pattern pattern = Pattern.compile(zhengre);
            Matcher matcher = pattern.matcher(cookie);
            String cookie_vlaues = "";
            if (matcher.find()) {
                cookie_vlaues = matcher.group();
            }
            String getkey = "(\\w|\\_)+\\b";
            Pattern keypa = Pattern.compile(getkey);
            Matcher matcher1 = keypa.matcher(cookie_1);
            String cook_key = "";

            if (matcher1.find()) {
                cook_key = matcher1.group();
            }
            CookieUtil.saveKind_cookies(context, cook_key, cookie_vlaues);
            i(cook_key, ">>>>>>>>cookie储存历史>>>>>>" + cookie_vlaues);
        }
        return super.parseNetworkResponse(response);

    }

    @Override
    public Request<?> setRequestQueue(RequestQueue requestQueue) {

        return super.setRequestQueue(requestQueue);
    }

}
