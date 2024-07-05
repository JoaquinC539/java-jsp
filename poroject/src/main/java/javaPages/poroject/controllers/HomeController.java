package javaPages.poroject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javaPages.poroject.models.Person;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping()
    public String index(Model model ){
        model.addAttribute("name", "Steve");
        List<String> names=new ArrayList<String>();
        names.add("Joe");
        names.add("Jack");
        names.add("Jake");
        Person person=new Person("Betty", 23);
        model.addAttribute("names", names);
        model.addAttribute("person", person);  
        model.addAttribute("bool", true);
         
        return "home";
        
    } 
    


}
