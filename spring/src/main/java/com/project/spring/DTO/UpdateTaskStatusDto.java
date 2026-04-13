package com.project.spring.DTO;

import com.project.spring.Enums.Status;

public class UpdateTaskStatusDto {
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
