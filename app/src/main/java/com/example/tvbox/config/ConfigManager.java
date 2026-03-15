package com.example.tvbox.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private static final String TAG = "ConfigManager";
    private static final String PREF_NAME = "tvbox_config";
    private static ConfigManager sInstance;
    private SharedPreferences mPrefs;
    private Gson mGson;

    private ConfigManager(Context context) {
        mPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mGson = new Gson();
    }

    public static ConfigManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (ConfigManager.class) {
                if (sInstance == null) {
                    sInstance = new ConfigManager(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    public void saveConfig(String key, Object value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            editor.putString(key, mGson.toJson(value));
        }
        editor.apply();
        Log.d(TAG, "Config saved: " + key + " = " + value);
    }

    public String getString(String key, String defaultValue) {
        return mPrefs.getString(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return mPrefs.getInt(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return mPrefs.getBoolean(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return mPrefs.getFloat(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return mPrefs.getLong(key, defaultValue);
    }

    public <T> T getObject(String key, Class<T> clazz, T defaultValue) {
        String json = mPrefs.getString(key, null);
        if (json != null) {
            try {
                return mGson.fromJson(json, clazz);
            } catch (Exception e) {
                Log.e(TAG, "Failed to parse object: " + e.getMessage());
            }
        }
        return defaultValue;
    }

    public void removeConfig(String key) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.remove(key);
        editor.apply();
        Log.d(TAG, "Config removed: " + key);
    }

    public void clearAll() {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.clear();
        editor.apply();
        Log.d(TAG, "All config cleared");
    }

    // 网络设置相关
    public void setProxyUrl(String proxyUrl) {
        saveConfig("proxy_url", proxyUrl);
    }

    public String getProxyUrl() {
        return getString("proxy_url", "");
    }

    public void setDoHUrl(String dohUrl) {
        saveConfig("doh_url", dohUrl);
    }

    public String getDoHUrl() {
        return getString("doh_url", "");
    }

    public void setHosts(String hosts) {
        saveConfig("hosts", hosts);
    }

    public String getHosts() {
        return getString("hosts", "");
    }

    public void setAdBlockEnabled(boolean enabled) {
        saveConfig("ad_block_enabled", enabled);
    }

    public boolean isAdBlockEnabled() {
        return getBoolean("ad_block_enabled", false);
    }

    // 数据源配置相关
    public void setDataSourceUrl(String url) {
        saveConfig("data_source_url", url);
    }

    public String getDataSourceUrl() {
        return getString("data_source_url", "");
    }

    public void setDataSourceEnabled(String key, boolean enabled) {
        saveConfig("data_source_" + key, enabled);
    }

    public boolean isDataSourceEnabled(String key) {
        return getBoolean("data_source_" + key, true);
    }

    // 播放器设置相关
    public void setPlayerType(int type) {
        saveConfig("player_type", type);
    }

    public int getPlayerType() {
        return getInt("player_type", 0); // 0: ExoPlayer, 1: 系统播放器
    }

    public void setAutoPlay(boolean autoPlay) {
        saveConfig("auto_play", autoPlay);
    }

    public boolean isAutoPlay() {
        return getBoolean("auto_play", true);
    }

    public void setDefaultQuality(int quality) {
        saveConfig("default_quality", quality);
    }

    public int getDefaultQuality() {
        return getInt("default_quality", 0); // 0: 自动, 1: 高清, 2: 超清, 3: 蓝光
    }
}
