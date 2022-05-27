package pl.edu.pjwstk.MovieService.exception;

public class NotRentMovieException extends RuntimeException {
    public NotRentMovieException(Long id) {
        super("Nie udało się wypożyczyc filmu o id " + id);
    }
}
