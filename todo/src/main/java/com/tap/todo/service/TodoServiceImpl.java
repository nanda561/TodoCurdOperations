package com.tap.todo.service;

import com.tap.todo.entity.Todo;
import com.tap.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;

    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Todo createTodo(Todo todo) {
        return repository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    @Override
    public Todo getTodoById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found"));
    }

    @Override
    public Todo updateTodo(Long id, Todo todo) {
        Todo existing = getTodoById(id);

        existing.setTask(todo.getTask());
        existing.setDescription(todo.getDescription());
        existing.setCompleted(todo.isCompleted());

        return repository.save(existing);
    }

    @Override
    public void deleteTodo(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }
        repository.deleteById(id);
    }
}
