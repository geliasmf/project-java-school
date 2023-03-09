package com.techprimers.security.springsecurityauthserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/home")
public class HelloResource {


    @GetMapping("/principal")
    public Principal user(Principal principal) {
        return principal;
    }
    
    /*@GetMapping("/roomsl")
    public Object user(Principal principal) {
        return principal;
    }*/
    
    @GetMapping
    public String hello() {
        return "Hello World";
    }
    
    

}
