package com.deyuan.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//      加密操作
        public static String encoderPassword(String password){
            return bCryptPasswordEncoder.encode(password);
        }

    public static void main(String[] args) {

//            动态加密，更安全，加盐，多次加密不一样
        String password = BCryptPasswordEncoderUtils.encoderPassword("cctv");
        System.out.println(password);
    }
}
