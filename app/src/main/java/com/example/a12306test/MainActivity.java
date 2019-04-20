package com.example.a12306test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.a12306test.bean.Query_TacketBean;
import com.example.a12306test.station_pack.Station_Util;
import com.example.a12306test.util.CookieUtil;
import com.example.a12306test.util.JSonRequestHeader;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.util.Log.i;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_from;
    private EditText et_to;
    private EditText et_time;
    private Button bt_station;
    private Station_Util intaces;
    private Map<String, String> map;
    private RequestQueue requestQueue;
    private ListView lv_ticket;
    private RadioButton radio_student;
    private RadioButton radio_audit;
    private SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        Context context = MainActivity.this;
        et_from = (EditText) findViewById(R.id.et_from);
        et_to = (EditText) findViewById(R.id.et_to);
        et_time = (EditText) findViewById(R.id.et_time);
        bt_station = (Button) findViewById(R.id.bt_station);
        bt_station.setOnClickListener(this);
        Station_Util intaces = Station_Util.getIntaces();
        map = intaces.getMap();
        requestQueue = Volley.newRequestQueue(context);
        lv_ticket = (ListView) findViewById(R.id.lv_ticket);

        radio_student = (RadioButton) findViewById(R.id.radio_student);
        radio_student.setOnClickListener(this);
        radio_audit = (RadioButton) findViewById(R.id.radio_audit);
        radio_audit.setOnClickListener(this);
        simpleDateFormat = new SimpleDateFormat("hh小时mm分");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_station:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String from = et_from.getText().toString().trim();
        if (TextUtils.isEmpty(from)) {
            Toast.makeText(this, "from不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String to = et_to.getText().toString().trim();
        if (TextUtils.isEmpty(to)) {
            Toast.makeText(this, "to不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String time = et_time.getText().toString().trim();
        if (TextUtils.isEmpty(time)) {
            Toast.makeText(this, "time不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            queryData();
        }

        // TODO validate success, do something


    }

    private void queryData() {
        String from_place = et_from.getText().toString();
        String to_place = et_to.getText().toString();
        String time = et_time.getText().toString();
        String type_perosn = "ADULT";
        if (radio_audit.isChecked()) {

        } else if (radio_student.isChecked()) {
            type_perosn = "STUDENT";

        }
        String url = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=" + time.trim() + "&leftTicketDTO.from_station=" + map.get(from_place).trim() + "&leftTicketDTO.to_station=" + map.get(to_place).trim() + "&purpose_codes=" + type_perosn;

        i("from——----", "" + map.get(from_place));
        i("to——----", "" + map.get(to_place));
//        String urlnew = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2019-04-17&leftTicketDTO.from_station=CQW&leftTicketDTO.to_station=TJP&purpose_codes=ADULT";
//        String url1 = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2019-04-16&leftTicketDTO.from_station=GZQ&leftTicketDTO.to_station=TJP&purpose_codes=ADULT";
//
//        String url_2 = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2019-04-19&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=TYV&purpose_codes=ADULT";
        JSonRequestHeader request = new JSonRequestHeader(JsonObjectRequest.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                Query_TacketBean query_tacketBean = gson.fromJson(jsonObject.toString(), Query_TacketBean.class);
                i("MainActivity", "" + query_tacketBean.getData().getResult());
                List<String> result_list = query_tacketBean.getData().getResult();
                Ticket_Aadpter ticket_aadpter = new Ticket_Aadpter(result_list);
                lv_ticket.setAdapter(ticket_aadpter);
                ticket_aadpter.notifyDataSetChanged();
                for (int i = 0; i < result_list.size(); i++) {
                    String[] strings = result_list.get(i).split("\\|");


                    for (int j = 0; j < strings.length; j++) {
                        System.out.println(j + "号" + strings[j]);

                    }


                }


                Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, "" + volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return sendHeader();
            }
        };
        requestQueue.add(request);
    }

    private Map<String, String> sendHeader() {
        i("StringRequestHeader", "请求头运行");
        Map<String, String> cookie = CookieUtil.getCookie(MainActivity.this);
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

    class Ticket_Aadpter extends BaseAdapter {

        List<String> list;

        public Ticket_Aadpter(List<String> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String[] strings = list.get(position).split("\\|");
            convertView = View.inflate(MainActivity.this, R.layout.item_ticket, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.tx_carid.setText(strings[3] + "");
            viewHolder.tx_from.setText(intaces.getMap_getname().get(strings[6]) + "");
            viewHolder.tx_from_time.setText(strings[8] + "");
            viewHolder.tx_to.setText(intaces.getMap_getname().get(strings[7]) + "");
            viewHolder.tx_to_time.setText(strings[9] + "");
//            String format = simpleDateFormat.format();
            viewHolder.tx_long_time.setText(strings[10] + "");
            viewHolder.tx_car_model.setText("商务特等座:" + (!strings[32].trim().equals(null) ? strings[32] + "" : "无"));
            viewHolder.tx_car_mode2.setText("一等座:" + (!strings[31].trim().equals(null) ? strings[31] + "" : "无"));
            viewHolder.tx_car_mode3.setText("二等座:" + (!strings[30].trim().equals(null) ? strings[30] + "" : "无"));
            viewHolder.tx_car_mode4.setText("高级软卧:" + (!strings[21].trim().equals(null) ? strings[21] + "" : "无"));
            viewHolder.tx_car_mode5.setText("一等软卧:" + (!strings[23].trim().equals(null) ? strings[23] + "" : "无"));
//            viewHolder.tx_car_mode6.setText("动卧 :" + (!strings[21].trim().equals(null) ? strings[21] + "" : "无"));
            viewHolder.tx_car_mode7.setText("硬卧二等 :" + (!strings[28].trim().equals(null) ? strings[28] + "" : "无"));
//            viewHolder.tx_car_mode8.setText("软座   :" + (!strings[21].trim().equals(null) ? strings[21] + "" : "无"));
            viewHolder.tx_car_mode9.setText("硬座   :" + (!strings[29].trim().equals(null) ? strings[29] + "" : "无"));
            viewHolder.tx_car_mode10.setText("无座   :" + (!strings[26].trim().equals(null) ? strings[26] + "" : "无"));
            return convertView;
        }

        public
        class ViewHolder {
            public View rootView;
            public TextView tx_carid;
            public TextView tx_from;
            public TextView tx_from_time;
            public TextView tx_long_time;
            public TextView tx_to;
            public TextView tx_to_time;
            public TextView tx_car_model;
            public TextView tx_car_mode2;
            public TextView tx_car_mode3;
            public TextView tx_car_mode4;
            public TextView tx_car_mode5;
            public TextView tx_car_mode6;
            public TextView tx_car_mode7;
            public TextView tx_car_mode8;
            public TextView tx_car_mode9;
            public TextView tx_car_mode10;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.tx_carid = (TextView) rootView.findViewById(R.id.tx_carid);
                this.tx_from = (TextView) rootView.findViewById(R.id.tx_from);
                this.tx_from_time = (TextView) rootView.findViewById(R.id.tx_from_time);
                this.tx_long_time = (TextView) rootView.findViewById(R.id.tx_long_time);
                this.tx_to = (TextView) rootView.findViewById(R.id.tx_to);
                this.tx_to_time = (TextView) rootView.findViewById(R.id.tx_to_time);
                this.tx_car_model = (TextView) rootView.findViewById(R.id.tx_car_model);
                this.tx_car_mode2 = (TextView) rootView.findViewById(R.id.tx_car_mode2);
                this.tx_car_mode3 = (TextView) rootView.findViewById(R.id.tx_car_mode3);
                this.tx_car_mode4 = (TextView) rootView.findViewById(R.id.tx_car_mode4);
                this.tx_car_mode5 = (TextView) rootView.findViewById(R.id.tx_car_mode5);
                this.tx_car_mode6 = (TextView) rootView.findViewById(R.id.tx_car_mode6);
                this.tx_car_mode7 = (TextView) rootView.findViewById(R.id.tx_car_mode7);
                this.tx_car_mode8 = (TextView) rootView.findViewById(R.id.tx_car_mode8);
                this.tx_car_mode9 = (TextView) rootView.findViewById(R.id.tx_car_mode9);
                this.tx_car_mode10 = (TextView) rootView.findViewById(R.id.tx_car_mode10);
            }

        }
    }
}
