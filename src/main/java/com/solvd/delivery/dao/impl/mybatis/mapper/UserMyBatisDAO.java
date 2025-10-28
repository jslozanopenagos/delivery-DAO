package com.solvd.delivery.dao.impl.mybatis.mapper;

import com.solvd.delivery.dao.interfaces.UserMapper;
import com.solvd.delivery.model.User;
import org.apache.ibatis.session.SqlSession;
import com.solvd.delivery.util.MyBatisConfig;

import java.util.List;

public class UserMyBatisDAO {

    public void insert(User user) {
        try (SqlSession session = MyBatisConfig.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.insert(user);
        }
    }

    public User findById(Long id) {
        try (SqlSession session = MyBatisConfig.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.findById(id);
        }
    }

    public List<User> findAll() {
        try (SqlSession session = MyBatisConfig.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.findAll();
        }
    }

    public User findByEmail(String email) {
        try (SqlSession session = MyBatisConfig.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.findByEmail(email);
        }
    }
}
