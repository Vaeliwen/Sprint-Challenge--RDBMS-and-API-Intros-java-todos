package com.authenticatedtodo.controller;

import com.authenticatedtodo.service.TodoService;
import com.authenticatedtodo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping(value = "todoid/{todoid}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateTodo(@Valid @RequestBody Todo todoUpdate, @PathVariable long todoid){
        todoService.update(todoUpdate, todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
