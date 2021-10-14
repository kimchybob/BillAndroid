package com.backend.Service;


import com.backend.Domain.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubjectService {

    @Resource
    com.backend.Dao.SubjectMapper SubjectMapper;

    public Subject selectByPrimaryKey(Integer id){
        return SubjectMapper.selectByPrimaryKey(id);
    }
    public Subject selectByName(String name){return SubjectMapper.selectByName(name);}
    public Subject selectByCode(String code){return SubjectMapper.selectByCode(code);}
    public List<Subject> selectByCourse(String course){return SubjectMapper.selectByCourse(course);}
    public List<Subject> selectByUid(Integer uid){ return SubjectMapper.selectByUid(uid);}

}
