package de.telran.lesson3.controller_layer;

import de.telran.lesson3.domain_layer.entity.jpa.JpaUser;
import de.telran.lesson3.service_layer.jpa.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private JpaUserService service;

    @GetMapping(value = "/all")
    public List<JpaUser> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/name/{name}")
    public UserDetails getByName(@PathVariable String name) {
        return service.loadUserByUsername(name);
    }


    @PostMapping(value = "/add")
    public JpaUser add(@RequestBody JpaUser user) {
        return service.saveUser(user);
    }
}