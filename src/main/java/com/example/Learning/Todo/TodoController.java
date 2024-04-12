package com.example.Learning.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    TodoService service;

    @GetMapping("/byID/{id}")
    Optional<TodoModel> getTodo(@PathVariable String id) {
        return service.getTodoById(id);
    }

    @GetMapping("/")
    Iterable<TodoModel> getTodos() {
        return service.getTodos();
    }

    @PostMapping("/")
    ResponseEntity<TodoModel> addTodo(@RequestBody TodoModel todo) {
        if (todo.getId() == null || todo.getValue() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.addTodo(todo), HttpStatus.OK);
    }

    @PatchMapping("/")
    ResponseEntity<?> updateTodo(@RequestBody TodoModel todo) {
        try {
            if (todo.getId() == null || todo.getValue() == null) {
                throw new IllegalArgumentException("Todo properties cannot be null");
            }

            return new ResponseEntity<>(service.updateTodo(todo), HttpStatus.OK);

        } catch (IllegalArgumentException err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
