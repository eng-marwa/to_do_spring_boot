package com.example.to_do_spring_boot.service.service_impl;

import com.example.to_do_spring_boot.enums.TaskStatus;
import com.example.to_do_spring_boot.model.ToDoModel;
import com.example.to_do_spring_boot.repository.ToDoRepository;
import com.example.to_do_spring_boot.request.ToDoRequest;
import com.example.to_do_spring_boot.response.ToDoResponse;
import com.example.to_do_spring_boot.service.ToDoService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ToDoServiceImp implements ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Cacheable(value = "allTasks" , key = "#pageable")
    @Override
    public List<ToDoResponse> getAllTasks(Pageable pageable) {
        List<ToDoModel> toDoModelList = toDoRepository.findAll(pageable).getContent();
        return toDoModelList.stream().map(toDoModel -> modelMapper.map(toDoModel, ToDoResponse.class)).collect(Collectors.toList());
    }

    @Cacheable(value = "completedTasks")
    @Override
    public List<ToDoResponse> getAllCompletedTasks(Pageable pageable) {
        List<ToDoModel> toDoModelList = toDoRepository.findAllByStatus(TaskStatus.COMPLETED, pageable);
        return toDoModelList.stream().map(toDoModel -> modelMapper.map(toDoModel, ToDoResponse.class)).collect(Collectors.toList());

    }

    @Cacheable(value = "inProgressTasks")
    @Override
    public List<ToDoResponse> getAllInProgressTasks(Pageable pageable) {
        List<ToDoModel> toDoModelList = toDoRepository.findAllByStatus(TaskStatus.IN_PROGRESS, pageable);
        return toDoModelList.stream().map(toDoModel -> modelMapper.map(toDoModel, ToDoResponse.class)).collect(Collectors.toList());

    }

    @Cacheable(value = "blockedTasks")
    @Override
    public List<ToDoResponse> getAllInBlockedTasks(Pageable pageable) {
        List<ToDoModel> toDoModelList = toDoRepository.findAllByStatus(TaskStatus.BLOCKED, pageable);
        return toDoModelList.stream().map(toDoModel -> modelMapper.map(toDoModel, ToDoResponse.class)).collect(Collectors.toList());

    }

    @Cacheable(value = "pendingTasks")
    @Override
    public List<ToDoResponse> getAllPendingTasks(Pageable pageable) {
        List<ToDoModel> toDoModelList = toDoRepository.findAllByStatus(TaskStatus.PENDING, pageable);
        return toDoModelList.stream().map(toDoModel -> modelMapper.map(toDoModel, ToDoResponse.class)).collect(Collectors.toList());

    }

    @Override
    public ToDoResponse createTask(ToDoRequest toDoRequest) {
        ToDoModel obj = new ToDoModel();
        obj.setTask(toDoRequest.getTask());
        obj.setStatus(toDoRequest.getStatus());
        obj.setStartDate(toDoRequest.getStartDate());
        obj.setDueDate(toDoRequest.getDueDate());
        obj.setEndDate(toDoRequest.getEndDate());
        obj.setNotes(toDoRequest.getNotes());
        return modelMapper.map(toDoRepository.save(obj), ToDoResponse.class);
    }

    @Caching(evict = {
            @CacheEvict(value = "allTasks", allEntries = true , key = "#toDoRequest.id"),
            @CacheEvict(value = "completedTasks", allEntries = true , key = "#toDoRequest.id"),
            @CacheEvict(value = "inProgressTasks", allEntries = true , key = "#toDoRequest.id"),
            @CacheEvict(value = "blockedTasks", allEntries = true, key = "#toDoRequest.id"),
            @CacheEvict(value = "pendingTasks", allEntries = true, key = "#toDoRequest.id")
    })
    @Override
    public void deleteTask(Long id) {
        toDoRepository.deleteById(id);
    }

    @Caching(evict = {
            @CacheEvict(value = "allTasks", allEntries = true, key = "#toDoRequest.id"),
            @CacheEvict(value = "completedTasks", allEntries = true, key = "#toDoRequest.id"),
            @CacheEvict(value = "inProgressTasks", allEntries = true, key = "#toDoRequest.id"),
            @CacheEvict(value = "blockedTasks", allEntries = true, key = "#toDoRequest.id"),
            @CacheEvict(value = "pendingTasks", allEntries = true, key = "#toDoRequest.id")
    })
    @Override
    public ToDoResponse updateTask(ToDoRequest toDoRequest) {
        Optional<ToDoModel> optionalToDoModel = toDoRepository.findById(toDoRequest.getId());
        if (optionalToDoModel.isPresent()) {
            ToDoModel toDoModel = optionalToDoModel.get();
            toDoModel.setTask(toDoRequest.getTask());
            toDoModel.setStatus(toDoRequest.getStatus());
            toDoModel.setStartDate(toDoRequest.getStartDate());
            toDoModel.setDueDate(toDoRequest.getDueDate());
            toDoModel.setEndDate(toDoRequest.getEndDate());
            toDoModel.setNotes(toDoRequest.getNotes());
            return modelMapper.map(toDoRepository.save(toDoModel), ToDoResponse.class);
        }
        return null;
    }
}
