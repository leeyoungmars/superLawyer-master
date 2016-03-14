package com.ithenu.lawyer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.ithenu.lawyer.R;
import com.ithenu.lawyer.utils.Utils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


/**
 * Created by Administrator on 2016/3/6 0006.
 */
public class LoadingActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initRongYun();
            }
        }, 2000);
    }

    private void initRongYun() {
        RongIM.connect(Utils.Token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
            }

            @Override
            public void onSuccess(String s) {
                startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
                LoadingActivity.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }
}
