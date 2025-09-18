package br.com.udemy.tasks.utils;

import br.com.udemy.tasks.controller.dto.TaskDTO;
import br.com.udemy.tasks.model.Task;
import br.com.udemy.tasks.model.TaskState;

public class TestUtils {
    public static Task buildValidTask() {
        return Task.builder()
                .withId("123")
                .withTitle("title")
                .withDescription("description")
                .withPriority(1)
                .withState(TaskState.INSERT)
                .build();
    }

    public static TaskDTO buildValidTaskDTO() {
        TaskDTO dto = new TaskDTO();
        dto.setId("123");
        dto.setTitle("title");
        dto.setDescription("description");
        dto.setPriority(1);
        dto.setState(TaskState.INSERT);
        return dto;
    }

}
