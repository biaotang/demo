package com.macrowing.mybatis.repository;

import com.macrowing.mybatis.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    int save(User user);

    int update(User user);

    int deleteById(int id);

    User selectById(Long id);

    List<User> selectAll();

}
