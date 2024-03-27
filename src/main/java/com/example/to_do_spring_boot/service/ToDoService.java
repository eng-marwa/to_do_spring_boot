package com.example.to_do_spring_boot.service;

import com.example.to_do_spring_boot.request.ToDoRequest;
import com.example.to_do_spring_boot.response.ToDoResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ToDoService {
    List<ToDoResponse> getAllTasks(Pageable pageable);

    List<ToDoResponse> getAllCompletedTasks(Pageable pageable);

    List<ToDoResponse> getAllInProgressTasks(Pageable pageable);

    List<ToDoResponse> getAllInBlockedTasks(Pageable pageable);

    List<ToDoResponse> getAllPendingTasks(Pageable pageable);

    ToDoResponse createTask(ToDoRequest toDoRequest);

    void deleteTask(Long id);

    ToDoResponse updateTask(ToDoRequest toDoRequest);
}
