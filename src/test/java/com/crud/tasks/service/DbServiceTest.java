package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DbServiceTest {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DbService dbService;

    @Test
    void testGetTaskNoSuchTask() {
        //when
        Optional<Task> foundTask = dbService.getTask(-1L);
        //then
        assertTrue(foundTask.isEmpty());
    }

    @Test
    void testGetTask() {
        //given
        Task task = new Task(1L, "test title", "test content");
        task = taskRepository.save(task);
        Long id = task.getId();
        //when
        Optional<Task> foundTask = dbService.getTask(id);

        //then
        assertTrue(foundTask.isPresent());
        assertNotEquals(0, id);
        assertEquals("test title", foundTask.get().getTitle());
        assertEquals("test content", foundTask.get().getContent());

        //cleanup
        try {
            taskRepository.deleteById(id);
        } catch (Exception e) {
        }
    }

    @Test
    void testSaveTask() {
        //given
        Task task = new Task(1L, "test title", "test content");

        //when
        dbService.saveTask(task);
        Long id = task.getId();
        //then
        assertNotEquals(0, id);
        assertEquals("test title", task.getTitle());
        assertEquals("test content", task.getContent());

        //cleanup
        try {
            taskRepository.deleteById(id);
        } catch (Exception e) {
        }
    }

    @Test
    void deleteTask() {
        //given
        Task task = new Task(1L, "test title", "test content");
        task = taskRepository.save(task);
        Long id = task.getId();
        //when
        dbService.deleteTask(id);
        //then
        assertEquals(Optional.empty(), taskRepository.findById(id));
    }
}