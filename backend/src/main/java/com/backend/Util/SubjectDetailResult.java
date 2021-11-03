package com.backend.Util;

import com.backend.Domain.Markrecord;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SubjectDetailResult implements Serializable {
    private String subjname;
    private String subjcode;
    private String descrip;
    private String pre_subject_name;
    private String pre_subject_code;
    private Float practiscore;
    private Float theoryscore;
    private Float diffiscore;
    private List<Markrecord> comment_list;
}
