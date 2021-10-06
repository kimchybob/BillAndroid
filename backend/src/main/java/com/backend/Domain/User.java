package com.backend.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Date modifiedTime;
}
