package pl.wiktor.todosapi.mappers;

import pl.wiktor.todosapi.management.model.TaskBO;
import pl.wiktor.todosapi.management.model.TaskDTO;
import pl.wiktor.todosapi.management.tools.EnumStringFinder;
import pl.wiktor.todosapi.persistance.model.TaskEntity;

public interface TaskMapper {

    static TaskBO fromDTOtoBO(TaskDTO taskDTO) {
        return TaskBO.builder()
                ._taskId(taskDTO.get_taskId())
                .title(taskDTO.getTitle())
                .details(taskDTO.getDetails())
                .taskStatus(taskDTO.getTaskStatus() != null ? EnumStringFinder.findTaskStatus(taskDTO.getTaskStatus()) : null)
                .creationTime(taskDTO.getCreationTime())
                .statusChangeTime(taskDTO.getStatusChangeTime())
                .build();
    }

    static TaskDTO fromBOtoDTO(TaskBO taskBO) {
        return TaskDTO.builder()
                ._taskId(taskBO.get_taskId())
                .title(taskBO.getTitle())
                .details(taskBO.getDetails())
                .taskStatus(taskBO.getTaskStatus().name())
                .creationTime(taskBO.getCreationTime())
                .statusChangeTime(taskBO.getStatusChangeTime())
                .build();
    }

    static TaskEntity fromBOToEntity(TaskBO taskBO) {
        return TaskEntity.builder()
                ._taskId(taskBO.get_taskId())
                .title(taskBO.getTitle())
                .details(taskBO.getDetails())
                .taskStatus(taskBO.getTaskStatus())
                .creationTime(taskBO.getCreationTime())
                .statusChangeTime(taskBO.getStatusChangeTime())
                .build();
    }

    static TaskBO fromEntityToBO(TaskEntity taskEntity) {
        return TaskBO.builder()
                ._taskId(taskEntity.get_taskId())
                .title(taskEntity.getTitle())
                .details(taskEntity.getDetails())
                .taskStatus(taskEntity.getTaskStatus())
                .creationTime(taskEntity.getCreationTime())
                .statusChangeTime(taskEntity.getStatusChangeTime())
                .build();
    }
}
