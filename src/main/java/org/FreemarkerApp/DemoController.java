package org.FreemarkerApp;

import org.FreemarkerApp.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DemoController {

    @Autowired
    private RepositoryService repositoryService;

    @GetMapping("/")
    public String index() {
        return "persons";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String hello(Model model, @RequestParam(defaultValue = "") String searchName) {
        List<List<String>> persons = repositoryService.getRepository();
        List<List<String>> filterList = persons.stream()
                .filter(p -> p.get(0).contains(searchName))
                .collect(Collectors.toList());
        model.addAttribute("persons", filterList);
        model.addAttribute("lastSearch", searchName);
        return "persons";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Model model, @ModelAttribute("person") Person person) {
        List<List<String>> persons = repositoryService.addPerson(person);
        model.addAttribute("persons", persons);
        return "persons";
    }
}