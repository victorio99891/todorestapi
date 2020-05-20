package pl.wiktor.todosapi.management.service;

import pl.wiktor.todosapi.management.model.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> findAll();

    List<TaskDTO> findAllByStatus(String taskStatus);

    TaskDTO findById(Long id);

    TaskDTO add(TaskDTO taskDTO);

    TaskDTO modify(Long id, TaskDTO taskDTO);

    boolean delete(Long id);
}
