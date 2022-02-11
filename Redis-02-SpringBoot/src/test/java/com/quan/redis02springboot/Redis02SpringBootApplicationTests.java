package com.quan.redis02springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.redis02springboot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import com.quan.redis02springboot.utils.RedisUtil;


import java.util.List;

@SpringBootTest
class Redis02SpringBootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        // 操作常用的数据类型
        redisTemplate.opsForValue().set("springRedisString", "string1");
        System.out.println(redisTemplate.opsForValue().get("springRedisString"));

        redisTemplate.opsForList().leftPush("list1", "test1");
        List list1 = redisTemplate.opsForList().range("list1", 0, -1);
        System.out.println(list1);


        // 操作与数据库相关的命令
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushDb();
    }

    // 序列化测试
    @Test
    void serializeTest() throws JsonProcessingException {
        // 真实的开发都是使用 json 来传输对象
        User user = new User("测试类4", 4);
        String userJson = new ObjectMapper().writeValueAsString(user);  // 使用jackson的json处理
        redisTemplate.opsForValue().set("user4", user);

        System.out.println(redisTemplate.opsForValue().get("user3"));
    }


    @Test
    void testUtils() {
        redisUtil.set("testUtils", "successTest!");
        System.out.println(redisUtil.get("testUtils"));
    }

}
