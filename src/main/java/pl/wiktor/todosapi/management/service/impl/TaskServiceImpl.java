package pl.wiktor.todosapi.management.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wiktor.todosapi.exception.TaskException;
import pl.wiktor.todosapi.management.model.TaskBO;
import pl.wiktor.todosapi.management.model.TaskDTO;
import pl.wiktor.todosapi.management.service.TaskService;
import pl.wiktor.todosapi.management.tools.EnumStringFinder;
import pl.wiktor.todosapi.mappers.TaskMapper;
import pl.wiktor.todosapi.persistance.model.TaskEntity;
import pl.wiktor.todosapi.persistance.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDTO> findAll() {
        final List<TaskDTO> collection = StreamSupport
                .stream(taskRepository.findAll().spliterator(), false)
                .map(TaskMapper::fromEntityToBO)
                .map(TaskMapper::fromBOtoDTO)
                .collect(Collectors.toList());
        log.debug("Fetched data:" + collection.toString());
        return collection;
    }

    @Override
    public List<TaskDTO> findAllByStatus(String taskStatus) {
        final List<TaskDTO> collection = taskRepository.findAllByTaskStatus(EnumStringFinder.findTaskStatus(taskStatus).name())
                .stream()
                .map(TaskMapper::fromEntityToBO)
                .map(TaskMapper::fromBOtoDTO)
                .collect(Collectors.toList());
        log.debug("Fetched data:" + collection.toString());
        return collection;
    }

    @Override
    public TaskDTO findById(Long id) {
        final TaskDTO task = TaskMapper.fromBOtoDTO(
                TaskMapper.fromEntityToBO(
                        taskRepository.findById(id).orElseThrow(() -> new TaskException("Cannot find Task with ID: " + id))
                )
        );
        log.debug("Fetched data:" + task.toString());
        return task;
    }

    @Override
    public TaskDTO findByUUID(String uuid) {
        final TaskDTO task = TaskMapper.fromBOtoDTO(
                TaskMapper.fromEntityToBO(
                        taskRepository.findByUUID(uuid).orElseThrow(() -> new TaskException("Cannot find Task with UUID: " + uuid))
                )
        );
        log.debug("Fetched data: " + task.toString());
        return task;
    }

    @Override
    public TaskDTO add(TaskDTO taskDTO) {
        TaskBO incoming = TaskMapper.fromDTOtoBO(taskDTO);

        TaskEntity entity = TaskEntity.builder()
                .UUID(UUID.randomUUID().toString())
                .title(incoming.getTitle().toUpperCase())
                .details(incoming.getDetails())
                .taskStatus(incoming.getTaskStatus())
                .build();

        final LocalDateTime now = LocalDateTime.now();
        entity.setStatusChangeTime(now);
        entity.setCreationTime(now);

        taskRepository.save(entity);

        log.debug("Created data: " + entity.toString());
        return TaskMapper.fromBOtoDTO(
                TaskMapper.fromEntityToBO(entity)
        );
    }

    @Override
    public TaskDTO modify(String uuid, TaskDTO taskDTO) {
        TaskEntity current = taskRepository.findByUUID(uuid).orElseThrow(() ->
                new TaskException("Cannot find Task with UUID: " + uuid)
        );

        TaskBO modified = TaskMapper.fromDTOtoBO(taskDTO);

        current.setTitle(modified.getTitle() != null ? modified.getTitle().toUpperCase() : current.getTitle());
        current.setDetails(modified.getDetails() != null ? modified.getDetails() : current.getDetails());
        current.setTaskStatus(modified.getTaskStatus() != null ? modified.getTaskStatus() : current.getTaskStatus());
        current.setStatusChangeTime(LocalDateTime.now());

        taskRepository.save(current);

        modified = TaskMapper.fromEntityToBO(current);
        log.debug("Modified data: " + modified.toString());
        return TaskMapper.fromBOtoDTO(modified);
    }

    @Override
    public boolean delete(String uuid) {
        final TaskEntity entity = taskRepository.findByUUID(uuid).orElseThrow(() ->
                new TaskException("Cannot find Task with UUID: " + uuid)
        );
        taskRepository.delete(entity);
        log.debug("Deleted data: " + entity.toString());
        return true;
    }
}
