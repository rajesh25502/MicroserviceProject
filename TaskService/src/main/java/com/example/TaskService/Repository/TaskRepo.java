package com.example.TaskService.Repository;

import com.example.TaskService.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {

    public List<Task> findByAssignedUserId(Long id);
}
