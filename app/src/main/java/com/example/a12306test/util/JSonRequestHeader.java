package com.example.a12306test.util;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.util.Log.i;

/**
 * Created by 昭阳 on 2019/4/19.
 */
public class JSonRequestHeader extends JsonObjectRequest {
    private static Context context;

    public JSonRequestHeader(int method, String url, String requestBody, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    public JSonRequestHeader(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    public JSonRequestHeader(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public JSonRequestHeader(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    public JSonRequestHeader(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
    }


    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        JSonRequestHeader.context = context;
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        Map<String, String> headers = response.headers;
        for (String key : headers.keySet()) {
            System.out.println(key + ">>>>>go>" + headers.get(key));
        }
        if (headers.keySet().contains("Set-Cookie")) {
            String cookie = headers.get("Set-Cookie");
            String zhengre = "(\\w|\\=)+;";
            Pattern pattern = Pattern.compile(zhengre);
            Matcher matcher = pattern.matcher(cookie);
            String cookie_vlaues = "";
            while (matcher.find()) {
                cookie_vlaues = matcher.group();
            }
            String getkey = "\\_\\w+\\_\\w+";
            Pattern keypa = Pattern.compile(getkey);
            Matcher matcher1 = keypa.matcher(cookie);
            String cook_key = "";
            while (matcher1.find()) {
                cook_key = matcher1.group();
            }
            CookieUtil.saveKind_cookies(context, cook_key, cookie_vlaues);
            i(cook_key, ">>>>>>>>cookie历史>>>>>>" + cookie_vlaues);
        }
        return super.parseNetworkResponse(response);

    }

    @Override
    public Request<?> setRequestQueue(RequestQueue requestQueue) {

        return super.setRequestQueue(requestQueue);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
//        i("StringRequestHeader", "请求头运行");
//        Map<String, String> cookie = CookieUtil.getCookie(context);
//        StringBuffer stringBuffer = new StringBuffer();
//        for (String key_1 : cookie.keySet()) {
//            stringBuffer.append(cookie.get(key_1));
//            i(key_1, ">>>>>>>>cookie读取>>>>>>" + cookie.get(key_1));
//        }
//        i("StringRequestHeader", "" + stringBuffer);
//        return cookie;
        return super.getParams();
    }
}
