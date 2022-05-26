package pl.edu.pjwstk.MovieService.service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.MovieService.exception.NotChangedAvailabilityException;
import pl.edu.pjwstk.MovieService.exception.NotFoundMovieException;
import pl.edu.pjwstk.MovieService.model.Movie;
import pl.edu.pjwstk.MovieService.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> takeAllMovies() {
        return movieRepository.findAll();
    }

    public Movie takeMovie(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(NotFoundMovieException::new);
    }

    public Movie addAndPrintMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateExistMovie(Movie movie, Long id) {
        Movie findMovie = takeMovie(id);
        return changeAndReturnMovie(findMovie, movie);
    }

    private Movie changeAndReturnMovie(Movie findMovie, Movie provideMovie) {
        findMovie.setName(provideMovie.getName());
        findMovie.setCategory(provideMovie.getCategory());
        findMovie.setAvailable(provideMovie.isAvailable());
        return movieRepository.save(findMovie);
    }

    public void deleteExistMovie(Long id) {
        movieRepository.delete(takeMovie(id));
    }

    public Movie rentMovie(Long id) {
        movieRepository.changeAvailable(false, id);
        return checkAvailabilityForRent(id);
    }

    private Movie checkAvailabilityForRent(Long id) {
        return Optional.of(takeMovie(id))
                .filter(movie -> !movie.isAvailable())
                .orElseThrow(NotChangedAvailabilityException::new);
    }

    public Movie returnMovie(Long id) {
        movieRepository.changeAvailable(true, id);
        return checkAvailabilityForReturn(id);


    }

    private Movie checkAvailabilityForReturn(Long id) {
        return Optional.of(takeMovie(id))
                .filter(Movie::isAvailable)
                .orElseThrow(NotChangedAvailabilityException::new);
    }
}
