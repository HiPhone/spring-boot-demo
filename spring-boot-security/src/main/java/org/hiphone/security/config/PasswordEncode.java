package org.hiphone.security.config;

import org.hiphone.security.utils.EncryptoUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * PasswordEncoder的实现类,Spring Security 5不允许密码以明文形式传输
 * 可以在这里加入加密解密算法
 * @author HiPhone
 */
public class PasswordEncode implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return EncryptoUtil.encryptStringByBase64(charSequence.toString(), null);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(encode(charSequence));
    }
}
