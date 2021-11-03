package com.backend.Service;

import com.backend.Dao.UserMapper;
import com.backend.Domain.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username))
        {
            throw new RuntimeException("Username not found");
        }
        // 调用方法查询用户
        User user = userMapper.findUserByUsername(username);
        if (user == null)
        {
            throw new RuntimeException("User not exist");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (Role role:userMapper.findRoleByUsername(username))
//        {
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
//        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),"{noop}"+user.getPassword(),authorities);
    }
}
