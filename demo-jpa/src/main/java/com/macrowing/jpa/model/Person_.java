package com.macrowing.jpa.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

//@StaticMetamodel(Person.class)
public class Person_ {

    public static  volatile SingularAttribute<Person, Long> id;

    public static volatile SingularAttribute<Person, Integer> age;

    public static volatile SingularAttribute<Person, String> name;

}
