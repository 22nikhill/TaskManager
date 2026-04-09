package com.project.spring.controller;

import com.project.spring.entity.Task;
import com.project.spring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {
   @Autowired
   private TaskService taskService;


   @GetMapping("/gettask")
   public ResponseEntity<List<Task>>getAllTask(){
       List<Task> allTask = taskService.getAllTask();
       return ResponseEntity.status(HttpStatus.OK).body(allTask);
   }
   @GetMapping("/gettask/{id}")
   public ResponseEntity<Task> getTaskById(@PathVariable Long id){
       Task taskbyid = taskService.getTaskById(id);
       return ResponseEntity.ok(taskbyid);
   }
   @GetMapping("/gettaskbydeadline/{deadline}")
   public ResponseEntity<List<Task>> getTaskByDeadLine(@PathVariable LocalDate deadline){
       List<Task> tasks=taskService.getTaskByDeadline(deadline);
       return ResponseEntity.status(HttpStatus.OK).body(tasks);
   }

   @PostMapping("/newtask")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
       Task newtask = taskService.createtask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newtask);
   }


}
