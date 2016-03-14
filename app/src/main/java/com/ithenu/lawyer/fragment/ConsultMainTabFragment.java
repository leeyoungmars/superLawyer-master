package com.ithenu.lawyer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ithenu.lawyer.R;
import com.ithenu.lawyer.activity.DetailActivity;
import com.ithenu.lawyer.bean.Subjectpost;
import com.ithenu.lawyer.utils.Utils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars on 2016/3/10.
 */
public class ConsultMainTabFragment extends Fragment {
    private ListView list;
    private List<Subjectpost> arrList;
    private LinearLayout layout;
    private static Thread thread;
    public static ConsultMainTabFragment instance = null;//编写单例模式
    public static ConsultMainTabFragment getInstance(){
        if (instance == null){
            instance = new ConsultMainTabFragment();
        }
        return instance;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_question, container, false);
        arrList = new ArrayList<>();
        list = (ListView) view.findViewById(R.id.qlist);
        layout = (LinearLayout) view.findViewById(R.id.progressBar);
        getData();
        return view;
    }

    public void getData() {
        thread = new Thread(){
            @Override
            public void run() {
                HttpUtils http = new HttpUtils();
                http.send(HttpRequest.HttpMethod.GET,
                        Utils.url + "user_getQuestion",
                        new RequestCallBack<String>() {

                            @Override
                            public void onLoading(long total, long current, boolean isUploading) {

                            }

                            @Override
                            public void onSuccess(ResponseInfo<String> responseInfo) {
                                Gson gson = new Gson();
                                arrList = gson.
                                        fromJson(responseInfo.result, new TypeToken<List<Subjectpost>>() {
                                        }.getType());

                                MyAdapter mAdapter = new MyAdapter();
                                list.setAdapter(mAdapter);
                                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("Subjectpost", arrList.get(position));
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                                layout.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onStart() {
                            }

                            @Override
                            public void onFailure(HttpException error, String msg) {
                            }
                        });
            }
        };
        thread.start();

    }



    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrList.get(position);
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
                view = View.inflate(getActivity(), R.layout.item_question, null);
                holder = new ViewHolder();

                holder.tv_title = (TextView) view.findViewById(R.id.title);
                holder.tv_author = (TextView) view.findViewById(R.id.author);
                holder.tv_date = (TextView) view.findViewById(R.id.date);
                holder.tv_content = (TextView) view.findViewById(R.id.content);
                view.setTag(holder);

            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }

            holder.tv_title.setText(arrList.get(position).getTitle());
            holder.tv_date.setText(arrList.get(position).getDate());
            holder.tv_author.setText(arrList.get(position).getAuthor());
            holder.tv_content.setText(arrList.get(position).getContent());
            return view;
        }
    }

    class ViewHolder {
        TextView tv_title;
        TextView tv_author;
        TextView tv_date;
        TextView tv_content;

    }
}
