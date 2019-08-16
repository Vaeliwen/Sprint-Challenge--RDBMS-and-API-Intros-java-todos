package com.authenticatedtodo.controller;


import com.authenticatedtodo.model.Todo;
import com.authenticatedtodo.model.User;
import com.authenticatedtodo.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users", produces = {"application/json"})
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/mine", produces = {"application/json"})
    public ResponseEntity<?> getUser(@Valid @RequestBody User user)
    {
        UserDetails u = userService.loadUserByUsername(user.getUsername());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }




    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/users", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newuser) throws URISyntaxException
    {
        newuser =  userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(Long.toString(newuser.getUserid()))
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/userid/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userid)
    {
        userService.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping(value = "/todo/{userid}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addTodoById(@Valid @RequestBody Todo newTodo, @PathVariable long userid ){
        userService.addTodo(userid, newTodo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}