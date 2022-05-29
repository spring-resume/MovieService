package pl.edu.pjwstk.movieService.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import pl.edu.pjwstk.movieService.exception.NotFoundMovieException;
import pl.edu.pjwstk.movieService.model.CategoryMovie;
import pl.edu.pjwstk.movieService.model.Movie;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static pl.edu.pjwstk.movieService.model.CategoryMovie.COMEDY;
import static pl.edu.pjwstk.movieService.model.CategoryMovie.HORROR;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieService movieService;

    @DisplayName("Should find all movies from DB")
    @Test
    public void shouldFindAllMovies() {
        when(movieService.takeAllMovies()).thenReturn(List.of(new Movie()));

        List<Movie> movies = movieService.takeAllMovies();

        assertThat(movies).hasSize(1);
    }

    @DisplayName("Should delete movie by provided id")
    @Test
    void shouldDeleteMovieById() {
        movieService.deleteExistMovie(2L);
        movieService.deleteExistMovie(3L);
        movieService.deleteExistMovie(4L);

        verify(movieService, times(3)).deleteExistMovie(anyLong());
    }

    @DisplayName("Should save movie in DB")
    @Test
    void shouldSaveMovie() {
        Movie some_film = new Movie("Some film", COMEDY, true);
        when(movieService.addAndPrintMovie(some_film))
                .thenReturn(new Movie("Some film", COMEDY, true));

        Movie save = movieService.addAndPrintMovie(some_film);

        assertThat(save.getName()).isEqualTo("Some film");
    }

    @DisplayName("Should return name of movie by id")
    @Test
    void shouldReturnSameNameOfMovieById() {
        when(movieService.takeMovie(5L)).thenReturn(
                new Movie("Test title of Movie", CategoryMovie.HORROR, true));

        String nameOfMovie = movieService.takeMovie(5L).getName();

        assertEquals("Test title of Movie", nameOfMovie);
    }

    @DisplayName("Should can return movie")
    @Test
    void shouldReturnMovie() {
        when(movieService.returnMovie(2L)).thenReturn(new Movie("some title", HORROR, true));

        boolean available = movieService.returnMovie(2L).isAvailable();

        assertTrue(available);
    }

    @DisplayName("Should throw exception when id doenst exist in DB")
    @Test
    void shouldThrowExceptionWhenDoNotFindByID() {
        Long id = 1000000000000L;
        when(movieService.takeMovie(id)).thenThrow(NotFoundMovieException.class);

        assertThatExceptionOfType(NotFoundMovieException.class)
                .isThrownBy(() -> movieService.takeMovie(id));

    }

    @DisplayName("Should can rent movie")
    @Test
    void shouldRentMovie() {
        when(movieService.rentMovie(1L)).thenReturn(new Movie("some title", HORROR, false));

        boolean available = movieService.rentMovie(1L).isAvailable();

        assertFalse(available);
    }

    @DisplayName("Should not find any movies")
    @Test
    public void shouldNotFindAnything() {
        when(movieService.takeAllMovies()).thenReturn(List.of());

        List<Movie> all = movieService.takeAllMovies();

        assertThat(all).isEmpty();
    }


}