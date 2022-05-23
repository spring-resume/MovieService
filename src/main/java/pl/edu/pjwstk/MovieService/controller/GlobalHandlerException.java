package pl.edu.pjwstk.MovieService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.edu.pjwstk.MovieService.exception.NotFoundMovieException;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(NotFoundMovieException.class)
    public ResponseEntity<String> handleNotFoundMovieException(NotFoundMovieException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Received Invalid Input Parameters")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidInputMovieException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());

//        Error error = new Error(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
//        return new ResponseEntity<>(error, error.getHttpStatus()); // import z modelu error,
//        responseentity z generykiem error,
//        bez responsestatus
//        https://www.amitph.com/spring-rest-api-custom-error-messages/
    }
}
