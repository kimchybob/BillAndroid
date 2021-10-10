package com.backend.Service;


import com.backend.Domain.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SubjectService {

    @Resource
    com.backend.Dao.SubjectMapper SubjectMapper;

    public Subject selectByPrimaryKey(Integer id){
        return SubjectMapper.selectByPrimaryKey(id);
    }
}
