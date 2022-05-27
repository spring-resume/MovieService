package pl.edu.pjwstk.MovieService.exception;

public class NotReturnMovieException extends RuntimeException {
    public NotReturnMovieException(Long id) {
        super("Nie udało się zwrócić filmu o id " + id);
    }
}
