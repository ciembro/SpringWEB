package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;

    @Test
    void testMapToTask() {
        //given
        TaskDto taskDto = new TaskDto(1L, "test title", "test content");
        //when
        Task mappedTask = taskMapper.mapToTask(taskDto);
        //then
        assertEquals(1L, mappedTask.getId());
        assertEquals("test title", mappedTask.getTitle());
        assertEquals("test content", mappedTask.getContent());
    }

    @Test
    void testMapToTaskDto() {
        //given
        Task task = new Task(1L, "test title", "test content");
        //when
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);
        //then
        assertEquals(1L, mappedTaskDto.getId());
        assertEquals("test title", mappedTaskDto.getTitle());
        assertEquals("test content", mappedTaskDto.getContent());
    }

    @Test
    void testMapToTaskDtoList() {
        //given
        Task task = new Task(1L, "test title", "test content");
        List<Task> taskList = List.of(task, task);
        //when
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //then
        assertEquals(2, taskDtoList.size());
        taskDtoList.forEach(taskDto -> {
                    assertEquals(1L, taskDto.getId());
                    assertEquals("test title", taskDto.getTitle());
                    assertEquals("test content", taskDto.getContent());
                });
    }
}