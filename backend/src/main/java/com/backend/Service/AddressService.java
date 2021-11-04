package com.backend.Service;


import com.backend.Dao.MapMapper;
import com.backend.Dao.SubjectMapMapper;
import com.backend.Domain.Map;
import com.backend.Domain.SubjectMap;
import com.backend.Util.AddressResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    @Resource
    SubjectMapMapper subjectMapMapper;

    @Resource
    MapMapper mapMapper;

    public List<AddressResult> getAddressBySid(Integer sid) {
        List<SubjectMap> subjectMaps = subjectMapMapper.selectBySubjId(sid);
        List<AddressResult> res = new ArrayList<>();
        for(SubjectMap sm: subjectMaps){
            Map map = mapMapper.selectByPrimaryKey(sm.getAddrid());
            AddressResult subRes = new AddressResult();
            subRes.setBuildname(map.getBuildname());
            subRes.setAddress(map.getAddress());
            subRes.setType(sm.getType());
            subRes.setLatlng(map.getLatlng());
            res.add(subRes);
        }
        return res;
    }
}
