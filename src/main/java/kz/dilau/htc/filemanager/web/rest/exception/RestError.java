package kz.dilau.htc.filemanager.web.rest.exception;

import kz.dilau.htc.filemanager.exception.DetailedException;
import kz.dilau.htc.filemanager.web.dto.LocaledValue;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RestError {
    private LocalDateTime timestamp;
    private String error;
    private LocaledValue message;

    public static RestError createRestException(final DetailedException de) {
        final RestError exception = new RestError();
        exception.timestamp = LocalDateTime.now();
        exception.error = de.getMessage();
        exception.message = de.getDescription();
        return exception;
    }
}
