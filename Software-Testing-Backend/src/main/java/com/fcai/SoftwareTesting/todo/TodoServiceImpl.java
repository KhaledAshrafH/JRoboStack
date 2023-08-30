package com.fcai.SoftwareTesting.todo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private static List<Todo> todos;

    public TodoServiceImpl() {
        todos = new ArrayList<Todo>();
    }

    @Override
    public Todo create(TodoCreateRequest todo) {
        if (todo == null) {
            throw new IllegalArgumentException("Todo cannot be null");
        }

        if (todo.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Todo title cannot be empty");
        }

        if (todo.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Todo description cannot be empty");
        }

        String id = String.valueOf(todos.size() + 1);
        Todo newTodo = new Todo(
                id,
                todo.getTitle(),
                todo.getDescription(),
                false
        );

        todos.add(newTodo);

        return newTodo;
    }

    @Override
    public Todo read(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Todo id cannot be null");
        }

        if (id.isEmpty()) {
            throw new IllegalArgumentException("Todo id cannot be empty");
        }

        Todo todo = null;
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getId().equals(id)) {
                todo = todos.get(i);
            }
        }

        if (todo == null) {
            throw new IllegalArgumentException("Todo not found");
        }

        return todo;
    }

    @Override
    public Todo update(String id, boolean completed) {
        Todo todo = read(id);
        todo.setCompleted(completed);
        return todo;
    }

    @Override
    public void delete(String id) {
        Todo todo = read(id);
        todos.remove(todo);
    }

    @Override
    public List<Todo> list() {
        if (todos == null) {
            throw new IllegalArgumentException("Todo list cannot be null");
        }

        return todos;
    }

    @Override
    public List<Todo> listCompleted() {
        if (todos == null) {
            throw new IllegalArgumentException("Todo list cannot be null");
        }

        List<Todo> completedTodos = new ArrayList<>();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).isCompleted()) {
                completedTodos.add(todos.get(i));
            }
        }

        return completedTodos;
    }
}
