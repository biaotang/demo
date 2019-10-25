package com.macrowing.jpa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "age")
    private Integer age;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "info", length = 1000)
    private String info;

}
