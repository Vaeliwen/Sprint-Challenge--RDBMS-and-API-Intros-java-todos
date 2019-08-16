package com.authenticatedtodo.service;

import com.authenticatedtodo.model.Todo;

public interface TodoService {

    Todo update(Todo todo, long id);

    Todo save(Todo todo);

}
