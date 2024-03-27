package com.example.to_do_spring_boot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "todo", schema = "public")
public class ToDoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false , name = "start_date")
    private String startDate;
    @Column(nullable = false , name = "due_date")
    private String dueDate;
    @Column(nullable = false, name = "end_date")
    private String endDate;
    @Column(nullable = false)
    private String task;
    @Column(nullable = false)
    private String status;
    @Column(nullable = true)
    private String notes;


}
