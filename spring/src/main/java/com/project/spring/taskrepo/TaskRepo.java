package com.project.spring.taskrepo;
import com.project.spring.Enums.Priority;
import com.project.spring.Enums.Status;
import com.project.spring.Entity.Task;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {

    public List<Task> findByDeadline(LocalDate deadline);
    public Page<Task> findByStatus(Status status, Pageable pageable);

    public Page<Task> findByPriority(Priority priority, Pageable pageable);
    public Page<Task> findByStatusAndPriority(Status status,Priority priority, Pageable pageable);


}
