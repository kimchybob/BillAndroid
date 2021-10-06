package com.backend.Dao;

import com.backend.Domain.Role;
import com.backend.Domain.User;

import java.util.List;

public interface UserMapper {
    public User findUserByUsername(String username);
    public List<Role> findRoleByUsername(String username);
}
