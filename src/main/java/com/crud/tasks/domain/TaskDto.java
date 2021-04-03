package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TaskDto {
    private final Long id;
    private final String title;
    private final String content;
}

