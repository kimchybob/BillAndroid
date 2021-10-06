package com.backend.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class Role implements Serializable {
    private Long id;
    private String username;
    private String name = "user";
}
