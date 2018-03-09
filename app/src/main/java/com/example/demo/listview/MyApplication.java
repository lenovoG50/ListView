package com.example.demo.listview;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;

/**
 * Created by 59246 on 2018/3/8.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //全局初始化
        OkGo.init(this);
        try {

            OkGo.getInstance().debug("okgo").setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)
                    .setCacheMode(CacheMode.NO_CACHE)
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                    //cookie持久化存储，如果cookie不过期，则一直有效
                    .setCookieStore(new PersistentCookieStore())
                    //信任所有证书
                    .setCertificates();
            //
//           .setCookieStore(new MemoryCookieStore()) //cookie使用内存缓存（app退出后，cookie消失）
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
