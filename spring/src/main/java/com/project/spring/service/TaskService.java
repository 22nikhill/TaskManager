package com.project.spring.service;

import com.project.spring.DTO.TaskRequestDTO;
import com.project.spring.DTO.UpdateTaskStatusDto;
import com.project.spring.Enums.Priority;
import com.project.spring.Enums.Status;
import com.project.spring.Exception.TaskNotFoundException;
import com.project.spring.entity.Task;
import com.project.spring.taskrepo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.project.spring.Enums.Status.PENDING;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskrepo;

    public List<Task> getAllTask(){

        return taskrepo.findAll();
    }
    public Task createtask(TaskRequestDTO taskdto){
        Task newtask = new Task();
        newtask.setTitle(taskdto.getTitle());
        newtask.setDeadline(taskdto.getDeadline());
        newtask.setPriority(taskdto.getPriority());
        newtask.setDescription(taskdto.getDescription());
        newtask.setStatus(PENDING);


         return taskrepo.save(newtask);
    }

    public Task getTaskById(Long id) {

        return taskrepo.findById(id).orElseThrow(()->new TaskNotFoundException("TasK Not found" + id));

    }



    public Task updateTaskStatus(Long id , UpdateTaskStatusDto statusdto){
        Task task = taskrepo.findById(id).orElseThrow(()->new RuntimeException("Task Not found"));
        task.setStatus(statusdto.getStatus());
        return taskrepo.save(task);

    }

    public Task updateTaskPriority(Long id, Priority priority) {
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
    public String deleteTask(Long id){
            taskrepo.deleteById(id);
        return "Task deleted Successfully";
    }

    public List<Task> gettask(Status status, Priority priority) {
        if(status != null && priority != null){
            return taskrepo.findByStatusAndPriority(status,priority);
        }
        if(priority != null ){
            return taskrepo.findByPriority(priority);
        }
        if(status != null){
            return taskrepo.findByStatus(status);
        }
        return taskrepo.findAll();
    }
}
