package pl.wiktor.todosapi.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import pl.wiktor.todosapi.persistance.model.TaskEntity;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByTaskStatus(String taskStatus);

    Optional<TaskEntity> findByUUID(String uuid);
}
