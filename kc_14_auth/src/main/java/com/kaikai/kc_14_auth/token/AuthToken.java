package com.kaikai.kc_14_auth.token;

import com.kaikai.kc_14_auth.util.MD5Utils;

import java.util.Map;
import java.util.Objects;

/**
 * AuthToken
 *
 * @author Hu Shengkai
 * @date 2021/8/8
 */
public class AuthToken {
    /*
        1. 把 URL、AppID、密码、时间戳拼接为一个字符串；
        2. 对字符串通过加密算法加密生成 token；
        6. 根据时间戳判断 token 是否过期失效；
        7. 验证两个 token 是否匹配；
     */

    /**
     * 默认过期时间间隔值
     */
    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 60 * 1000;

    private static final String SEPARATE = "|";

    private String token;

    private long createTime;

    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken(String token, long createTime, long expiredTimeInterval) {
        this.token = token;
        this.createTime = createTime;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    public AuthToken(String token, long expiredTimeInterval) {
        this.token = token;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    public static AuthToken create(String baseUrl, long createTime, Map<String, String> params) {
        //TODO 创建AutoToken
        return null;
    }

    public static AuthToken generateToken(String baseUrl, String password, long createTime, String appId) {
        String tokenStr = generateTokenStr(baseUrl, password, createTime, appId);
        return new AuthToken(MD5Utils.md5(tokenStr), createTime);
    }

    private static String generateTokenStr(String baseUrl, String password, long createTime, String appId) {
        //把 URL、AppID、密码、时间戳拼接为一个字符串；
        StringBuffer sb = new StringBuffer(baseUrl);
        sb.append(SEPARATE).append(password);
        sb.append(SEPARATE).append(appId);
        sb.append(SEPARATE).append(createTime);
        return sb.toString();
    }

    public String getToken() {
        return this.token;
    }

    public boolean isExpired() {
        //根据时间戳判断 token 是否过期失效；
        if (createTime + expiredTimeInterval > System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    public boolean match(AuthToken authToken) {
        //验证两个 token 是否匹配；
        return Objects.equals(authToken.getToken(), token);
    }
}
