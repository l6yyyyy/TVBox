package com.example.tvbox.spider;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpiderImpl implements Spider {

    private static final String TAG = "SpiderImpl";
    private Map<String, String> mParams;

    @Override
    public void init(Map<String, String> params) {
        mParams = params;
        Log.d(TAG, "Spider initialized with params: " + params);
    }

    @Override
    public String homeContent(boolean filter) {
        // 模拟返回首页内容
        Map<String, Object> result = new HashMap<>();
        result.put("class", getCategories());
        result.put("list", getHomeVideos());
        return new Gson().toJson(result);
    }

    @Override
    public String homeVideoContent(String channelId, int page) {
        // 模拟返回首页视频内容
        Map<String, Object> result = new HashMap<>();
        result.put("list", getHomeVideos());
        result.put("page", page);
        result.put("pagecount", 1);
        result.put("limit", 20);
        result.put("total", 20);
        return new Gson().toJson(result);
    }

    @Override
    public String categoryContent(String tid, String pg, String filter, boolean extend) {
        // 模拟返回分类内容
        Map<String, Object> result = new HashMap<>();
        result.put("list", getCategoryVideos(tid));
        result.put("page", Integer.parseInt(pg));
        result.put("pagecount", 1);
        result.put("limit", 20);
        result.put("total", 20);
        return new Gson().toJson(result);
    }

    @Override
    public String detailContent(String id) {
        // 模拟返回详情内容
        Map<String, Object> result = new HashMap<>();
        result.put("vod_id", id);
        result.put("vod_name", "电影" + id);
        result.put("vod_en", "Movie " + id);
        result.put("vod_year", "2023");
        result.put("vod_area", "中国大陆");
        result.put("vod_type", "动作");
        result.put("vod_director", "导演" + id);
        result.put("vod_actor", "演员1, 演员2, 演员3");
        result.put("vod_content", "剧情简介" + id);
        result.put("vod_pic", "https://example.com/poster" + id + ".jpg");
        result.put("vod_play_from", "默认");
        result.put("vod_play_url", "第1集$https://example.com/play1.mp4#第2集$https://example.com/play2.mp4");
        return new Gson().toJson(result);
    }

    @Override
    public String searchContent(String key, boolean quick) {
        // 模拟返回搜索结果
        Map<String, Object> result = new HashMap<>();
        result.put("list", getSearchVideos(key));
        return new Gson().toJson(result);
    }

    @Override
    public String playerContent(String flag, String id, List<String> vipFlags) {
        // 模拟返回播放内容
        Map<String, Object> result = new HashMap<>();
        result.put("parse", 0);
        result.put("url", "https://example.com/play" + id + ".mp4");
        return new Gson().toJson(result);
    }

    @Override
    public boolean manualVideoCheck() {
        return false;
    }

    @Override
    public boolean cacheData() {
        return false;
    }

    private List<Map<String, Object>> getCategories() {
        List<Map<String, Object>> categories = new ArrayList<>();
        Map<String, Object> category1 = new HashMap<>();
        category1.put("type_id", "1");
        category1.put("type_name", "电影");
        categories.add(category1);
        Map<String, Object> category2 = new HashMap<>();
        category2.put("type_id", "2");
        category2.put("type_name", "电视剧");
        categories.add(category2);
        Map<String, Object> category3 = new HashMap<>();
        category3.put("type_id", "3");
        category3.put("type_name", "综艺");
        categories.add(category3);
        Map<String, Object> category4 = new HashMap<>();
        category4.put("type_id", "4");
        category4.put("type_name", "动漫");
        categories.add(category4);
        Map<String, Object> category5 = new HashMap<>();
        category5.put("type_id", "5");
        category5.put("type_name", "直播");
        categories.add(category5);
        return categories;
    }

    private List<Map<String, Object>> getHomeVideos() {
        List<Map<String, Object>> videos = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Map<String, Object> video = new HashMap<>();
            video.put("vod_id", String.valueOf(i));
            video.put("vod_name", "电影" + i);
            video.put("vod_pic", "https://example.com/poster" + i + ".jpg");
            video.put("vod_remarks", "2023");
            videos.add(video);
        }
        return videos;
    }

    private List<Map<String, Object>> getCategoryVideos(String tid) {
        List<Map<String, Object>> videos = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Map<String, Object> video = new HashMap<>();
            video.put("vod_id", tid + "_" + i);
            video.put("vod_name", "分类" + tid + "_电影" + i);
            video.put("vod_pic", "https://example.com/poster" + tid + "_" + i + ".jpg");
            video.put("vod_remarks", "2023");
            videos.add(video);
        }
        return videos;
    }

    private List<Map<String, Object>> getSearchVideos(String key) {
        List<Map<String, Object>> videos = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Map<String, Object> video = new HashMap<>();
            video.put("vod_id", "search_" + i);
            video.put("vod_name", key + "_电影" + i);
            video.put("vod_pic", "https://example.com/poster_search_" + i + ".jpg");
            video.put("vod_remarks", "2023");
            videos.add(video);
        }
        return videos;
    }
}
