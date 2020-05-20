package pl.wiktor.todosapi.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wiktor.todosapi.management.model.enums.TaskStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TASKS")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long _taskId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DETAILS")
    private String details;

    @Column(name = "TASK_STATUS")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Column(name = "CREATION_TIME")
    private LocalDateTime creationTime;

    @Column(name = "STATUS_CHANGE_TIME")
    private LocalDateTime statusChangeTime;

}
