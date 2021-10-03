package com.backend.Service;

import com.sblearning.Dao.TestMapper;
import com.sblearning.Domain.DemoFile;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestService {

    @Resource
    TestMapper testMapper;

    public DemoFile getById(Integer id){
        return testMapper.findById(id);
    }
}
