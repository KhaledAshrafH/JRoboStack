package com.fcai.SoftwareTesting;
import com.fcai.SoftwareTesting.todo.Todo;
import com.fcai.SoftwareTesting.todo.TodoCreateRequest;
import com.fcai.SoftwareTesting.todo.TodoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoServiceImplTest {

    private TodoServiceImpl todoService;

    @BeforeEach
    void setUp() {
        todoService = new TodoServiceImpl();
    }

    // 1,3,5,7,8
    @Test
    void create_ValidTodo_ReturnsCreatedTodo() {
        // Arrange
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Title", "Description");

        // Act
        Todo createdTodo = todoService.create(todoCreateRequest);

        // Assert
        assertNotNull(createdTodo);
        assertNotNull(createdTodo.getId());
        assertEquals(todoCreateRequest.getTitle(), createdTodo.getTitle());
        assertEquals(todoCreateRequest.getDescription(), createdTodo.getDescription());
        assertFalse(createdTodo.isCompleted());
    }

    // 1 2
    @Test
    void create_NullTodo_ThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> todoService.create(null));
    }

    //1 3 4
    @Test
    void create_EmptyTitle_ThrowsIllegalArgumentException() {
        // Arrange
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("", "Description");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> todoService.create(todoCreateRequest));
    }

    // 1 3 5 6
    @Test
    void create_EmptyDescription_ThrowsIllegalArgumentException() {
        // Arrange
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Title", "");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> todoService.create(todoCreateRequest));
    }

    @Test
    void read_ExistingTodoId_ReturnsTodo() {
        // Arrange
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Title", "Description");
        Todo createdTodo = todoService.create(todoCreateRequest);

        // Act
        Todo readTodo = todoService.read(createdTodo.getId());

        // Assert
        assertNotNull(readTodo);
        assertEquals(createdTodo, readTodo);
    }

    @Test
    void read_NonExistingTodoId_ThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> todoService.read("non_existing_id"));
    }

    @Test
    void read_NullTodoId_ThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> todoService.read(null));
    }

    @Test
    void read_EmptyTodoId_ThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> todoService.read(""));
    }

    @Test
    void update_ExistingTodoId_ReturnsUpdatedTodo() {
        // Arrange
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Title", "Description");
        Todo createdTodo = todoService.create(todoCreateRequest);
        // Act
        Todo updatedTodo = todoService.update(createdTodo.getId(), true);

        // Assert
        assertNotNull(updatedTodo);
        assertEquals(createdTodo.getId(), updatedTodo.getId());
        assertEquals(createdTodo.getTitle(), updatedTodo.getTitle());
        assertEquals(createdTodo.getDescription(), updatedTodo.getDescription());
        assertTrue(updatedTodo.isCompleted());
    }

    @Test
    void delete_ExistingTodoId_TodoIsDeleted() {
        // Arrange
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Title", "Description");
        Todo createdTodo = todoService.create(todoCreateRequest);

        // Act
        todoService.delete(createdTodo.getId());

        // Assert
        assertThrows(IllegalArgumentException.class, () -> todoService.read(createdTodo.getId()));
    }

    @Test
    void list_ReturnsListOfTodos() {
        // Arrange
        TodoCreateRequest todoCreateRequest1 = new TodoCreateRequest("Title 1", "Description 1");
        TodoCreateRequest todoCreateRequest2 = new TodoCreateRequest("Title 2", "Description 2");
        todoService.create(todoCreateRequest1);
        todoService.create(todoCreateRequest2);

        // Act
        List<Todo> todos = todoService.list();

        // Assert
        assertNotNull(todos);
        assertEquals(2, todos.size());
    }

    @Test
    void listCompleted_ReturnsListOfCompletedTodos() {
        // Arrange
        TodoCreateRequest todoCreateRequest1 = new TodoCreateRequest("Title 1", "Description 1");
        TodoCreateRequest todoCreateRequest2 = new TodoCreateRequest("Title 2", "Description 2");
        todoService.create(todoCreateRequest1);
        todoService.create(todoCreateRequest2);
        todoService.update(todoService.list().get(0).getId(), true);

        // Act
        List<Todo> completedTodos = todoService.listCompleted();

        // Assert
        assertNotNull(completedTodos);
        assertEquals(1, completedTodos.size());
        assertTrue(completedTodos.get(0).isCompleted());
    }


}
