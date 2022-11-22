package com.RL.Test;

import com.RL.domain.*;
import com.RL.repository.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AuthorCRUDTest {
    @Autowired
    private AuthorRepository repository;
    String name = "Zekeriya Kaya test0111";


    @Test
    public void authorCreateTest() {
        Author author = new Author();
        author.setName(name);
        author.setBuiltIn(false);
        repository.save(author);

    }

    @Test
    public void authorUpdateTest() {
        List<Author> objs = repository.findAll();
        for (var each : objs) {
            if (each.getName().equals(name)) {
                each.setName(name + " Update");
                repository.save(each);
            }
        }
    }

    @Test
    public void authorDeleteTest() {
        var objs= repository.findAll();
        for (var each : objs) {
            if (each.getName().equals(name + " Update") || each.getName().equals(name)) {
                repository.deleteById(each.getId());
            }
        }
    }

}