package ir.aamnapm.history.exeption;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(notFoundException.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({DateException.class})
    protected ResponseEntity<Object> handleDateException(DateException dateException) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(dateException.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({DataException.class})
    protected ResponseEntity<Object> handleDataException(DataException dateException) {
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR);
        apiError.setMessage(dateException.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
