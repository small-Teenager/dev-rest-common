package com.dev.rest.common.utils;

import java.util.concurrent.ThreadLocalRandom;

public class GeneratePhoneNumber {
    public static void main(String[] args) {
        String phoneNumber = generatePhoneNumber();
        System.out.println(phoneNumber);
    }

    public static String generatePhoneNumber() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        StringBuilder sb = new StringBuilder();
        // 手机号的前三位
        sb.append("1");
        sb.append(random.nextInt(9) + 1);
        sb.append(random.nextInt(10));
        // 后面的八位数字
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}