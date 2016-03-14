package com.ithenu.lawyer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by provate on 2016/3/9.
 */
public class RegThreeActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnRegNext,mBtnRegUp;
    private Intent intent;
    private EditText mPersonProfile;
    private Bundle mBundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_three);
        init();
        mBundle = getIntent().getBundleExtra("bundleExtra");

    }

    private void init() {
        mBtnRegNext = (Button) findViewById(R.id.ToTwoButton);
        mBtnRegUp = (Button) findViewById(R.id.reg_thre_back_button);
        mBtnRegNext.setOnClickListener(this);
        mBtnRegUp.setOnClickListener(this);
        mPersonProfile = (EditText) findViewById(R.id.Reg_jianjie);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ToTwoButton:

               /* if(TextUtils.isEmpty(mPersonProfile.getText().toString())){
                    //Utils.Toast("请填写您的个人简介不少于100字");
                }else {*/
                mBundle.putString("Lprofile", mPersonProfile.getText().toString());
                RequestParams params = new RequestParams();
                params.addBodyParameter("lawyerInfo", toJosn(mBundle));
                HttpUtils http = new HttpUtils();
                http.send(HttpRequest.HttpMethod.POST,
                        Utils.url + "lawyer_register",
                        params,
                        new RequestCallBack<String>() {
                            @Override
                            public void onSuccess(ResponseInfo<String> responseInfo) {
                                intent = new Intent(RegThreeActivity.this, LoginActivity.class);
                                startActivity(intent);
                                System.out.println("responseInfo:" + responseInfo.result);
                            }

                            @Override
                            public void onFailure(HttpException e, String s) {
                                System.out.println("HttpException:" + e + "---" + s);
                            }

                        });


                    /*try {
                        URL url = new URL(Utils.url+ "lawyer_register");
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setReadTimeout(5000);
                        //添加post请求的两行属性
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        conn.setRequestProperty("Content-Length", toJosn(mBundle).length()+"");
                        //设置打开输出流
                        conn.setDoOutput(true);
                        //拿到输出流
                        OutputStream os = conn.getOutputStream();
                        //使用输出流往服务器提交数据
                        os.write(toJosn(mBundle).getBytes());
                        if (conn.getResponseCode() == 200){
                            InputStream is = conn.getInputStream();
                            String result = Utils.readInputStream(is);
                            Toast.makeText(this,result,Toast.LENGTH_LONG).show();
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(this,conn.getResponseCode()+"",Toast.LENGTH_LONG).show();
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                //}
                break;
            case R.id.reg_thre_back_button:
                intent = new Intent(this, RegTwoActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }


    //将bundle转换成Json
    public String toJosn(Bundle bundle) {
        LawyerInfo lawyerInfo = new LawyerInfo(bundle.getString("Lnumber"), bundle.getString("Lpassword"),
                bundle.getString("Lphone"), 0, "", bundle.getString("Lname"), bundle.getString("Lyear")
                , bundle.getString("LProvince"), bundle.getString("LCity"), bundle.getString("Loffice"), "", "",
                "", bundle.getString("Lprofile"), "", "");
        Gson gson = new Gson();
        String json = gson.toJson(lawyerInfo, LawyerInfo.class);
        return json;
    }
}
