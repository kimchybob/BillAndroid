package com.backend.Controller;


import com.backend.Domain.User;
import com.backend.Service.LoginService;
import com.backend.Util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

//    @ResponseBody
//    @GetMapping("/user")
//    public User getById(@RequestParam("id") Integer id){
//        return loginService.selectByPrimaryKey(id);
//    }

    @PostMapping("/api/signup")
    public AjaxResult insertUser(@RequestBody User user){
        if(user==null||user.getUsername()==null||user.getPassword()==null)
            return AjaxResult.error("Input Missing!");
        int userId=loginService.insert(user);
        if (userId == 0)
            return AjaxResult.error("Signup fail!");
        return AjaxResult.success("Successfully signup!");
    }

}

