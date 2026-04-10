package com.project.spring.controller;

import com.project.spring.entity.Task;
import com.project.spring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/newtask")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task newtask = taskService.createtask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newtask);
    }

    @GetMapping("/gettaskbypriority/{priority}")
    public ResponseEntity<List<Task>> getTaskByStatus(@PathVariable Integer priority) {
        List<Task> tasks = taskService.taskByPriority(priority);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/gettaskbystatus/{status}")
    public ResponseEntity<List<Task>> getTaskByStatus(@PathVariable Boolean status) {
        List<Task> tasks = taskService.taskByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/gettask")
    public ResponseEntity<List<Task>> getAllTask() {
        List<Task> allTask = taskService.getAllTask();
        return ResponseEntity.status(HttpStatus.OK).body(allTask);
    }

    @GetMapping("/gettask/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task taskbyid = taskService.getTaskById(id);
        return ResponseEntity.ok(taskbyid);
    }

    @GetMapping("/gettaskbydeadline/{id}/{deadline}")
    public ResponseEntity<List<Task>> getTaskByDeadLine(@PathVariable LocalDate deadline) {
        List<Task> tasks = taskService.getTaskByDeadline(deadline);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @PatchMapping("/{id}/updatestatus")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @PathVariable Boolean status) {
        Task task = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok(task);

    }

    @PatchMapping("/{id}/updatepriority")
    public ResponseEntity<Task> updateTaskPriority(@PathVariable Long id,
                                                   @RequestBody Map<String, String> body) {
        Integer priority = Integer.parseInt(body.get("priority"));
        Task task = taskService.updateTaskPriority(id, priority);
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}/updatedeadline")
    public ResponseEntity<Task> updateTaskDeadline(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        LocalDate date = LocalDate.parse(body.get("deadline"));
        Task task = taskService.updateTaskDeadline(id, date);

        return ResponseEntity.ok(task);
    }
    @DeleteMapping("/Deletetask/{id}")
    public ResponseEntity deletetask(@PathVariable Long id){
        String s = taskService.deleteTask(id);
        return ResponseEntity.ok(s);
    }





}
