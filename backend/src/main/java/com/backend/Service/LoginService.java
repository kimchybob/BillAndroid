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

    public int getUserIdByUsername(String username){
        User userByUsername = UserMapper.findUserByUsername(username);
        if(userByUsername==null){
            return 77777;
        }
        return userByUsername.getUid();
    }

    public int insert(User user){
        User userByUsername = UserMapper.findUserByUsername(user.getUsername());
        if(userByUsername!=null){
            return 77777;
        }
        User userByEmail = UserMapper.findUserByEmail(user.getEmail());
        if(userByEmail!=null){
            return 88888;
        }

        return UserMapper.insert(user);
    }
}
