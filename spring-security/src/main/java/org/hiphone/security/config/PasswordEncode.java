package org.hiphone.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * PasswordEncoder的实现类,Spring Security 5不允许密码已铭文形式传输
 * 可以在这里加入加密解密算法
 * @author HiPhone
 */
public class PasswordEncode implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
