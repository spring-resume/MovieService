package pl.edu.pjwstk.movieService.exception;

public class NotReturnMovieException extends RuntimeException {
    public NotReturnMovieException(Long id) {
        super("Nie udało się zwrócić filmu o id " + id);
    }
}
