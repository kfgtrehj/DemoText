package com.geren.caozhi.myapplication.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.geren.caozhi.myapplication.R;
import com.geren.caozhi.myapplication.bean.BorrowIndex;
import com.geren.caozhi.myapplication.bean.BorrowPage;
import com.geren.caozhi.myapplication.bean.Page;
import com.geren.caozhi.myapplication.bean.UpdateBean;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpTestActivity extends AppCompatActivity {

    private Button requestBtn,postRequest;
    private TextView responseText ,
            version, //版本号
            message, //信息
            downloadUrl; // 下载地址
    private EditText account,password;


    final int RESPONSE = 0x01;
    final int RESPONSE2 = 0x02;
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
                case RESPONSE2:
                    BorrowIndex response2 = (BorrowIndex) msg.obj;
                    System.out.println("顶层的数据" + "status = "
                            +response2.getStatus() + "isFirst = "
                            + response2.getIsFirst() + "子级 = "
                            + response2.getBorrowPage().getClass().getName()
                            + "\n" );
                    System.out.println("borrowPage---->" + "pageSize = "
                            +response2.getBorrowPage().getPageSize() + "pageNum = "
                            + response2.getBorrowPage().getPageNum() + "array 大小  = "
                            + response2.getBorrowPage().getPageArray().size() + "数组展开一行 = "
                            + "\n" );
                    System.out.println("page---->" + "borrow = "
                            +response2.getBorrowPage().getPageArray().get(0).getBorrowWay() + "audiTime = "
                            + response2.getBorrowPage().getPageArray().get(0).auditTime + "最底层结束"
                            + "\n" );

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
        postRequest = (Button) findViewById(R.id.postRequest);
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sendRequestWithHttpClient();
                getBorrowIndexPage();
            }
        });
        postRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataToPostRequest();
            }
        });
    }

    private void getDataToPostRequest() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                //创建一个HTTP客户端
                HttpClient httpClient = new DefaultHttpClient();
                //创建一个post 请求
                HttpPost httpPost = new HttpPost("https://m.sunfobank.com/user/app/login");
                //创建一个集合， 集合对象中存放的是BasicNmaeValuePair 对象（map）
                List<NameValuePair> formParams = new ArrayList<NameValuePair>();
                formParams.add(new BasicNameValuePair("account", account.getText().toString()));
                formParams.add(new BasicNameValuePair("password", password.getText().toString()));
                formParams.add(new BasicNameValuePair("errorCount", "0"));
                formParams.add(new BasicNameValuePair("imgCodeKey", "loginUser"));
                formParams.add(new BasicNameValuePair("code", ""));

                //创建一个把一个实体编码成url 形式
                UrlEncodedFormEntity uefEntity;
                try {
                    //改变编码格式
                    uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
                    //为这个Post请求，提供一个表单
                    httpPost.setEntity(uefEntity);

                    //返回结果集。 通过httpClient execute 方法
                    HttpResponse response = httpClient.execute(httpPost);
                    try {
                        HttpEntity entity = response.getEntity();
                        if (entity != null) {
                            System.out.println("--------------------------------------");
                            System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                            System.out.println("--------------------------------------");
                        }
                    } finally {
//                        response.close();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }.start();
    }


    private void getBorrowIndexPage(){
//
        new Thread(){
            @Override
            public void run() {
                super.run();
                //创建一个HTTP 客户端 用于发起HTTP请求
                HttpClient httpCient = new DefaultHttpClient();
                //创建一个Get请求 url  = www.baidu.com
                HttpGet httpGet = new HttpGet("https://m.sunfobank.com/borrow/app/1");



                try {
                    HttpResponse httpResponse = httpCient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity,"utf-8");
                        Message message = new Message();
                        //第一步，把一个String数据，变成一个Json 对象
                        JSONObject jsonObject = new JSONObject(response);
                        //创建一个空数组
                        ArrayList<Page> pages = new ArrayList<Page>();
                        //为空的数组，循环增加一个Page对象
                        for(int i = 0; i<jsonObject.getJSONObject("borrowsPage").getJSONArray("page").length(); i++){
                            //创建一个空的Page对象
                            Page page = new Page();
                            //从json对象中，依靠层级关系，取出响应的 数据. 这里，先取一个整数数据，他是，第i个数据里的内容
                            int borrow = jsonObject.getJSONObject("borrowsPage").getJSONArray("page").getJSONObject(i).getInt("borrowWay");
                            //为空的page 添加数据。
                            page.setBorrowWay(borrow);
                            page.auditTime = jsonObject.getJSONObject("borrowsPage").getJSONArray("page").getJSONObject(i).getString("auditTime");
                            //page 填充完毕后，把该页，添加进数组里。
                            pages.add(page);
                        }
                        //创建一个borrowPage 对象
                        BorrowPage borrowPage = new BorrowPage();
                        //把数组装进 borrowPage
                        borrowPage.setPageArray(pages);
                        //装 pageSize
                        borrowPage.setPageSize(jsonObject.getJSONObject("borrowsPage").getInt("pageSize"));
                        //装pageNum
                        borrowPage.setPageNum(jsonObject.getJSONObject("borrowsPage").getInt("pageNum"));
                        //创建顶层 borrowIndex
                        BorrowIndex borrowIndex = new BorrowIndex();

                        //装数据
                        borrowIndex.setStatus(jsonObject.getInt("status"));
                        borrowIndex.setIsFirst(jsonObject.getInt("isFirst"));
                        borrowIndex.setBorrowPage(borrowPage);

                        message.what = RESPONSE2;
                        message.obj = borrowIndex;
                        //sendMessage 是一个方法。 他的作用是，可以发送一条"消息"。
                        handler.sendMessage(message);

                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();

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
                        //sendMessage 是一个方法。 他的作用是，可以发送一条"消息"。
                        handler.sendMessage(message);

                    }

                }catch (Exception e){

                }
            }
        }.start();
    }



}
