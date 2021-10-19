package com.backend.Service;


import com.backend.Domain.Markrecord;
import com.backend.Domain.Subject;
import com.backend.Util.SubjectDetailResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubjectService {

    @Resource
    com.backend.Dao.SubjectMapper SubjectMapper;
    @Resource
    MarkrecordService markrecordService;

    public Subject selectByPrimaryKey(Integer id){
        return SubjectMapper.selectByPrimaryKey(id);
    }
    public Subject selectByName(String name){return SubjectMapper.selectByName(name);}
    public Subject selectByCode(String code){return SubjectMapper.selectByCode(code);}
    public List<Subject> selectByCourse(String course){return SubjectMapper.selectByCourse(course);}
    public List<Subject> selectByUid(Integer uid){ return SubjectMapper.selectByUid(uid);}

    public SubjectDetailResult getSubjDetailBySid(int sid) {
        Subject sub = SubjectMapper.selectByPrimaryKey(sid);
        SubjectDetailResult res = new SubjectDetailResult();
        res.setSubjname(sub.getSubjname());
        res.setSubjcode(sub.getSubjcode());
        res.setDescrip(sub.getDescrip());
        res.setDiffiscore(sub.getDiffiscore());
        res.setPractiscore(sub.getPractiscore());
        res.setTheoryscore(sub.getTheoryscore());

        if(sub.getPrisid() != null){
            Integer preId = Integer.parseInt(sub.getPrisid());
            Subject preSub = SubjectMapper.selectByPrimaryKey(preId);
            res.setPre_subject_code(preSub.getSubjcode());
            res.setPre_subject_name(preSub.getSubjname());
        } else {
            res.setPre_subject_code("null");
            res.setPre_subject_name("null");
        }

        List<Markrecord> markrecords = markrecordService.selectBySubjId(sid);
        res.setComment_list(markrecords);
        return res;
    }
}
