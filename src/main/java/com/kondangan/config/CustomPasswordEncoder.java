package com.kondangan.config;

import com.kondangan.util.Constanta;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        String rawPass = mergePasswordAndSalt((String) rawPassword, Constanta.SALT, true);
        String encodePassword = DigestUtils.md5Hex(rawPassword + Constanta.SALT);
//        String encodePassword = new MessageDigestPasswordEncoder("MD5").encode(rawPass);
        return encodePassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        String rawPass = mergePasswordAndSalt((String) rawPassword, Constanta.SALT, true);
        String rawPass = DigestUtils.md5Hex(rawPassword + Constanta.SALT);
//        PasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("MD5");
        boolean isMatch = rawPass.matches(encodedPassword);

        return isMatch;
    }

    protected String mergePasswordAndSalt(String password, Object salt, boolean strict) {
        if (password == null) {
            password = "";
        }

        if (strict && (salt != null)) {
            if ((salt.toString().lastIndexOf("{") != -1) || (salt.toString().lastIndexOf("}") != -1)) {
                throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
            }
        }

        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }
}