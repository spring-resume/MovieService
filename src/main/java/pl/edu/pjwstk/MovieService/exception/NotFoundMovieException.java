package pl.edu.pjwstk.MovieService.exception;

public class NotFoundMovieException extends RuntimeException {
    public NotFoundMovieException(String message) {
        super(message);
    }
}
