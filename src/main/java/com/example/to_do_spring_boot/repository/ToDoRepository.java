package com.example.to_do_spring_boot.repository;

import com.example.to_do_spring_boot.enums.TaskStatus;
import com.example.to_do_spring_boot.model.ToDoModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoModel, Long> {
     List<ToDoModel> findAllByStatus(TaskStatus status, Pageable pageable);

}
