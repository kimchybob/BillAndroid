package com.backend.Service;

import com.backend.Dao.UserMapper;
import com.backend.Domain.Role;
import com.backend.Domain.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;



@Service
public class UserDetailsService {

    @Resource
    private UserMapper userMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username))
        {
            throw new RuntimeException("用户不能为空");
        }
        // 调用方法查询用户
        User user = userMapper.findUserByUsername(username);
        if (user == null)
        {
            throw new RuntimeException("用户不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (Role role:userMapper.findRoleByUsername(username))
//        {
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
//        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),"{noop}"+user.getPassword(),authorities);
    }
}
