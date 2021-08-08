package com.kaikai.kc_14_auth.auth;

import com.kaikai.kc_14_auth.apirequest.ApiRequest;

public interface ApiAuthencator {
    void auth(String url);

    void auth(ApiRequest apiRequest);
}