package com.project.spring.service;

import com.project.spring.Exception.TaskNotFoundException;
import com.project.spring.entity.Task;
import com.project.spring.taskrepo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskrepo;

    public List<Task> getAllTask(){

        return taskrepo.findAll();
    }
    public Task createtask(Task task){
         return taskrepo.save(task);
    }
    public List<Task> taskByStatus(Boolean status){
        return taskrepo.findByStatus(status);
    }
    public Task getTaskById(Long id) {

        return taskrepo.findById(id).orElseThrow(()->new TaskNotFoundException("TasK Not found" + id));

    }
    public List<Task> getTaskByDeadline(LocalDate date){
        return taskrepo.findByDeadline(date);
    }

    public List<Task> taskByPriority(Integer priority) {
        return taskrepo.findByPriority(priority);
    }
    public Task updateTaskStatus(Long id , Boolean status){
        Task task = taskrepo.findById(id).orElseThrow(()->new RuntimeException("Task Not found"));
        task.setStatus(status);
        return taskrepo.save(task);

    }

    public Task updateTaskPriority(Long id, Integer priority) {
        Task task = taskrepo.findById(id).orElseThrow(()->new RuntimeException("Task Not found"));
        task.setPriority(priority);
        return taskrepo.save(task);
    }
    public Task updateTaskDeadline(Long id , LocalDate date) {
        LocalDate currdate = LocalDate.now();
        System.out.print(currdate);
        Task task = taskrepo.findById(id).orElseThrow(()->new RuntimeException("Task Not found"));
        task.setDeadline(date);
        return taskrepo.save(task);
    }
}
