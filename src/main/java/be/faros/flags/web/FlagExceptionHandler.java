package be.faros.flags.web;

import be.faros.flags.exceptions.EntityNotFoundException;
import be.faros.flags.exceptions.FlagException;
import be.faros.flags.exceptions.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FlagExceptionHandler {
    @ExceptionHandler(FlagException.class)
    public ResponseEntity<Void> handleFlagException(FlagException ignore) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<Void> handleNotImplementedException(NotImplementedException ignore) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleEntityNotFoundException(EntityNotFoundException ignore) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
