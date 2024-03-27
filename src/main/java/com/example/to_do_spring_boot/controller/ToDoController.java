package com.example.to_do_spring_boot.controller;

import com.example.to_do_spring_boot.request.ToDoRequest;
import com.example.to_do_spring_boot.response.ToDoResponse;
import com.example.to_do_spring_boot.service.ToDoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todo")
@Slf4j
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @GetMapping("/db")
    public String getDBUrl() {
        return "Database URL: " + databaseUrl;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ToDoResponse>> getAllTasks(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ToDoResponse> responseList = toDoService.getAllTasks(pageable);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/all_pending")
    public ResponseEntity<List<ToDoResponse>> getAllPendingTasks(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ToDoResponse> responseList = toDoService.getAllPendingTasks(pageable);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/all_completed")
    public ResponseEntity<List<ToDoResponse>> getAllCompletedTasks(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ToDoResponse> responseList = toDoService.getAllCompletedTasks(pageable);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/all_progress")
    public ResponseEntity<List<ToDoResponse>> getAllInProgressTasks(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ToDoResponse> responseList = toDoService.getAllInProgressTasks(pageable);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/all_blocked")
    public ResponseEntity<List<ToDoResponse>> getAllInBlockedTasks(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ToDoResponse> responseList = toDoService.getAllInBlockedTasks(pageable);
        return ResponseEntity.ok(responseList);
    }

    @PostMapping("/create")
    public ResponseEntity<ToDoResponse> createTask(@RequestBody ToDoRequest toDoRequest) {
        ToDoResponse response = toDoService.createTask(toDoRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<ToDoResponse> updateTask(@RequestBody ToDoRequest toDoRequest) {
        ToDoResponse response = toDoService.updateTask(toDoRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        toDoService.deleteTask(id);
        return ResponseEntity.ok("Task with id ' " + id + "' Deleted successfully");
    }

}
