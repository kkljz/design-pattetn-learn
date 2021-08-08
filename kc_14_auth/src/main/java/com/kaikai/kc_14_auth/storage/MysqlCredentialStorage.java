package com.kaikai.kc_14_auth.storage;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟数据库信息
 */
public class MysqlCredentialStorage implements CredentialStorage {

    private static Map<String, String> passwordMap = new  HashMap();

    static {
        passwordMap.put("1", "ddd-1001");
        passwordMap.put("2", "ddd-1002");
        passwordMap.put("3", "ddd-1003");
        passwordMap.put("4", "ddd-1004");
        passwordMap.put("5", "ddd-1005");
        passwordMap.put("6", "ddd-1006");
    }

    @Override
    public String getPasswordByAppId(String appId) {
        return passwordMap.get(appId);
    }
}
