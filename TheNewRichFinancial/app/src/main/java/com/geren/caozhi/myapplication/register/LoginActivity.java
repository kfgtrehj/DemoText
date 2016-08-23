package com.geren.caozhi.myapplication.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geren.caozhi.myapplication.BaseActivity;
import com.geren.caozhi.myapplication.R;
import com.geren.caozhi.myapplication.activity.MainActivity;
/**
 * Created by Administrator on 2016/8/19.
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{
    SharedPreferences sp = null;
    EditText user = null;
    EditText password;
    TextView textView;  //我要注册
    private CheckBox checkboxButton;
    private Button login;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        initWindow(R.color.white);
        setContentView(R.layout.login_view);

        imageview = (ImageView) findViewById(R.id.imagemain);  //返回到主页面按钮
        textView = (TextView) findViewById(R.id.my_register);
        imageview.setOnClickListener(this);
        textView.setOnClickListener(this);

        //SharedPreferences 的存储实现
        sp = this.getSharedPreferences("userinfo" , Context.MODE_PRIVATE);
        init();
    }
     public void init(){
         user = (EditText) findViewById(R.id.user);             //用户名
         password = (EditText) findViewById(R.id.password);   //用户密码
         checkboxButton = (CheckBox) findViewById(R.id.checkBoxLogin); //记住密码
         login = (Button) findViewById(R.id.login);           //登录按钮
         if (sp.getBoolean("checkboxBoolean", false)){
             user.setText(sp.getString("user","")); //admin nu
             password.setText(sp.getString("password",""));
             checkboxButton.setChecked(true);
         }
         login.setOnClickListener(this);
     }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.imagemain:  //返回到主页面
                intent.setClass(LoginActivity.this,MainActivity.class);
                //首先我们在LoginActivity的跳转事件里面给mainactivity传个名为id的参数
                //然后在mainactivity里面我们接收一下这个id值
                intent.putExtra("id", 2);
                startActivity(intent);
                break;
            case R.id.my_register:   //前往我要注册页面
                intent.setClass(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
       }

        if (v == login){
            String name = user.getText().toString();
            String pswd = password.getText().toString();
            //判断是否输入了账号和密码
            if (name.trim().equals("")){
                Toast.makeText(this,"请您输入用户名！", Toast.LENGTH_SHORT).show();
                return;
            }
            if (pswd.trim().equals("")){
                Toast.makeText(this,"请您输入密码！", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean checkBoxLogin = checkboxButton.isChecked();
            //按钮被种中，下次进入时会显示账号和密码
            if (checkBoxLogin){
           SharedPreferences.Editor editor = sp.edit();
                editor.putString("user", "");
                editor.putString("password", "");
                editor.putBoolean("checkboxBoolean",true);
                editor.commit();
            }else{           //按钮被选中，清空账号和密码，下次进入时会显示账号和密码
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("user", "");
                editor.putString("password", "");
                editor.putBoolean("checkboxBoolean",false);
                editor.commit();
            }
            intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
