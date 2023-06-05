package pl.unityt.repositorytask.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GeneralRepositoryWebException extends RuntimeException {

    private HttpStatus httpStatus;
    public GeneralRepositoryWebException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
    
}
