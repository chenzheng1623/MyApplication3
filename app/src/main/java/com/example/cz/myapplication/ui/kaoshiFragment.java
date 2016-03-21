package com.example.cz.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cz on 2015/6/26.
 */
public class kaoshiFragment extends Fragment {

    public String url = "http://jw3.nwnu.edu.cn:7001/WebEducation/examservlet?action=S&userid=94706";
    private RecyclerView mRecyclerView;
    private CardView mCardView;
    private myAdapter myadapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private myasync lala;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.kaoshi_view, null, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recycleview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                final android.os.Handler mHandler = new android.os.Handler();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final List<mInfo> LL = pareseHtml(Okhttp(url));
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                myadapter.setlist(LL);
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
        new myasync().execute(url);
        return view;

    }

    //jsoup解析html
    private List<mInfo> pareseHtml(String s) {
        List<mInfo> mInfoList = new ArrayList<>();
        Document doc = Jsoup.parse(s);
        Elements trs = doc.select("tr");
        for (int i = 1; i < trs.size() - 1; i++) {
            mInfo mInfo = new mInfo();
            Elements tds = trs.get(i).select("td");
            for (int j = 0; j < tds.size(); j++) {
                //  Log.i(TAG, "----"+tds.get(j).text());
                //Log.i(TAG, tds.get(j).toString());
                String content = tds.get(j).text();
                switch (j) {
                    case 0:
                        mInfo.setNumber(content);
                        break;
                    case 1:
                        mInfo.setSer(content);
                        break;
                    case 2:
                        mInfo.setName(content);
                        break;
                    case 3:
                        mInfo.setTeacher(content);
                        break;
                    case 4:
                        mInfo.setStyle(content);
                        break;
                    case 5:
                        mInfo.setDate(content);
                        break;
                    case 6:
                        mInfo.setTime(content);
                        break;
                    case 7:
                        mInfo.setKaochang(content);
                        break;
                    case 8:
                        mInfo.setZuowei(content);
                        break;
                    case 9:
                        mInfo.setHuankao(content);
                        break;
                }
            }
            mInfoList.add(mInfo);
            // Log.i(TAG,mInfo.toString());
        }
        return mInfoList;
    }

    //使用okhttp访问网络
    private String Okhttp(String param) {
        String content = "";
        OkHttpClient client = new OkHttpClient();
        Request Request = new Request.Builder().url(param).build();
        Response Response = null;
        try {
            Response = client.newCall(Request).execute();
            if (Response.isSuccessful()) {
                return Response.body().string();
            }
        } catch (IOException e) {
            Log.i(MainActivity.TAG, e.getMessage());
            return null;
        }
        return content;
    }

    class myasync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return Okhttp(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            List<mInfo> mInfoList = pareseHtml(s);
            Log.i(MainActivity.TAG, mInfoList.toString());
            myadapter = new myAdapter();
            myadapter.setlist(mInfoList);
            mRecyclerView.setAdapter(myadapter);
        }
    }

    class myAdapter extends RecyclerView.Adapter<myAdapter.MyviewHolder> {
        private List<mInfo> mInfoList;

        public void setlist(List<mInfo> mInfoList) {
            this.mInfoList = mInfoList;
            notifyDataSetChanged();
        }

        @Override
        public MyviewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            CardView mCardView = (android.support.v7.widget.CardView) LayoutInflater.from(getActivity())
                    .inflate(R.layout.kaoshi_item_view, viewGroup, false);
            MyviewHolder holder = new MyviewHolder(mCardView);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyviewHolder holder, int i) {

            holder.number.setText(mInfoList.get(i).getNumber());
            holder.ser.setText(mInfoList.get(i).getSer());
            holder.name.setText(mInfoList.get(i).getName());
            holder.teacher.setText(mInfoList.get(i).getTeacher());
            holder.style.setText(mInfoList.get(i).getStyle());
            holder.date.setText(mInfoList.get(i).getDate());
            holder.time.setText(mInfoList.get(i).getTime());
            holder.kaocha.setText(mInfoList.get(i).getKaochang());
            holder.zuowei.setText(mInfoList.get(i).getZuowei());
            holder.huankao.setText(mInfoList.get(i).getHuankao());

        }

        @Override
        public int getItemCount() {
            return mInfoList.size();
        }

        class MyviewHolder extends RecyclerView.ViewHolder {

            TextView number;
            TextView ser;
            TextView name;
            TextView teacher;
            TextView style;
            TextView date;
            TextView time;
            TextView kaocha;
            TextView zuowei;
            TextView huankao;

            public MyviewHolder(View view) {
                super(view);
                number = (TextView) view.findViewById(R.id.number);
                ser = (TextView) view.findViewById(R.id.ser);
                name = (TextView) view.findViewById(R.id.name);
                teacher = (TextView) view.findViewById(R.id.teacher);
                style = (TextView) view.findViewById(R.id.style);
                date = (TextView) view.findViewById(R.id.date);
                time = (TextView) view.findViewById(R.id.time);
                kaocha = (TextView) view.findViewById(R.id.kaochang);
                zuowei = (TextView) view.findViewById(R.id.zuowei);
                huankao = (TextView) view.findViewById(R.id.huankao);
            }
        }

    }
}
