package com.project.spring.controller;

import com.project.spring.entity.Task;
import com.project.spring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/create")
@CrossOrigin
public class TaskController {
   @Autowired
   private TaskService taskService;

   @PostMapping("/newtask")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
       Task newtask = taskService.createtask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newtask);
   }


}
