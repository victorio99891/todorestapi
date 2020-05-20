package pl.wiktor.todosapi.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import pl.wiktor.todosapi.persistance.model.TaskEntity;

import java.util.List;

public interface TaskRepository extends CrudRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByTaskStatus(String taskStatus);
}
