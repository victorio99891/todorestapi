package pl.wiktor.todosapi.exception;

import lombok.Getter;

@Getter
public class TaskException extends RuntimeException {
    private String message;

    public TaskException(String message) {
        this.message = message;
    }
}
