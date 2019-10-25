package com.macrowing.mybatis.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class User implements Serializable {

    private Long id;

    private Integer age;

    private String name;

    private String info;

}
