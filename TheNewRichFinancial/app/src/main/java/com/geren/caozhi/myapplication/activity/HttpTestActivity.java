package com.geren.caozhi.myapplication.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geren.caozhi.myapplication.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HttpTestActivity extends AppCompatActivity {

    private Button requestBtn;
    private TextView responseText;

    final int RESPONSE = 0x01;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case RESPONSE:
                    String response = (String)msg.obj;
                    responseText.setText(response);
                    break;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_test);
        requestBtn = (Button) findViewById(R.id.requestBtn);
        responseText = (TextView) findViewById(R.id.responseText);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestWithHttpClient();
            }
        });
    }

    private void sendRequestWithHttpClient(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                //创建一个HTTP 客户端 用于发起HTTP请求
                HttpClient httpCient = new DefaultHttpClient();
                //创建一个Get请求 url  = www.baidu.com
                HttpGet httpGet = new HttpGet("http://www.baidu.com");

                try {
                    HttpResponse httpResponse = httpCient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity,"utf-8");
                        Message message = new Message();
                        message.what = RESPONSE;
                        message.obj = response.toString();
                        handler.sendMessage(message);
                    }

                }catch (Exception e){

                }
            }
        }.start();
    }

}
