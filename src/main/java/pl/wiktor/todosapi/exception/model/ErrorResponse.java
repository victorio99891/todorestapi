package pl.wiktor.todosapi.exception.model;

import lombok.Getter;
import lombok.Setter;

import java.io.PrintWriter;
import java.io.StringWriter;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String details;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(String message, String details) {
        this.message = message;
        this.details = details;
    }

    public static String convertStackTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString().substring(0, 500);
    }
}
