package com.example.tvbox.spider;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class DataSourceManager {

    private static final String TAG = "DataSourceManager";
    private static DataSourceManager sInstance;
    private Map<String, Spider> mSpiderMap;

    private DataSourceManager() {
        mSpiderMap = new HashMap<>();
    }

    public static DataSourceManager getInstance() {
        if (sInstance == null) {
            synchronized (DataSourceManager.class) {
                if (sInstance == null) {
                    sInstance = new DataSourceManager();
                }
            }
        }
        return sInstance;
    }

    public Spider getSpider(String key) {
        return mSpiderMap.get(key);
    }

    public void registerSpider(String key, Spider spider) {
        mSpiderMap.put(key, spider);
        Log.d(TAG, "Spider registered: " + key);
    }

    public void unregisterSpider(String key) {
        mSpiderMap.remove(key);
        Log.d(TAG, "Spider unregistered: " + key);
    }

    public void clearAllSpiders() {
        mSpiderMap.clear();
        Log.d(TAG, "All spiders cleared");
    }

    public int getSpiderCount() {
        return mSpiderMap.size();
    }

    public void initDefaultSpiders() {
        // 注册默认的爬虫
        SpiderImpl defaultSpider = new SpiderImpl();
        defaultSpider.init(new HashMap<String, String>());
        registerSpider("default", defaultSpider);
        Log.d(TAG, "Default spiders initialized");
    }
}
