package com.ithenu.lawyer.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ithenu.lawyer.R;
import com.ithenu.lawyer.bean.LawyerInfo;
import com.ithenu.lawyer.bean.Subjectpost;
import com.ithenu.lawyer.utils.Utils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
/**
 * Created by Administrator on 2016/3/13 0013.
 */
public class ReleaseActivity extends Activity {
    private ImageView img;
    private TextView content;
    Subjectpost subjectpost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        Bundle bundle =  getIntent().getExtras();
        subjectpost = (Subjectpost) bundle.getSerializable("Subjectpost");
        img = (ImageView) findViewById(R.id.img);
        content = (TextView) findViewById(R.id.content);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(content.getText().toString()!=""){
                    sendData();
                }else{
                    Utils.Toast("请补全信息");
                }

            }
        });

    }


    public void sendData(){

        new Thread(){
            @Override
            public void run() {
                SharedPreferences getNamePre = getSharedPreferences("lawyerInfo", MODE_PRIVATE);
                String json = getNamePre.getString("Json", "");
                Log.e("Json",json);
                Gson gson = new Gson();
                String lawyerName = gson.fromJson(json, LawyerInfo.class).getName();
                HttpUtils http = new HttpUtils();
                RequestParams params = new RequestParams();
                params.addBodyParameter("author",lawyerName);
                params.addBodyParameter("pid",subjectpost.getId()+"");
                params.addBodyParameter("content", content.getText().toString());

                http.send(HttpRequest.HttpMethod.POST,
                        Utils.url + "lawyer_release",
                        params,
                        new RequestCallBack<String>() {

                            @Override
                            public void onLoading(long total, long current, boolean isUploading) {

                            }

                            @Override
                            public void onSuccess(ResponseInfo<String> responseInfo) {
                                if(responseInfo.result.equals("SUCCESS")){
                                    Utils.Toast("回复成功");
//                                    QuestionActivity.handler.sendEmptyMessage(1);
                                    finish();
                                }else{
                                    Utils.Toast("回复失败，请重试");
                                }

                            }

                            @Override
                            public void onStart() {
                            }

                            @Override
                            public void onFailure(HttpException error, String msg) {
                            }
                        });
            }
        }.start();
    }
}
