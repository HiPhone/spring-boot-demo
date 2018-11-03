package org.hiphone.security.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HiPhone
 */
public class Constant {

    public static final String BASE64_KEY = "hcFKesGkfEkECB951q7VDoXBSnrBpHxJ";

    public static final String ENCRYPT_TYPE = "DESede";

    public static final String ENCODE_TYPE = "UTF-8";

    public static final Map<Integer, String> userRoleMap = new HashMap<>();

    static {
        userRoleMap.put(0, "ADMIN");
        userRoleMap.put(1, "DBA");
        userRoleMap.put(2, "USER");
    }
}
