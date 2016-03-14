package com.ithenu.lawyer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ithenu.lawyer.R;
import com.ithenu.lawyer.bean.Replypost;
import com.ithenu.lawyer.bean.Subjectpost;
import com.ithenu.lawyer.utils.Utils;
import com.ithenu.lawyer.view.MyListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

/**
 * Created by Administrator on 2016/3/13 0013.
 */
public class DetailActivity extends AppCompatActivity {
    public MyListView listView;
    //主题id
    public Subjectpost subjectpost;
    public TextView title;
    public TextView content;
    public TextView author;
    public TextView date;
    public List<Replypost> replyposts;
    private ImageView mReply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_detail);
        Bundle bundle =  getIntent().getExtras();
        subjectpost = (Subjectpost) bundle.getSerializable("Subjectpost");


        listView = (MyListView)findViewById(R.id.list);
        sendData();
        initUI();

    }

    private void initUI() {
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
        author = (TextView) findViewById(R.id.author);
        date = (TextView) findViewById(R.id.date);
        mReply = (ImageView) findViewById(R.id.iv_reply);
        title.setText(subjectpost.getTitle());
        content.setText(subjectpost.getContent());
        author.setText(subjectpost.getAuthor());
        date.setText(subjectpost.getDate());
        mReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle =  getIntent().getExtras();
                startActivity(new Intent(DetailActivity.this,ReleaseActivity.class).putExtras(bundle));
            }
        });

    }

    private void sendData() {
        new Thread(){
            @Override
            public void run() {
                HttpUtils http = new HttpUtils();
                RequestParams params = new RequestParams();
                params.addBodyParameter("tid", String.valueOf(subjectpost.getId()));
                http.send(HttpRequest.HttpMethod.POST,
                        Utils.url + "user_getReply",
                        params,
                        new RequestCallBack<String>() {

                            @Override
                            public void onLoading(long total, long current, boolean isUploading) {

                            }

                            @Override
                            public void onSuccess(ResponseInfo<String> responseInfo) {
                                Gson gson = new Gson();
                                replyposts = gson.
                                        fromJson(responseInfo.result, new TypeToken<List<Replypost>>() {
                                        }.getType());

                                MyAdapter adapter = new MyAdapter();
                                listView.setAdapter(adapter);
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

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return replyposts.size();
        }

        @Override
        public Object getItem(int position) {
            return replyposts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder holder = null;
            if (convertView == null) {
                view = View.inflate(DetailActivity.this, R.layout.item_reply, null);
                holder = new ViewHolder();

                holder.name = (TextView) view.findViewById(R.id.lawyerName);
                holder.date = (TextView) view.findViewById(R.id.date);
                holder.content = (TextView) view.findViewById(R.id.content);
                view.setTag(holder);

            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
Log.e("liu",replyposts.get(position).toString());
            holder.name.setText(replyposts.get(position).getAuthor()+"回复：");
            holder.content.setText(replyposts.get(position).getContent());
            holder.date.setText(replyposts.get(position).getDate());
            return view;
        }
    }
    class ViewHolder {
        TextView name;
        TextView content;
        TextView date;
    }


}
