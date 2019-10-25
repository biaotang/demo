package com.macrowing.jpa.repository;

import com.macrowing.jpa.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    @Query("select p from Person p where p.name like :#{#person.name}%")
    Page<Person> findByName(@Param("person") Person person, Pageable page);

}
