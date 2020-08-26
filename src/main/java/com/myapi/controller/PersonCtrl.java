package com.myapi.conroller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import com.myapi.model.Person;

@Controller("/persons")
@Secured(SecurityRule.IS_ANONYMOUS)
public class PersonCtrl {

    List<Person> persons = new ArrayList<>();

    @Get
    public List<Person> findAll() {
        return persons;
    }

    @Get("/data")
    public HashMap<String, Integer> data() {
        HashMap<String, Integer> x = new HashMap<>();
        x.put("a", 1);
        x.put("b", 2);
        return x;
    }
}