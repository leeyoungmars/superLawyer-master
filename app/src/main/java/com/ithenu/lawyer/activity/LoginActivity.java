package com.ithenu.lawyer.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.ithenu.lawyer.R;
import com.ithenu.lawyer.bean.LawyerInfo;
import com.ithenu.lawyer.utils.Utils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;


public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText mEditUser, mEditPassword;
    private Button mBtnLogin, mBtnreg;
    private SharedPreferences mPre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPre = getSharedPreferences("lawyerInfo",MODE_PRIVATE);
        mEditUser = (EditText) findViewById(R.id.et_username);
        mEditPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.login_btn);
        mBtnreg = (Button) findViewById(R.id.reg_btn);
        mBtnLogin.setOnClickListener(this);
        mBtnreg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                login();
                break;
            case R.id.reg_btn:
                startActivity(new Intent(this, RegOneActivity.class));
                break;
        }
    }

    private void login() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("phone", mEditUser.getText().toString());
        params.addBodyParameter("passWord", mEditPassword.getText().toString());
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
                Utils.url + "lawyer_login",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        Utils.Toast("登陆成功");
                        //Gson gson = new Gson();
                        //LawyerInfo lawyerInfo = gson.fromJson(responseInfo.result, LawyerInfo.class);
                        mPre.edit().putString("Json",responseInfo.result).commit();
                        finish();
                        System.out.println("responseInfo:" + responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        System.out.println("HttpException:" + e + "---" + s);
                        Utils.Toast("登陆失败");
                        //startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        //finish();
                    }

                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
