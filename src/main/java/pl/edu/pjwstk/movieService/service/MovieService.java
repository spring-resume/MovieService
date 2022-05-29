package pl.edu.pjwstk.movieService.service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.movieService.exception.NotFoundMovieException;
import pl.edu.pjwstk.movieService.exception.NotRentMovieException;
import pl.edu.pjwstk.movieService.exception.NotReturnMovieException;
import pl.edu.pjwstk.movieService.model.Movie;
import pl.edu.pjwstk.movieService.repository.MovieRepository;

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
                .orElseThrow(NotFoundMovieException::new);
    }

    public Movie addAndPrintMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateExistMovie(Movie movie, Long id) {
        return changeAndReturnMovie(takeMovie(id), movie);
    }

    private Movie changeAndReturnMovie(Movie findMovie, Movie provideMovie) {
        provideMovie.setId(findMovie.getId());
        return movieRepository.save(provideMovie);
    }

    public void deleteExistMovie(Long id) {
        movieRepository.delete(takeMovie(id));
    }

    public Movie rentMovie(Long id) {
        if (movieRepository.checkByAvailable(id))
            movieRepository.changeAvailable(false, id);
        else throw new NotRentMovieException(id);
        return takeMovie(id);
    }

    public Movie returnMovie(Long id) {
        if (!(movieRepository.checkByAvailable(id)))
            movieRepository.changeAvailable(true, id);
        else throw new NotReturnMovieException(id);
        return takeMovie(id);
    }


}
