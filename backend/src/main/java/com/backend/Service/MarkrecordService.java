package com.backend.Service;

import com.backend.Dao.MarkrecordMapper;
import com.backend.Domain.Markrecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MarkrecordService {
    @Resource
    private MarkrecordMapper markrecordMapper;

    public int setSubjComment(Markrecord markrecord){
        DateTimeFormatter dateForm=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime datetime= LocalDateTime.now();
        markrecord.setLasttime(datetime);
        return markrecordMapper.insertSelective(markrecord);}


    public List<Markrecord> selectBySubjId(Integer id){
        return markrecordMapper.selectBySubjId(id);
    }

}
