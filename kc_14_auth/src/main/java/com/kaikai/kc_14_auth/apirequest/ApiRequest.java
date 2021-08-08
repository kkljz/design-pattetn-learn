package com.kaikai.kc_14_auth.apirequest;

import java.util.HashMap;
import java.util.Map;

/**
 * ApiRequest
 *
 * @author Hu Shengkai
 * @date 2021/8/8
 */
public class ApiRequest {
    private String baseUrl;
    private String token;
    private String appId;
    private long timestamp;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getToken() {
        return token;
    }

    public String getAppId() {
        return appId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public ApiRequest(String baseUrl, String token, String appId, long timestamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }

    public static ApiRequest buildFromUrl(String baseUrl) {
        // baseUrl=urlxxx&appId=1001&timestamp=18898987654&token=a78cdef998
        // 根据URL解析出appId, token, createTime, url
        Map<String, String> paramMap = analysisUrlToParamMap(baseUrl);

        String appId = paramMap.get("appId");
        String token = paramMap.get("token");
        long timestamp = Long.valueOf(paramMap.get("timestamp"));
        String url = paramMap.get("url");

        return new ApiRequest(url, token, appId, timestamp);
    }

    private static Map<String, String> analysisUrlToParamMap(String baseUrl) {
        String str = baseUrl.substring(baseUrl.indexOf("?") + 1);
//        System.out.println(str);

        String url = baseUrl.substring(0, baseUrl.indexOf("?"));

        String[] split = str.split("&");

        Map<String, String> paramMap = new HashMap<>();
        for (String s : split) {
            String[] split1 = s.split("=");
            if (split1.length == 2) {
                paramMap.put(split1[0], split1[1]);
            }
        }
        paramMap.put("url", url);
//        System.out.println(paramMap.toString());
        return paramMap;
    }
}
