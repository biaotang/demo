package com.macrowing.jpa.repository;

import com.macrowing.jpa.model.Person;
import com.macrowing.jpa.model.Person_;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Entity;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class PersonRepositorySpec {

    public static Specification<Person> where(Person person) {
        return (Specification<Person>) (root, criteriaQuery, cb) -> {
            Predicate p1 = null;
            if (person.getName() != null) {
//                Predicate p2 = cb.equal(root.get(Person_.name), person.getName());
                Predicate p2 = cb.like(root.get(Person_.name), person.getName() + "%");
                if (p1 != null) {
                    p1 = cb.and(p1, p2);
                } else {
                    p1 = p2;
                }
            }
            return p1;
        };
    }


    public static <T> Specification<T> whereas(T t, String suffix) {
        return (Specification<T>) (root, criteriaQuery, cb) -> {
            Predicate p1 = null;

            Field[] fields = t.getClass().getDeclaredFields();

            try {
                for (Field f : fields) {
                    f.setAccessible(Boolean.TRUE);
                    Object obj = f.get(t);
                    if (obj == null)
                        continue;

                    Class t_ = Class.forName(t.getClass().getName() + suffix);
                    Predicate p2 = null;

                    Object fieldVal = t_.getField(f.getName()).get(null);
                    if (!(fieldVal instanceof Attribute)) {
                        throw new IllegalAccessException("field_ type error");
                    }
                    if (f.getType().equals(String.class)) {
                        String str = String.valueOf(obj);
                        SingularAttribute<T, String> attr = (SingularAttribute<T, String>)fieldVal;
                        p2 = cb.like(root.get(attr), str + "%");
                    } else if (f.getType().equals(long.class) || f.getType().equals(Long.class)) {
                        long l = f.getLong(t);
                        SingularAttribute<T, Long> attr = (SingularAttribute<T, Long>)fieldVal;
                        p2 = cb.equal(root.get(attr), l);
                    } else if (f.getType().equals(int.class) || f.getType().equals(Integer.class)) {
                        int l = f.getInt(t);
                        SingularAttribute<T, Integer> attr = (SingularAttribute<T, Integer>)fieldVal;
                        p2 = cb.equal(root.get(attr), l);
                    } else if (f.getType().equals(boolean.class) || f.getType().equals(Boolean.class)) {
                        boolean l = f.getBoolean(t);
                        SingularAttribute<T, Boolean> attr = (SingularAttribute<T, Boolean>)fieldVal;
                        p2 = cb.equal(root.get(attr), l);
                    }
                    p1 = (p1 != null) ? cb.and(p1, p2) : p2;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return p1;
        };
    }

}
