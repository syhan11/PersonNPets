package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class HomeCtrl {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    PetRepository petRepository;

    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("pets", petRepository.findAll());
        model.addAttribute("people", personRepository.findAll());
        return ("index");
    }



    @RequestMapping("/addpet")
    public String addPet(Model model)
    {
        model.addAttribute("pet", new Pet());
        model.addAttribute("people", personRepository.findAll());
        return "addpet";
    }

    @RequestMapping("/processpet")
    public String savePet(@ModelAttribute("pet") Pet pet,
                          Model model)
    {
        petRepository.save(pet);
        return "redirect:/";
    }

    @PostConstruct()
    public void fillTable(){
        Person p = new Person();
        p.setName("John Smith");
        personRepository.save(p);

        p = new Person();
        p.setName("Owen Richards");
        personRepository.save(p);

        p = new Person();
        p.setName("Ama Baidoo");
        personRepository.save(p);

    }

}
