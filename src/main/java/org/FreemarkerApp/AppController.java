package org.FreemarkerApp;

import org.FreemarkerApp.entity.Owner;
import org.FreemarkerApp.entity.Pet;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-freemarker
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 23/03/18
 * Time: 06.03
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class AppController {

    static RestTemplate restTemplate = new RestTemplate();
    static{
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("Alexander", "12345"));
    }

    @RequestMapping("/")
    public String index(Model model) {

        return "redirect:/ownersWithPets";
    }

    @RequestMapping("/ownersWithPets")
    public ModelAndView ownerWithPets() {

        /*List<Owner> owners = new ArrayList<Owner>(){{
            add(new Owner(1, "Вася").addPet(new Pet(1, "Мурзач")).addPet(new Pet(3, "Стэла")));
            add(new Owner(2, "Коля").addPet(new Pet(2, "Дружок")));
        }};*/



        ResponseEntity<List<Owner>> ownersResponse = restTemplate.exchange("http://localhost:8888/api/findAllWithPets",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Owner>>() {});
        List<Owner> owners = ownersResponse.getBody();

        Map<String, Object> params = new HashMap<>();
        params.put("owners", owners);

        List<Pet> pets = new ArrayList<>();
        for (Owner owner: owners){
            if (owner.getPets()!=null) {
                owner.getPets().forEach(pet->pet.setOwnerName(owner.getFirstName()));
                pets.addAll(owner.getPets());
            }
        }
        params.put("pets", pets);



        return new ModelAndView("ownersWithPets", params);
    }


    @RequestMapping(value = "/addOwner", method = RequestMethod.POST)
    public String addOwner(Model model, @ModelAttribute("owner") Owner owner) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        // Data attached to the request.

        HttpEntity<Owner> requestBody = new HttpEntity<>(owner, headers);

        // Send request with POST method.
        Owner o = restTemplate.postForObject("http://localhost:8888/api/addOwnerWithPets", requestBody, Owner.class);
        System.out.println("created!!! = " + o);

        return "redirect:/ownersWithPets";
    }

    @RequestMapping(value = "/addPet", method = RequestMethod.POST)
    public String addPet(Model model, @ModelAttribute("pet1") Pet pet, @ModelAttribute("petOwner") Owner owner) {
        System.out.println("model = " + model);
        System.out.println("pet = " + pet);
        System.out.println("owner = " + owner);


        ResponseEntity<List<Owner>> ownerResponse = restTemplate.exchange("http://localhost:8888/api/findBy?firstName="+owner.getFirstName(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Owner>>() {});
        List<Owner> owners = ownerResponse.getBody();
        pet.setOwner_id(owners.get(0).getId());


        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        // Data attached to the request.



        HttpEntity<Pet> requestBody = new HttpEntity<>(pet, headers);

        // Send request with POST method.
        Pet o = restTemplate.postForObject("http://localhost:8888/api/addPet", requestBody, Pet.class);
        System.out.println("created!!! = " + o);

        return "redirect:/ownersWithPets";
    }

}
