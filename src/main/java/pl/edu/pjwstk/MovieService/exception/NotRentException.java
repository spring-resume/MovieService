package pl.edu.pjwstk.MovieService.exception;

public class NotRentException extends RuntimeException {
    public NotRentException(String message, Long id) {
        super(message + " " + id);
    }
}
