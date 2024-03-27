package com.example.to_do_spring_boot.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ToDoRequest {
    private Long id;
    private String startDate;
    private String dueDate;
    private String endDate;
    private String task;
    private String status;
    private String notes;
}
