package pl.wiktor.todosapi.management.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wiktor.todosapi.exception.TaskException;
import pl.wiktor.todosapi.management.model.TaskDTO;
import pl.wiktor.todosapi.management.service.TaskService;

import java.util.List;

@Slf4j
@RequestMapping(path = "/tasks")
@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(@RequestParam(name = "status", required = false) String taskStatus) {
        log.debug("Invoke method [getAllTasks] with details: {statusParam: " + taskStatus + "}");
        List<TaskDTO> tasks = null;
        if (taskStatus != null && !taskStatus.isEmpty()) {
            tasks = taskService.findAllByStatus(taskStatus);
        } else {
            tasks = taskService.findAll();
        }
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable("id") String uuid) {
        log.debug("Invoke method [getTask] with details: {idPath: " + uuid + "}");
        return ResponseEntity.ok(taskService.findByUUID(uuid));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO task) {
        log.debug("Invoke method [addTask] with details: {task: " + task.toString() + "}");
        return ResponseEntity.ok(taskService.add(task));
    }

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<TaskDTO> modifyTask(@PathVariable("uuid") String uuid, @RequestBody TaskDTO task) {
        log.debug("Invoke method [modifyTask] with details: {uuidPath: " + uuid + ", task: " + task.toString() + "}");
        return ResponseEntity.ok(taskService.modify(uuid, task));
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable("uuid") String uuid) {
        log.debug("Invoke method [deleteTask] with details: {uuidPath: " + uuid + "}");
        return ResponseEntity.ok(taskService.delete(uuid));
    }

}
