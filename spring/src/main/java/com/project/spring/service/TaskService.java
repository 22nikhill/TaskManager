package com.project.spring.service;

import com.project.spring.DTO.TaskRequestDTO;
import com.project.spring.DTO.TaskResponseDto;
import com.project.spring.DTO.UpdateTaskStatusDto;
import com.project.spring.Enums.Priority;
import com.project.spring.Enums.Status;
import com.project.spring.Exception.TaskNotFoundException;
import com.project.spring.entity.Task;
import com.project.spring.taskrepo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

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

    public TaskResponseDto mapToDto(Task task){
        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setDeadline(task.getDeadline());
        return dto;
    }
    public Page<TaskResponseDto> gettask(Status status, Priority priority,
                              String sortby, String order,
                              int page, int size)
    {
        Sort sort = order.equalsIgnoreCase("desc")? Sort.by(sortby).descending():
                 Sort.by(sortby).ascending();
        Pageable pageable = PageRequest.of(page,size,sort);

        if(status != null && priority != null){
           Page<Task> taskPage = taskrepo.findByStatusAndPriority(status,priority,pageable);
           return taskPage.map(this::mapToDto);
        }
        else if(priority != null ){
            Page<Task> taskPage = taskrepo.findByPriority(priority,pageable);
            return taskPage.map(this::mapToDto);
        }
        else if(status != null){
            Page<Task> taskPage = taskrepo.findByStatus(status,pageable);
            return  taskPage.map(this::mapToDto);
        }
        Page<Task> taskPage = taskrepo.findAll(pageable);
        return taskPage.map(this::mapToDto);

    }
}
