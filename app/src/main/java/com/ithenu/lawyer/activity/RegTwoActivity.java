package com.ithenu.lawyer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ithenu.lawyer.R;


/**
 * Created by provate on 2016/3/9.
 */
public class RegTwoActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnRegNext,mBtnRegUp;
    private Intent intent;
    private EditText mLawyerPro, mLawyerCity, mOfficeName, mLawyerSdept, mYears;
    private Bundle mbundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_two);
        init();
        mbundle = getIntent().getBundleExtra("bundleExtra");//获得上个Activity的数据
    }

    private void init() {
        mBtnRegNext = (Button) findViewById(R.id.ToTwoButton);
        mBtnRegUp = (Button) findViewById(R.id.reg_two_back_button);
        mBtnRegNext.setOnClickListener(this);
        mBtnRegUp.setOnClickListener(this);
        mLawyerPro = (EditText) findViewById(R.id.reg_lawyerPro);
        mLawyerCity = (EditText) findViewById(R.id.reg_lawyercity);
        mOfficeName = (EditText) findViewById(R.id.et_reg_officename);
        mLawyerSdept = (EditText) findViewById(R.id.et_reg_lawyersdept);
        mYears = (EditText) findViewById(R.id.et_reg_years);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ToTwoButton:
                intent = new Intent(this,RegThreeActivity.class);
                /*if(TextUtils.isEmpty(mLawyerPro.getText().toString())){
                    //Utils.Toast("请填写您的地址");
                }else if(TextUtils.isEmpty(mLawyerCity.getText().toString())){
                //Utils.Toast("请填写您的地址");
                }else if (TextUtils.isEmpty(mOfficeName.getText().toString())){
                    //Utils.Toast("请填写真实律师事务所");
                }else if (TextUtils.isEmpty(mLawyerSdept.getText().toString())){
                    //Utils.Toast("请填写您的专长");
                }else if (TextUtils.isEmpty(mYears.getText().toString())){
                    //Utils.Toast("请填写您的从业时长");
                }else {*/
                mbundle.putString("LProvince", mLawyerPro.getText().toString());
                mbundle.putString("LCity", mLawyerCity.getText().toString());
                mbundle.putString("Loffice", mOfficeName.getText().toString());
                mbundle.putString("Lsdept", mLawyerSdept.getText().toString());
                mbundle.putString("Lyear", mYears.getText().toString());
                intent.putExtra("bundleExtra", mbundle);
                startActivity(intent);
                finish();
                // }
                break;
            case R.id.reg_two_back_button:
                intent = new Intent(this,RegOneActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }
}
