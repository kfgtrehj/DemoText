package com.geren.caozhi.myapplication.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geren.caozhi.myapplication.R;
import com.geren.caozhi.myapplication.bean.UpdateBean;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpTestActivity extends AppCompatActivity {

    private Button requestBtn;
    private TextView responseText ,
            version, //版本号
            message, //信息
            downloadUrl; // 下载地址


    final int RESPONSE = 0x01;
    //线程之间的通信
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case RESPONSE:
                    UpdateBean response = (UpdateBean) msg.obj;
//                    responseText.setText("");
                    if(response.getStatus() != 0){
                        version.setText(response.getVersionCode());
                        message.setText(response.getMessage());
                        downloadUrl.setText(response.getDownloadUrl());
                    }else {
                        responseText.setText("当前已经是最新版本");
                    }

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
        version = (TextView) findViewById(R.id.version);
        message = (TextView) findViewById(R.id.message);
        downloadUrl = (TextView) findViewById(R.id.downloadUrl);

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
                HttpGet httpGet = new HttpGet("https://m.sunfobank.com/ver/android/3.1.3");

                try {
                    HttpResponse httpResponse = httpCient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity,"utf-8");
                        Message message = new Message();
                        message.what = RESPONSE;
//                        message.obj = response.toString();

                        //对服务器传来的 json 文本进行解析
                        //1创建一个Json 对象  把整个服务器传来的json 都放入 json 对象中
                        JSONObject jsonObject = new JSONObject(response);
                        //创建一个实体对象
                        UpdateBean updateBean = new UpdateBean();
                        //吧json对象中的 key 为 status 属性，映射到 对象 updateBean 里面的 status 属性中。
                        updateBean.setStatus(jsonObject.getInt("status"));
                        updateBean.setVersionCode(jsonObject.getString("version"));
                        updateBean.setDownloadUrl(jsonObject.getString("downloadUrl"));
                        updateBean.setMessage(jsonObject.getString("message"));
                        //
                        message.obj = updateBean;
                        handler.sendMessage(message);

                    }

                }catch (Exception e){

                }
            }
        }.start();
    }



}
