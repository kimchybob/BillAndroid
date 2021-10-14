package com.backend;

import com.backend.Controller.SubjectController;
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
}
