package com.example.demo.listview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private List<JsonBean.ResultBean.DataBean> data;
    Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MyAdapter myAdapter = new MyAdapter(MainActivity.this, data);
            mListView.setAdapter(myAdapter);
        }
    };
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //请求数据
        initData();
        mListView = findViewById(R.id.mListView);

    }

    private void initData() {
        OkGo.get("http://v.juhe.cn/toutiao/index?type=top&key=b0c1a57febbe49da8940dc820c2d8e43")
                .tag(this).cacheKey("top").cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        JsonBean s1 = gson.fromJson(s, JsonBean.class);
                        data = s1.getResult().getData();
                        handler.sendEmptyMessage(0);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                    }
                });
    }
}
