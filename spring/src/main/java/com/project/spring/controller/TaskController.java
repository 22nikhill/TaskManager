package com.project.spring.controller;

import com.project.spring.DTO.TaskRequestDTO;
import com.project.spring.DTO.TaskResponseDto;
import com.project.spring.DTO.UpdateTaskStatusDto;
import com.project.spring.Enums.Priority;
import com.project.spring.Enums.Status;
import com.project.spring.Entity.Task;
import com.project.spring.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTask() {
        List<Task> allTask = taskService.getAllTask();
        return ResponseEntity.status(HttpStatus.OK).body(allTask);
    }

    @PostMapping("/newtask")
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequestDTO task) {
        Task newtask = taskService.createtask(task);
        return ResponseEntity.ok(newtask);
    }



   @GetMapping("/gettask")
   public ResponseEntity<Page<TaskResponseDto>> gettask(
           @RequestParam(required = false) Status status,
           @RequestParam(required = false) Priority priority,
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "5") int size,
           @RequestParam(defaultValue = "id") String sortby,
           @RequestParam(defaultValue = "asc") String order


   ){
        Page<TaskResponseDto> tasks = taskService.gettask(status,priority,sortby,order, page , size);
        return ResponseEntity.ok(tasks);
   }



    @GetMapping("/gettask/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task taskbyid = taskService.getTaskById(id);
        return ResponseEntity.ok(taskbyid);
    }



    @PatchMapping("/{id}/updatestatus")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @RequestBody UpdateTaskStatusDto status) {
        Task task = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok(task);

    }

    @PatchMapping("/{id}/updatepriority")
    public ResponseEntity<Task> updateTaskPriority(@PathVariable Long id,
                                                   @RequestBody Map<String, Priority> body) {
        Priority priority = body.get("priority");
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
