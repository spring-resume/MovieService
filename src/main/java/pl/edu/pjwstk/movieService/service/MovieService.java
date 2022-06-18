package pl.edu.pjwstk.movieService.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.movieService.exception.NotFoundMovieException;
import pl.edu.pjwstk.movieService.exception.NotRentMovieException;
import pl.edu.pjwstk.movieService.exception.NotReturnMovieException;
import pl.edu.pjwstk.movieService.model.Movie;
import pl.edu.pjwstk.movieService.model.dto.MovieDto;
import pl.edu.pjwstk.movieService.repository.MovieRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieService(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    public List<MovieDto> takeAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public Movie takeMovie(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(NotFoundMovieException::new);
    }

    public MovieDto findMovie(Long id) {
        return movieRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(NotFoundMovieException::new);
    }

    public MovieDto addAndPrintMovie(Movie movie) {
        movie.setCreatedAt(LocalDateTime.now());
        return mapToDto(movieRepository.save(movie));
    }

    public MovieDto updateExistMovie(Movie movie, Long id) {
        return changeAndReturnMovie(takeMovie(id), movie);
    }

    private MovieDto changeAndReturnMovie(Movie findMovie, Movie provideMovie) {
        provideMovie.setId(findMovie.getId());
        return mapToDto(movieRepository.save(provideMovie));
    }

    public void deleteExistMovie(Long id) {
        movieRepository.delete(takeMovie(id));
    }

    public MovieDto rentMovie(Long id) {
        if (movieRepository.checkByAvailable(id))
            movieRepository.changeAvailable(false, id);
        else throw new NotRentMovieException(id);
        return mapToDto(takeMovie(id));
    }

    public MovieDto returnMovie(Long id) {
        if (!(movieRepository.checkByAvailable(id)))
            movieRepository.changeAvailable(true, id);
        else throw new NotReturnMovieException(id);
        return mapToDto(takeMovie(id));
    }

    public MovieDto mapToDto(Movie movie){
        return modelMapper.map(movie, MovieDto.class);
    }


    public List<Movie> takeAllMoviesTest() {
        return movieRepository.findAll();
    }
}
