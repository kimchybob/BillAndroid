package com.backend.Controller;

import com.backend.Domain.Subject;
import com.backend.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {

    @Autowired
    SubjectService subjectService;



    @ResponseBody
    @GetMapping("/subject")
    public Subject getById(@RequestParam("id") Integer id){
        return subjectService.selectByPrimaryKey(id);
    }
}
