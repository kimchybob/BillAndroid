package com.backend.Service;

import com.backend.Dao.TestMapper;
import com.backend.Domain.DemoFile;
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
