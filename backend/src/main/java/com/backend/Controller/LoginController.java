package com.backend.Controller;


import com.backend.Domain.User;
import com.backend.Service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {


    @Autowired
    LoginService loginService;



    @ResponseBody
    @GetMapping("/user")
    public User getById(@RequestParam("id") Integer id){
        return loginService.selectByPrimaryKey(id);
    }


}

