package com.backend.Service;

import com.backend.Dao.UserMapper;
import com.backend.Domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginService {

    @Resource
    UserMapper UserMapper;

    public User selectByPrimaryKey(Integer id){
        return UserMapper.selectByPrimaryKey(id);
    }

    public int insert(User user){
        return UserMapper.insert(user);
    }
}
