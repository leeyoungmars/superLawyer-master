package com.ithenu.lawyer.activity;


import android.content.DialogInterface;
import android.net.Uri;
import android.os.*;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.ithenu.lawyer.R;
import com.ithenu.lawyer.fragment.ConsultMainTabFragment;
import com.ithenu.lawyer.fragment.SettingMainTabFragment;
import java.util.ArrayList;
import java.util.List;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

public class MainActivity extends FragmentActivity{
    private ViewPager mViewPager;
    private TextView mChart,mConsult,mSetting;
    private Fragment mConversationList;
    private Fragment mConversationFragment = null;
    private FragmentPagerAdapter mFragmentPagerAdapter;//将Tab页面持久在内存中
    private List<Fragment> mFragment = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTitle(R.string.app_name);
        setContentView(R.layout.activity_main);
        initView();
        mConversationList = initConversationList();//融云会话列表对象
        //mViewPager = (ViewPager) findViewById(R.id.vp_viewPager);
        mFragment.add(mConversationList);
        mFragment.add(ConsultMainTabFragment.getInstance());
        mFragment.add(SettingMainTabFragment.getInstance());
        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        };
        mViewPager.setAdapter(mFragmentPagerAdapter);
        setListener();
    }
    private Fragment initConversationList(){
        if (mConversationFragment == null) {
            ConversationListFragment listFragment = ConversationListFragment.getInstance();
            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                    .build();
            listFragment.setUri(uri);
            return listFragment;
        }else {
            return mConversationFragment;
        }
    }


    private void setListener() {
        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChart.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                mConsult.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                mSetting.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                v.setBackgroundColor(getResources().getColor(R.color.them));
                if (v == mChart) {
                    mViewPager.setCurrentItem(0);
                } else if (v == mConsult) {
                    mViewPager.setCurrentItem(1);
                } else if (v == mSetting) {
                    mViewPager.setCurrentItem(2);
                }
            }
        };
        mChart.setOnClickListener(onClickListener);
        mConsult.setOnClickListener(onClickListener);
        mSetting.setOnClickListener(onClickListener);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        onClickListener.onClick(mChart);
                        break;
                    case 1:
                        onClickListener.onClick(mConsult);
                        break;
                    case 2:
                        onClickListener.onClick(mSetting);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        onClickListener.onClick(mConsult);
    }
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_viewPager);
        mChart = (TextView) findViewById(R.id.tv_chart);
        mConsult = (TextView) findViewById(R.id.tv_consult);
        mSetting = (TextView) findViewById(R.id.tv_setting);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()){
            final AlertDialog.Builder alterDialog = new AlertDialog.Builder(this);
            alterDialog.setMessage("确定退出应用？");
            alterDialog.setCancelable(true);
            alterDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (RongIM.getInstance() != null){
                        RongIM.getInstance().disconnect();
                        try {
                            Thread.sleep(500);
                            android.os.Process.killProcess(Process.myPid());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        return super.onKeyDown(keyCode, event);
    }
}
