package pl.edu.pjwstk.MovieService.exception;

public class NotChangedAvailabilityException extends RuntimeException {
    public NotChangedAvailabilityException() {
        super("Nie udało się zmienić dostępności filmu");
    }
}
