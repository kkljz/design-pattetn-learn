package com.kaikai.kc_14_auth.auth.impl;

import com.kaikai.kc_14_auth.apirequest.ApiRequest;
import com.kaikai.kc_14_auth.auth.ApiAuthencator;
import com.kaikai.kc_14_auth.storage.CredentialStorage;
import com.kaikai.kc_14_auth.storage.MysqlCredentialStorage;
import com.kaikai.kc_14_auth.token.AuthToken;

/**
 * ApiAuthencator
 *
 * @author Hu Shengkai
 * @date 2021/8/8
 */
public class ApiAuthencatorImpl implements ApiAuthencator {

    private CredentialStorage credentialStorage;

    public ApiAuthencatorImpl(CredentialStorage credentialStorage) {
        this.credentialStorage = credentialStorage;
    }

    public ApiAuthencatorImpl() {
        this.credentialStorage = new MysqlCredentialStorage();
    }

    @Override
    public void auth(String url) {
        ApiRequest apiRequest = ApiRequest.buildFromUrl(url);
        auth(apiRequest);
    }

    @Override
    public void auth(ApiRequest apiRequest) {
        String appId = apiRequest.getAppId();
        String token = apiRequest.getToken();
        long timestamp = apiRequest.getTimestamp();
        String baseUrl = apiRequest.getBaseUrl();

        AuthToken clientAuthToken = new AuthToken(token, timestamp);
        if (clientAuthToken.isExpired()) {
            throw new RuntimeException("Token is expired.");
        }

        String password = credentialStorage.getPasswordByAppId(appId);
        AuthToken serverAuthToken = AuthToken.generateToken(baseUrl, password, timestamp, appId);
        if (!serverAuthToken.match(clientAuthToken)) {
            throw new RuntimeException("Token verfication failed.");
        }
    }
}
