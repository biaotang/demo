package com.macrowing.mybatis.controller;

import com.alibaba.fastjson.JSONObject;
import com.macrowing.mybatis.model.User;
import com.macrowing.mybatis.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/{id}")
    public String info(@PathVariable Long id) {
        User user = userMapper.selectById(id);
        return JSONObject.toJSONString(user);
    }

    @PostMapping(value = "/add")
    public String add(@RequestBody User user) {
        int count = userMapper.save(user);
        return String.valueOf(count);
    }

}
