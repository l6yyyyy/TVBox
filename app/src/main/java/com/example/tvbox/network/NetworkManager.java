package com.example.tvbox.network;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkManager {

    private static final String TAG = "NetworkManager";
    private static NetworkManager sInstance;
    private OkHttpClient mClient;

    private NetworkManager() {
        // 初始化OkHttpClient
        mClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public static NetworkManager getInstance() {
        if (sInstance == null) {
            synchronized (NetworkManager.class) {
                if (sInstance == null) {
                    sInstance = new NetworkManager();
                }
            }
        }
        return sInstance;
    }

    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = mClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public void setProxy(String proxyUrl) {
        // 实现代理设置
        Log.d(TAG, "Proxy set to: " + proxyUrl);
    }

    public void setDoH(String dohUrl) {
        // 实现DoH设置
        Log.d(TAG, "DoH set to: " + dohUrl);
    }

    public void setHosts(String hosts) {
        // 实现Hosts设置
        Log.d(TAG, "Hosts set: " + hosts);
    }

    public void setAdBlock(boolean enabled) {
        // 实现广告拦截设置
        Log.d(TAG, "Ad block " + (enabled ? "enabled" : "disabled"));
    }
}
