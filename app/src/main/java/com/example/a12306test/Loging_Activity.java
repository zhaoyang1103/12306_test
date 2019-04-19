package com.example.a12306test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.a12306test.bean.GetImageUrl;
import com.example.a12306test.bean.Xy_Util;
import com.example.a12306test.util.CookieUtil;
import com.example.a12306test.util.JSonRequestHeader;
import com.example.a12306test.util.StringRequestHeader;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.util.Log.i;

public class Loging_Activity extends AppCompatActivity implements View.OnClickListener {
    private String getImage_url = "";
    private EditText ed_username;
    private EditText ed_password;
    private ImageView im_code;
    private Button bt_login;
    private RequestQueue requestQueue;
    private GetImageUrl getImageUrl;
    private Button bt_resh;
    private Button bt_intent_main;
    private String image_change;
    private String user_login_utl;
    private CheckBox check_1;
    private CheckBox check_2;
    private CheckBox check_3;
    private CheckBox check_4;
    private CheckBox check_5;
    private CheckBox check_6;
    private CheckBox check_7;
    private CheckBox check_8;
    private CheckBox[] checkBoxes;
    private List<String> xy_list;
    private String image_url = "";
    private String get_image_json;
    private String first_page;
    private Bitmap bitmap;
    private static final String TAG = "Loging_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging_);
        initView();

    }

    private void initView() {
//        CookieUtil.setContext(Loging_Activity.this);
        StringRequestHeader.setContext(Loging_Activity.this);
        JSonRequestHeader.setContext(Loging_Activity.this);
        ed_username = (EditText) findViewById(R.id.ed_username);
        ed_password = (EditText) findViewById(R.id.ed_password);
        im_code = (ImageView) findViewById(R.id.im_code);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        xy_list = new ArrayList<>();
        xy_list.add(Xy_Util.getXy_1());
        xy_list.add(Xy_Util.getXy_2());
        xy_list.add(Xy_Util.getXy_3());
        xy_list.add(Xy_Util.getXy_4());
        xy_list.add(Xy_Util.getXy_5());
        xy_list.add(Xy_Util.getXy_6());
        xy_list.add(Xy_Util.getXy_7());
        xy_list.add(Xy_Util.getXy_8());
        requestQueue = Volley.newRequestQueue(Loging_Activity.this);
//        getImage_url = "https://kyfw.12306.cn/passport/captcha/captcha-image64?login_site=E&module=login&rand=sjrand";
        first_page = "https://www.12306.cn/index/";
        get_image_json = "https://kyfw.12306.cn/passport/captcha/captcha-image64";
        user_login_utl = "https://kyfw.12306.cn/otn/resources/login.html";
        image_change = "https://kyfw.12306.cn/passport/captcha/captcha-image?login_site=E&module=login&rand=sjrand";



      /*  try {
            showImage();
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }*/
        bt_resh = (Button) findViewById(R.id.bt_resh);
        bt_resh.setOnClickListener(this);
        bt_intent_main = (Button) findViewById(R.id.bt_intent_main);
        bt_intent_main.setOnClickListener(this);
        im_code.setOnClickListener(this);
//        getCookie();
//        getLoginCookie();
        check_1 = (CheckBox) findViewById(R.id.check_1);
        check_1.setOnClickListener(this);
        check_2 = (CheckBox) findViewById(R.id.check_2);
        check_2.setOnClickListener(this);
        check_3 = (CheckBox) findViewById(R.id.check_3);
        check_3.setOnClickListener(this);
        check_4 = (CheckBox) findViewById(R.id.check_4);
        check_4.setOnClickListener(this);
        check_5 = (CheckBox) findViewById(R.id.check_5);
        check_5.setOnClickListener(this);
        check_6 = (CheckBox) findViewById(R.id.check_6);
        check_6.setOnClickListener(this);
        check_7 = (CheckBox) findViewById(R.id.check_7);
        check_7.setOnClickListener(this);
        check_8 = (CheckBox) findViewById(R.id.check_8);
        check_8.setOnClickListener(this);
        checkBoxes = new CheckBox[]{check_1, check_2, check_3, check_4, check_5, check_6, check_7, check_8};
//        getLoginCookie();
        getFirst_cookie();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            im_code.setImageBitmap(bitmap);
            super.handleMessage(msg);
        }
    };

    private void getFirst_cookie() {
        final StringRequestHeader requestHeader = new StringRequestHeader(StringRequestHeader.Method.GET, first_page, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                i("Loging_Activity", "网页记载成功");
                getLogin_cookie();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
//                i("StringRequestHeader", "请求头运行");
//                Map<String, String> cookie = CookieUtil.getCookie(Loging_Activity.this);
//                StringBuffer stringBuffer = new StringBuffer();
//                for (String key_1 : cookie.keySet()) {
//                    stringBuffer.append(cookie.get(key_1));
//                    i(key_1, ">>>>>>>>cookie读取>>>>>>" + cookie.get(key_1));
//                }
//                i("StringRequestHeader", "" + stringBuffer);
//                Map<String,String> fincookie=new HashMap<>();
//                fincookie.put("Cookie",stringBuffer.toString());
                return sendHeader();

            }
        };

        requestQueue.add(requestHeader);
    }

    private void getLogin_cookie() {
        final StringRequestHeader requestHeader = new StringRequestHeader(StringRequestHeader.Method.GET, user_login_utl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                i("Loging_Activity", "登录网页记载成功");
                getImageJson();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
//                i("StringRequestHeader", "请求头运行");
//                Map<String, String> cookie = CookieUtil.getCookie(Loging_Activity.this);
//                StringBuffer stringBuffer = new StringBuffer();
//                for (String key_1 : cookie.keySet()) {
//                    stringBuffer.append(cookie.get(key_1));
//                    i(key_1, ">>>>>>>>cookie读取>>>>>>" + cookie.get(key_1));
//                }
//                i("StringRequestHeader", "" + stringBuffer);
//                Map<String,String> fincookie=new HashMap<>();
//                fincookie.put("Cookie",stringBuffer.toString());
                return sendHeader();

            }
        };

        requestQueue.add(requestHeader);
    }

    private Map<String, String> sendHeader() {
        i("StringRequestHeader", "请求头运行");
        Map<String, String> cookie = CookieUtil.getCookie(Loging_Activity.this);
        StringBuffer stringBuffer = new StringBuffer();
        for (String key_1 : cookie.keySet()) {
            stringBuffer.append(cookie.get(key_1));
            i(key_1, ">>>>>>>>cookie读取>>>>>>" + cookie.get(key_1));
        }
        i("StringRequestHeader", "" + stringBuffer);
        Map<String, String> fincookie = new HashMap<>();
        fincookie.put("Cookie", stringBuffer.toString());
        return fincookie;
    }

    private void getImageJson() {
        JSonRequestHeader jSonRequestHeader = new JSonRequestHeader(JSonRequestHeader.Method.GET, get_image_json, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject jsonObject) {
                Gson gson = new Gson();
                GetImageUrl getImageUrl = gson.fromJson(jsonObject.toString(), GetImageUrl.class);
//                im_code.setImageURI(Uri.parse("data:image/jpg;base64,"+getImageUrl.getImage()));
                showImage(getImageUrl.getImage());
                i("\"最终数据显示\"", "终点" + getImageUrl.getImage().length() + ">>" + getImageUrl.getImage());
                i("我是图片", "终点" + getImageUrl.getImage().length() + ">>" + getImageUrl.getImage().length());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return sendHeader();
            }
        };
        requestQueue.add(jSonRequestHeader);


    }

    private void showImage(String url_1) {
        byte[] decode = Base64.decode(url_1.getBytes(), Base64.DEFAULT);

        this.bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        handler.sendEmptyMessage(0);
    }

    private void showImage_1(String image) {
        ImageRequest imageRequest = new ImageRequest("data:image/jpg;jpg;base64," + image, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                i("Loging_Activity", "图片已经显示");
                im_code.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i(TAG, "onErrorResponse: " + volleyError);

                Toast.makeText(Loging_Activity.this, "" + volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(imageRequest);
//
    }

    //    private void getCookie() {
//        RequestQueue requestQueue = Volley.newRequestQueue(Loging_Activity.this);
//
//        CookieUtil cookieUtil = new CookieUtil(StringRequest.Method.GET, image_change, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
////                CookieUtil.saveCookie(Loging_Activity.this, CookieUtil.getCook());
////                String cookie = CookieUtil.getCookie(Loging_Activity.this);
////                i("最终的cook", "" + cookie);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        });
////        CookieHandler.setDefault(cookieUtil);
//        requestQueue.add(cookieUtil);
//    }
//
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                submit();
                break;
            case R.id.bt_resh:
                getImageJson();
                break;
            case R.id.bt_intent_main:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.im_code:
//                float x = v.get();
//                float y = v.getScaleY();
//                i("图片点击位置", ""+x+">>>"+y);
                break;
        }
    }

    //
    private void submit() {
        // validate
        String username = ed_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "username不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = ed_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "password不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            checkData();
        }


    }


    //
//    private void showImage() throws AuthFailureError {
//
//
//        ImageRequest imageRequest = new ImageRequest(image_change, new Response.Listener<Bitmap>() {
//            @Override
//            public void onResponse(Bitmap bitmap) {
//                im_code.setImageBitmap(bitmap);
////                getCookie();
//
//            }
//        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        });
//
//        requestQueue.add(imageRequest);
//
//    }
//
//
//    private void getImaqgeUrl_true() {
////        JsonObjectRequest request=new JsonObjectRequest()
//    }
//
//
    private void checkData() {
        StringBuffer stringBuffer_xy = new StringBuffer();

        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isChecked()) {
                stringBuffer_xy.append(xy_list.get(i) + ",");
            }
        }

        String xy_zhenfgze = "(\\w|,)+\\b";
        Pattern compile = Pattern.compile(xy_zhenfgze);
        Matcher matcher = compile.matcher(stringBuffer_xy);
        String reslut_xy = "";
        while (matcher.find()) {
            reslut_xy = matcher.group();
            System.out.println(reslut_xy);
        }


        i("坐标以此显示", "" + stringBuffer_xy.toString());
        String check_code_url_2 = "https://kyfw.12306.cn/passport/captcha/captcha-check";
        //验证 验证码  发送cookeie 得到相应的cookie
        String check_code_url = "https://kyfw.12306.cn/passport/captcha/captcha-check?login_site=E&rand=sjrand&answer=" + reslut_xy;


        JSonRequestHeader jsonObjectRequest = new JSonRequestHeader(JsonObjectRequest.Method.GET, check_code_url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    String result_message = jsonObject.getString("result_message");
                    int arocde = jsonObject.getInt("result_code");
                    Toast.makeText(Loging_Activity.this, "" + result_message + "?????", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(Loging_Activity.this, "" + arocde, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return sendHeader();
            }
        };
        requestQueue.add(jsonObjectRequest);

    }
//
//
//    private void getLoginCookie() {
//        RequestQueue requestQueue = Volley.newRequestQueue(Loging_Activity.this);
//        CookieUtil cookieUtil = new CookieUtil(StringRequest.Method.GET, "https://kyfw.12306.cn/otn/resources/login.html", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        });
////        CookieHandler.setDefault(cookieUtil);
//        requestQueue.add(cookieUtil);
//    }
//
//
//    private void loginCookeie() {
////        new
//    }

//}
}