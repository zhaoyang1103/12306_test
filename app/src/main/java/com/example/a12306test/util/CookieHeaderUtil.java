package com.example.a12306test.util;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.util.Log.i;

/**
 * Created by 昭阳 on 2019/4/19.
 */
public class CookieHeaderUtil extends StringRequest {
    private JSONObject jsonObject = new JSONObject();
    private Response.Listener<JSONObject> mListener;
    private String cook_key;
    private Context context;
    private Map<String, String> map = new HashMap<>();
    private String cookieName = "";
    public CookieHeaderUtil(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        Map<String, String> headers = response.headers;
        i("CookieHeaderUtil", "尺寸" + headers.size());
        for (String key : headers.keySet()) {
            i(">>>>>()" + key, "" + headers.get(key));

        }
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            JSONObject jsonObject = new JSONObject(jsonString);
            if (headers.keySet().contains("Set-Cookie")) {
                String cookie = headers.get("Set-Cookie");
                String zhengre = "(\\w|\\=)+(?=(?:\\;))";
                Pattern pattern = Pattern.compile(zhengre);
                Matcher matcher = pattern.matcher(cookie);
                while (matcher.find()) {
                    cookie = matcher.group();
                }
                jsonObject.put("Cookie", cookie);
                i("返回cookie", "" + cookie);
                return Response.success(jsonObject.toString(), HttpHeaderParser.parseCacheHeaders(response));
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
//        Map cookie = CookieUtil.getCookie(context);
////        StringBuffer stringBuffer = new StringBuffer();
////        for (Object key : cookie.keySet()) {
////            stringBuffer.append(cookie.get(key).toString());
////        }
////
////        map.put("")
        return map;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    private void setCookie(String cookie) {
        map.put("Cookie", cookie);
    }
}
