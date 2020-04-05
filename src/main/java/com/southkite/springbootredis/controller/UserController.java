package com.southkite.springbootredis.controller;

import com.southkite.springbootredis.domain.User;
import com.southkite.springbootredis.mapper.UserMapper;
import com.southkite.springbootredis.util.RedisUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liao xin <liaoxin@mailink.com>
 * @date 2020/4/4 16:17
 */
@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisUtils redisUtils;

    private static final String key = "userCache_";

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(String id){
        return userMapper.find(id);
    }

    @RequestMapping("/getUserCache")
    @ResponseBody
    public User getUserCache(String id){
        // 先从 redis 里面取值
        User user = (User) redisUtils.get(key + id,new User());
        if (user != null){
            System.out.println(key +id);
            System.out.println("fresh value from Redis id" + "=" + id);
        }
        // 如果拿不到则从 DB 取值
        if (user == null){
            User userDB = userMapper.find(id);
            System.out.println("fresh value from DB id" + "=" + id);
            // DB 非空情况刷新 redis 值
            if (userDB != null){
                redisUtils.set(key + id,userDB);
                return userDB;
            }
        }
        return user;
    }
}
