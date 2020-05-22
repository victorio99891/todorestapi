package pl.wiktor.todosapi.management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wiktor.todosapi.management.model.enums.TaskStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskBO {

    private Long _taskId;

    private String UUID;

    private String title;

    private String details;

    private TaskStatus taskStatus;

    private LocalDateTime creationTime;

    private LocalDateTime statusChangeTime;
}
