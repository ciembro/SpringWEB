package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAll();

    @Query
    Optional<Task> findById(Long taskId);
}
