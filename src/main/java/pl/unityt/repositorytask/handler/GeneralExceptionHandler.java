package pl.unityt.repositorytask.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.unityt.repositorytask.dto.MessageDto;
import pl.unityt.repositorytask.exception.GeneralRepositoryWebException;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GeneralRepositoryWebException.class)
    protected ResponseEntity<Object> handleClientError(GeneralRepositoryWebException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(MessageDto.builder()
                .message(ex.getMessage())
                .httpStatus(ex.getHttpStatus())
                .build());
    }
}

