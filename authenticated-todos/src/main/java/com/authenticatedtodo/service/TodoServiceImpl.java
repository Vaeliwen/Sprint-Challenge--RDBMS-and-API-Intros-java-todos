package com.authenticatedtodo.service;

import com.authenticatedtodo.model.Todo;
import com.authenticatedtodo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service("todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todorepos;

    @Transactional
    @Override
    public Todo save(Todo todo){
        Todo newTodo = new Todo();
        newTodo.setDescription(todo.getDescription());
        newTodo.setUser(todo.getUser());

        return todorepos.save(newTodo);
    }

    @Transactional
    @Override
    public Todo update(Todo todo, long id){
        Todo currentTodo = todorepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (todo.getDescription() != null){
            currentTodo.setDescription(todo.getDescription());
        }
        if (todo.getDatestarted() != null){
            currentTodo.setDatestarted(todo.getDatestarted());
        }
        if (todo.getUser() != null){
            currentTodo.setUser(todo.getUser());
        }

        currentTodo.setCompleted(todo.isCompleted());

        return todorepos.save(currentTodo);

    }

}
