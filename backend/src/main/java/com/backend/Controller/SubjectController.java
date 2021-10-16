package com.backend.Controller;

import com.backend.Domain.Markrecord;
import com.backend.Domain.Subject;
import com.backend.Service.MarkrecordService;
import com.backend.Service.SubjectService;
import com.backend.Util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    MarkrecordService markrecordService;

    @ResponseBody
    @GetMapping("/subject")
    public Subject getById(@RequestParam("id") Integer id){
        return subjectService.selectByPrimaryKey(id);
    }

    @GetMapping("/subject/getListByUid/{uid}")
    public AjaxResult getListByUid(@PathVariable("uid") int uid){
        if(uid==0)
            return AjaxResult.error("Input Empty!");
        List<Subject> subjList=subjectService.selectByUid(uid);
        if(subjList.isEmpty()){
            return AjaxResult.warn("No corresponding subject list here.");
        }
        return AjaxResult.success(subjList);
    }

    @GetMapping("/subject/getListByCourse/{course}")
    public AjaxResult getListByCourse(@PathVariable("course") String course){
        if(course==null||course.isEmpty())
            return AjaxResult.error("Input Empty!");
        List<Subject> subjList=subjectService.selectByCourse(course);
        if(subjList.isEmpty()){
            return AjaxResult.warn("No corresponding subject list here.");
        }
        return AjaxResult.success(subjList);
    }

    @GetMapping("/subject/getSubjByName/{name}")
    public AjaxResult getSubjByName(@PathVariable("name") String name){
        if(name==null||name.isEmpty())
            return AjaxResult.error("Input Empty!");
        Subject subject=subjectService.selectByName(name);
        if(subject==null||subject.getSid()==0){
            return AjaxResult.warn("No corresponding subject here.");
        }
        return AjaxResult.success(subject);
    }

    @GetMapping("/subject/getSubjByCode/{code}")
    public AjaxResult getSubjByCode(@PathVariable("code") String code){
        if(code==null||code.isEmpty())
            return AjaxResult.error("Input Empty!");
        Subject subject=subjectService.selectByCode(code);
        if(subject==null||subject.getSid()==0){
            return AjaxResult.warn("No corresponding subject here.");
        }
        return AjaxResult.success(subject);
    }

    @PostMapping("/subject/setSubjComment")
    public AjaxResult setSubjComment(@RequestBody Markrecord record){
        if(record==null||record.getSubjid()==0)
            return AjaxResult.error("Input Empty!");
        int result=markrecordService.setSubjComment(record);
        if (result != 0)
            return AjaxResult.success("Successful insert!");
        else
            return AjaxResult.error("Insert fail!");

    }


}
