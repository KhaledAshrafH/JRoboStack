package com.fcai.SoftwareTesting;
import com.fcai.SoftwareTesting.todo.Todo;
import com.fcai.SoftwareTesting.todo.TodoController;
import com.fcai.SoftwareTesting.todo.TodoCreateRequest;
import com.fcai.SoftwareTesting.todo.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @Test
    public void testCreate_ValidRequest_ReturnsOkResponse() {
        // Arrange
        TodoCreateRequest request = new TodoCreateRequest();
        Todo createdTodo = new Todo();
        when(todoService.create(request)).thenReturn(createdTodo);

        // Act
        ResponseEntity<Todo> response = todoController.create(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdTodo, response.getBody());
    }

    @Test
    public void testCreate_InvalidRequest_ReturnsBadRequestResponse() {
        // Arrange
        TodoCreateRequest request = new TodoCreateRequest();
        when(todoService.create(request)).thenThrow(IllegalArgumentException.class);

        // Act
        ResponseEntity<Todo> response = todoController.create(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testRead_ValidId_ReturnsOkResponse() {
        // Arrange
        String id = "todoId";
        Todo todo = new Todo();
        when(todoService.read(id)).thenReturn(todo);

        // Act
        ResponseEntity<Todo> response = todoController.read(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(todo, response.getBody());
    }

    @Test
    public void testRead_InvalidId_ReturnsBadRequestResponse() {
        // Arrange
        String id = "todoId";
        when(todoService.read(id)).thenThrow(IllegalArgumentException.class);

        // Act
        ResponseEntity<Todo> response = todoController.read(id);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testUpdate_ValidIdAndCompletedFlag_ReturnsOkResponse() {
        // Arrange
        String id = "todoId";
        boolean completed = true;
        Todo todo = new Todo();
        when(todoService.update(id, completed)).thenReturn(todo);

        // Act
        ResponseEntity<Todo> response = todoController.update(id, completed);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(todo, response.getBody());
    }

    @Test
    public void testUpdate_InvalidId_ReturnsBadRequestResponse() {
        // Arrange
        String id = "todoId";
        boolean completed = true;
        when(todoService.update(id, completed)).thenThrow(IllegalArgumentException.class);

        // Act
        ResponseEntity<Todo> response = todoController.update(id, completed);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDelete_ValidId_ReturnsOkResponse() {
        // Arrange
        String id = "todoId";

        // Act
        ResponseEntity<?> response = todoController.delete(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(todoService, times(1)).delete(id);
    }

    @Test
    public void testDelete_InvalidId_ReturnsBadRequestResponse() {
        // Arrange
        String id = "todoId";
        doThrow(IllegalArgumentException.class).when(todoService).delete(id);

        // Act
        ResponseEntity<?> response = todoController.delete(id);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testList_ReturnsListOfTodos() {
        // Arrange
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo());
        todos.add(new Todo());
        when(todoService.list()).thenReturn(todos);

        // Act
        ResponseEntity<List<Todo>> response = todoController.list();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(todos, response.getBody());
    }

    @Test
    public void testListCompleted_ReturnsListOfCompletedTodos() {
        // Arrange
        List<Todo> completedTodos = new ArrayList<>();
        completedTodos.add(new Todo());
        completedTodos.add(new Todo());
        when(todoService.listCompleted()).thenReturn(completedTodos);

        // Act
        ResponseEntity<List<Todo>> response = todoController.listCompleted();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(completedTodos, response.getBody());
    }
}
