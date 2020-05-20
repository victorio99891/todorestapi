package pl.wiktor.todosapi.management.tools;

import pl.wiktor.todosapi.exception.TaskException;
import pl.wiktor.todosapi.management.model.enums.TaskStatus;

import java.util.stream.Stream;

public class EnumStringFinder {

    public static TaskStatus findTaskStatus(String taskStatusString) {
        return Stream.of(TaskStatus.values())
                .filter(ts -> ts.name().equalsIgnoreCase(taskStatusString))
                .findFirst()
                .orElseThrow(() -> new TaskException("Could not find status: " + taskStatusString));
    }

}
