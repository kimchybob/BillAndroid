package com.backend.Domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DemoFile implements Serializable {

    public Integer id;
    public String name;
    public String uuid;
    public String path;
    public Integer size;
    public String md5;
    public Integer status;
    public Date createTime;
    public String suffix;
    public Integer timeLength;
}
