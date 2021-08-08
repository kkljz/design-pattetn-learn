package com.kaikai.kc_14_auth.test;

import com.kaikai.kc_14_auth.auth.ApiAuthencator;
import com.kaikai.kc_14_auth.auth.impl.ApiAuthencatorImpl;
import com.kaikai.kc_14_auth.token.AuthToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * AutoTest
 *
 * @author Hu Shengkai
 * @date 2021/8/8
 */

public class AutoTest {

    public static void main(String[] args) {
        ApiAuthencator apiAuthencator = new ApiAuthencatorImpl();

        String req = "kaikai?"
                + "appId=1"
                + "&token=600f20ea1ab2118792e4e0321307a57c"
                + "&timestamp=1628421435750";
        apiAuthencator.auth(req);
        System.out.println("验证通过");
    }

    @Test
    public void generateToken() {
        AuthToken authToken = AuthToken.generateToken("kaikai", "ddd-1001", 1628421435750L, "1");
        System.out.println(authToken.getToken());
    }

    @Test
    public void test() {
        System.out.println(System.currentTimeMillis());
    }
}
