package com.example.Learning.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    TodoRepository db;
    Optional<TodoModel> getTodoById(String id){
        return db.findById(id);
    }

    Iterable<TodoModel> getTodos(){
        return db.findAll();
    }

    TodoModel addTodo(TodoModel todo){
        return db.save(todo);
    }

    TodoModel updateTodo(TodoModel todo) throws Exception {
        if(!db.existsById(todo.getId())){
            throw new Exception("id not found");
        }
        db.update(todo.getId(),todo.getValue());
        return todo;
    }

}
