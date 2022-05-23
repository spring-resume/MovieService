package pl.edu.pjwstk.MovieService.service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.MovieService.exception.NotFoundMovieException;
import pl.edu.pjwstk.MovieService.model.Movie;
import pl.edu.pjwstk.MovieService.repository.MovieRepository;

import java.util.List;

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
                .orElseThrow(() -> new NotFoundMovieException("Nie znaleziono filmu"));
    }

    public String addAndPrintMovie(Movie movie) {
        return movieRepository.save(movie).toString();
    }

    public Movie updateExistMovie(Movie movie, Long id) {
        Movie findMovie = takeMovie(id);
        return changeAndReturnMovie(findMovie, movie);
    }

    private Movie changeAndReturnMovie(Movie findMovie, Movie provideMovie) {
        findMovie.setId(provideMovie.getId());
        findMovie.setName(provideMovie.getName());
        findMovie.setCategory(provideMovie.getCategory());
        return movieRepository.save(findMovie);
    }

    public void deleteExistMovie(Long id) {
        movieRepository.delete(takeMovie(id));
    }

    public Movie changeAvailableMovie(Long id) {
        Movie movie = takeMovie(id);
        movie.setAvailable(true);
        return movieRepository.save(movie);
    }
}
