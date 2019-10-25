package com.macrowing.jpa.controller;

import com.alibaba.fastjson.JSONObject;
import com.macrowing.jpa.model.Person;
import com.macrowing.jpa.repository.PersonRepository;
import com.macrowing.jpa.repository.PersonRepositorySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping(value = "/info")
    public String info(@RequestParam(value = "name") String name) {

        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(0, 10, sort);

        Person person = new Person();
        person.setName(name);
//        Page<Person> personList = personRepository.findByName(person, pageRequest);

//        Page<Person> personList = personRepository.findAll(PersonRepositorySpec.where(person), pageRequest);

        Page<Person> personList = personRepository.findAll(PersonRepositorySpec.whereas(person, "_"), pageRequest);

        if (personList.isEmpty()) {
            return "没有找到此人";
        }
        return JSONObject.toJSONString(personList);
    }

    @PostMapping(value = "/add")
    public String add(@RequestBody Person person) {
        Person p = personRepository.save(person);
        return JSONObject.toJSONString(p);
    }

}
