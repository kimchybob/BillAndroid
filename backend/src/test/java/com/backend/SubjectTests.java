package com.backend;

import com.backend.Controller.SubjectController;
import com.backend.Domain.Markrecord;
import com.backend.Util.AjaxResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SubjectTests {
    @Autowired
    SubjectController subjectController;

    @Test
    public void getListByUidTest(){
        int uid=1;
        AjaxResult result=subjectController.getListByUid(uid);
        System.out.println(result);
    }

    @Test
    public void setSubjCommentTest(){
        Markrecord record=Markrecord.builder().subjid(1).practiscore((float) 1).theoryscore((float) 2)
                .diffiscore((float) 3).comment("not so good").comuid(1).build();
        AjaxResult result=subjectController.setSubjComment(record);
        System.out.println(result);
    }
}
