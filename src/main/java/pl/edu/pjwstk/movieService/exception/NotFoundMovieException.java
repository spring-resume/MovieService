package pl.edu.pjwstk.movieService.exception;

public class NotFoundMovieException extends RuntimeException {
    public NotFoundMovieException() {
        super("Nie znaleziono filmu");
    }
}
