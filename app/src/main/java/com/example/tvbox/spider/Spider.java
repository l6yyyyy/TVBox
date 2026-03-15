package com.example.tvbox.spider;

import java.util.List;
import java.util.Map;

public interface Spider {
    void init(Map<String, String> params);
    String homeContent(boolean filter);
    String homeVideoContent(String channelId, int page);
    String categoryContent(String tid, String pg, String filter, boolean extend);
    String detailContent(String id);
    String searchContent(String key, boolean quick);
    String playerContent(String flag, String id, List<String> vipFlags);
    boolean manualVideoCheck();
    boolean cacheData();
}
