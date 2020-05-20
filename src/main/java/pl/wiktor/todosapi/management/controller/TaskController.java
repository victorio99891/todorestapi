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

    @GetMapping(path = "/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable("id") String id) {
        log.debug("Invoke method [getTask] with details: {idPath: " + id + "}");
        long longId = parseLongId(id);
        return ResponseEntity.ok(taskService.findById(longId));
    }

    @PostMapping(path = "/{id}")
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO task) {
        log.debug("Invoke method [addTask] with details: {task: " + task.toString() + "}");
        return ResponseEntity.ok(taskService.add(task));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<TaskDTO> modifyTask(@PathVariable("id") String id, @RequestBody TaskDTO task) {
        log.debug("Invoke method [modifyTask] with details: {idPath: " + id + ", task: " + task.toString() + "}");
        long longId = parseLongId(id);
        return ResponseEntity.ok(taskService.modify(longId, task));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable("id") String id) {
        log.debug("Invoke method [deleteTask] with details: {idPath: " + id + "}");
        long longId = parseLongId(id);
        return ResponseEntity.ok(taskService.delete(longId));
    }

    private Long parseLongId(@PathVariable("id") String id) {
        long longId;
        try {
            longId = Long.parseLong(id);
        } catch (Exception e) {
            throw new TaskException("Unable to parse ID to Long: " + id);
        }
        return longId;
    }

}
