package com.project.spring.taskrepo;
import com.project.spring.Enums.Priority;
import com.project.spring.Enums.Status;
import com.project.spring.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {

    public List<Task> findByDeadline(LocalDate deadline);
    public List<Task> findByStatus(Status status);

    public List<Task> findByPriority(Priority priority);
    public List<Task> findByStatusAndPriority(Status status,Priority priority);


}
