package com.ithenu.lawyer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ithenu.lawyer.R;

/**
 * Created by provate on 2016/3/9.
 */
public class RegOneActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnRegNext,mBtnRegUp;
    private Intent intent;
    private Bundle mbundle = new Bundle();
    private EditText mLawyerNumber,mLawyerName,mLawyerPhone,mPassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_one);
        init();

    }
    private void init(){
        mBtnRegNext = (Button) findViewById(R.id.ToTwoButton);
        mBtnRegUp = (Button) findViewById(R.id.reg_one_back_button);
        mBtnRegNext.setOnClickListener(this);
        Log.e("mBtnRegUp", mBtnRegUp + "");
        mBtnRegUp.setOnClickListener(this);

        mLawyerNumber = (EditText) findViewById(R.id.et_reg_lawyernumber);
        mLawyerName = (EditText) findViewById(R.id.et_reg_lawyername);
        mLawyerPhone = (EditText) findViewById(R.id.et_reg_lawyerphone);
        mPassWord = (EditText) findViewById(R.id.et_reg_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ToTwoButton:
                intent = new Intent(this,RegTwoActivity.class);
               /* if(TextUtils.isEmpty(mLawyerNumber.getText().toString())){
                    //Utils.Toast("您的证件号不能为空！");
                }else if (TextUtils.isEmpty(mLawyerName.getText().toString())){
                    //Utils.Toast("请输入您的真实姓名");
                }else if (TextUtils.isEmpty(mLawyerPhone.getText().toString())){
                    //Utils.Toast("请填写您的手机号");
                }else if (TextUtils.isEmpty(mPassWord.getText().toString())){
                //Utils.Toast("请填写您的密码");
                }else {*/
                mbundle.putString("Lnumber", mLawyerNumber.getText().toString());
                mbundle.putString("Lname", mLawyerName.getText().toString());
                mbundle.putString("Lphone", mLawyerPhone.getText().toString());
                mbundle.putString("Lpassword", mPassWord.getText().toString());
                intent.putExtra("bundleExtra", mbundle);
                startActivity(intent);
                finish();
                //}
                break;
            case R.id.reg_one_back_button:
                intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }

}
