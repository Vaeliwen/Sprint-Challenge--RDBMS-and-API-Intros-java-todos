package com.authenticatedtodo.service;

import com.authenticatedtodo.model.Todo;
import com.authenticatedtodo.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    List<User> findAll();

    UserDetails loadUserByUsername(String username);

    void delete(long id);

    User save(User user);

    User addTodo(long id, Todo todo);

}