package com.example.tvbox;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.leanback.preference.LeanbackPreferenceFragmentCompat;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.example.tvbox.config.ConfigManager;
import com.example.tvbox.network.NetworkManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.settings_fragment, new SettingsFragment())
                    .commitNow();
        }
    }

    public static class SettingsFragment extends LeanbackPreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.preferences, rootKey);

            // 初始化配置管理器
            final ConfigManager configManager = ConfigManager.getInstance(getContext());
            final NetworkManager networkManager = NetworkManager.getInstance();

            // 网络设置
            EditTextPreference proxyPreference = findPreference("proxy_url");
            if (proxyPreference != null) {
                proxyPreference.setSummary(configManager.getProxyUrl());
                proxyPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        String proxyUrl = (String) newValue;
                        configManager.setProxyUrl(proxyUrl);
                        networkManager.setProxy(proxyUrl);
                        preference.setSummary(proxyUrl);
                        return true;
                    }
                });
            }

            EditTextPreference dohPreference = findPreference("doh_url");
            if (dohPreference != null) {
                dohPreference.setSummary(configManager.getDoHUrl());
                dohPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        String dohUrl = (String) newValue;
                        configManager.setDoHUrl(dohUrl);
                        networkManager.setDoH(dohUrl);
                        preference.setSummary(dohUrl);
                        return true;
                    }
                });
            }

            SwitchPreferenceCompat adBlockPreference = findPreference("ad_block_enabled");
            if (adBlockPreference != null) {
                adBlockPreference.setChecked(configManager.isAdBlockEnabled());
                adBlockPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        boolean enabled = (Boolean) newValue;
                        configManager.setAdBlockEnabled(enabled);
                        networkManager.setAdBlock(enabled);
                        return true;
                    }
                });
            }

            // 数据源设置
            EditTextPreference dataSourcePreference = findPreference("data_source_url");
            if (dataSourcePreference != null) {
                dataSourcePreference.setSummary(configManager.getDataSourceUrl());
                dataSourcePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        String dataSourceUrl = (String) newValue;
                        configManager.setDataSourceUrl(dataSourceUrl);
                        preference.setSummary(dataSourceUrl);
                        return true;
                    }
                });
            }

            // 播放器设置
            SwitchPreferenceCompat autoPlayPreference = findPreference("auto_play");
            if (autoPlayPreference != null) {
                autoPlayPreference.setChecked(configManager.isAutoPlay());
                autoPlayPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        boolean autoPlay = (Boolean) newValue;
                        configManager.setAutoPlay(autoPlay);
                        return true;
                    }
                });
            }
        }
    }
}
