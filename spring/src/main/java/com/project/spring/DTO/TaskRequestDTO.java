package com.project.spring.DTO;

import com.project.spring.Enums.Priority;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public class TaskRequestDTO {
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @Future(message = "DeadLine must be in future")
    private LocalDate deadline;


    @NotBlank(message = "Description cannot be empty")
    private String description;
    @NotNull (message = "priority is required")
    private Priority priority;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
