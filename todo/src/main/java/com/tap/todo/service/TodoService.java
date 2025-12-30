package com.tap.todo.service;

import com.tap.todo.entity.Todo;

import java.util.List;

public interface TodoService {

    Todo createTodo(Todo todo);

    List<Todo> getAllTodos();

    Todo getTodoById(Long id);

    Todo updateTodo(Long id, Todo todo);

    void deleteTodo(Long id);
}
