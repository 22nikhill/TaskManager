package com.project.spring.service;

import com.project.spring.entity.Task;
import com.project.spring.taskrepo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.http.HttpResponse;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskrepo;

    public Task createtask(Task task){
         return taskrepo.save(task);
    }
}
