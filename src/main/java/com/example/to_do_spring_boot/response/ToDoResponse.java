package com.example.to_do_spring_boot.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoResponse {
    private Long id;
    private String startDate;
    private String dueDate;
    private String endDate;
    private String task;
    private String status;
    private String createdBy;
    private String updatedBy;
    private String notes;


}
