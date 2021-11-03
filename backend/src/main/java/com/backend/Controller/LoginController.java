package com.backend.Controller;


import com.backend.Domain.User;
import com.backend.Service.LoginService;
import com.backend.Util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

//    @ResponseBody
//    @GetMapping("/user")
//    public User getById(@RequestParam("id") Integer id){
//        return loginService.selectByPrimaryKey(id);
//    }

    @GetMapping("/getUidByUsername")
    public AjaxResult login(@RequestParam("username") String username){

        int userIdByUsername = loginService.getUserIdByUsername(username);
        return AjaxResult.success(userIdByUsername);
    }
    @PostMapping("/api/signup")
    public AjaxResult insertUser(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("email") String email){
        if(username==null||password==null||email==null)
            return AjaxResult.error("Input Missing!");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setLasttime(new Date());
        int userId=loginService.insert(user);
        if (userId == 77777)
            return AjaxResult.error("Username already exists!");
        if (userId == 88888)
            return AjaxResult.error("Email already exists!");

        return AjaxResult.success("Successfully signup!");
    }

}

