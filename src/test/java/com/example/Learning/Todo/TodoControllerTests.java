package com.example.Learning.Todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TodoControllerTests {

    @InjectMocks
    TodoController todoController;

    @Mock
    TodoService todoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTodo() {
        String id = "1";
        TodoModel todoModel = new TodoModel(id, "Test Todo");

        when(todoService.getTodoById(id)).thenReturn(Optional.of(todoModel));

        Optional<TodoModel> result = todoController.getTodo(id);

        assertTrue(result.isPresent());
        assertEquals(todoModel, result.get());
    }

    @Test
    void testGetTodos() {
        when(todoService.getTodos()).thenReturn(Arrays.asList(new TodoModel("1", "Test Todo 1"), new TodoModel("2", "Test Todo 2")));

        Iterable<TodoModel> result = todoController.getTodos();

        assertNotNull(result);
        assertEquals(2, ((List<TodoModel>) result).size());
    }

    @Test
    void testAddTodo() {
        TodoModel todoModel = new TodoModel("1", "Test Todo");

        when(todoService.addTodo(todoModel)).thenReturn(todoModel);

        ResponseEntity<TodoModel> response = todoController.addTodo(todoModel);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(todoModel, response.getBody());
    }

    @Test
    void testAddTodoWithNullValues() {
        TodoModel todoModel = new TodoModel(null, null);

        ResponseEntity<TodoModel> response = todoController.addTodo(todoModel);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testUpdateTodo() throws Exception {
        TodoModel todoModel = new TodoModel("1", "Updated Test Todo");

        when(todoService.updateTodo(todoModel)).thenReturn(todoModel);

        ResponseEntity<?> response = todoController.updateTodo(todoModel);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateTodoWithNullValues() {
        TodoModel todoModel = new TodoModel(null, null);

        ResponseEntity<?> response = todoController.updateTodo(todoModel);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}

