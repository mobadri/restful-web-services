package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User retrieveUser(@PathVariable int id) {

        User user = service.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id-" + id);
        return user;

//        Resource<User>
    }

    @GetMapping(path = "/users/query")
    public String retrieveUserByParam(@RequestParam("param") String name) {

        return name;
    }

    @PostMapping(path = "/users")
    public ResponseEntity saveUser(@Valid @RequestBody User user) {

        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id) {

        User user = service.deleteById(id);
        if (user == null)
            throw new UserNotFoundException("id-" + id);
    }
}
