package com.ithenu.lawyer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Mars on 2016/3/10.
 */
public class SettingMainTabFragment extends Fragment {
    public static SettingMainTabFragment instance = null;
    public static SettingMainTabFragment getInstance(){
        if (instance == null){
            instance = new SettingMainTabFragment();
        }
        return instance;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("主页面");
        return textView;
    }
}
