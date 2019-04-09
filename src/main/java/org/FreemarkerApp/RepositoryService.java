package org.FreemarkerApp;

import org.FreemarkerApp.entity.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RepositoryService {

    private static List<List<String>> repository = new ArrayList<>();

    public List<List<String>> getRepository() {
        return repository;
    }

    public List<List<String>> addPerson(Person person) {
        repository.add(Arrays.asList(person.getFirstName(), person.getAge().toString()));
        return repository;
    }
}