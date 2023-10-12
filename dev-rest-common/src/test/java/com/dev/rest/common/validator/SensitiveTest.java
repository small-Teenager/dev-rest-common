package com.dev.rest.common.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: yaodong zhang
 * @create 2023/1/3
 */
public class SensitiveTest {


    public static void main (String args[]) throws JsonProcessingException {

        sensitiveDemo();
    }
    public static void sensitiveDemo() throws JsonProcessingException {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1l);
        userEntity.setName("张三");
        userEntity.setMobile("18000000001");
        userEntity.setIdCard("420117200001011000008888");
        userEntity.setAge(20);
        userEntity.setSex("男");

        //通过jackson方式，将对象序列化成json字符串
        ObjectMapper objectMapper = new ObjectMapper();
        System.err.println(objectMapper.writeValueAsString(userEntity));
    }
}
