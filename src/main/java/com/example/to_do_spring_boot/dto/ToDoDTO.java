package com.example.to_do_spring_boot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDTO {

    private Long id;
    private String startDate;
    private String dueDate;
    private String endDate;
    private String task;
    private String status;
    private String notes;
}
