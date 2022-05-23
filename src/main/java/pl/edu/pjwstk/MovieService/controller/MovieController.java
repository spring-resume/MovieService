package pl.edu.pjwstk.MovieService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.MovieService.model.Movie;
import pl.edu.pjwstk.MovieService.service.MovieService;

import java.util.List;

@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.status(200)
                .body(movieService.takeAllMovies());
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieWithId(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.takeMovie(id));
    }

    @PostMapping("/add/movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.addAndPrintMovie(movie));
    }

    @PutMapping("/update/movie/{id}")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie, @PathVariable Long id) {
        return ResponseEntity.ok(movieService.updateExistMovie(movie, id));
    }

    @DeleteMapping("/delete/movie/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteExistMovie(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/change-field-movie/{id}")
    public ResponseEntity<Movie> changeBoolean(@PathVariable Long id){
        return ResponseEntity.status(200)
                .body(movieService.changeAvailableMovie(id));
    }
}
