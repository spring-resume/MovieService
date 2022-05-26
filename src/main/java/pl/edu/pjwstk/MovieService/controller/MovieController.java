package pl.edu.pjwstk.MovieService.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value = "Find all movies")
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.status(200)
                .body(movieService.takeAllMovies());
    }

    @ApiOperation(value = "Find movie by id", notes = "provide information about movie by id")
    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieWithId(@ApiParam(value = "unique id of movie") @PathVariable Long id) {
        return ResponseEntity.ok(movieService.takeMovie(id));
    }

    @ApiOperation(value = "Add movie", notes = "provide movie by json")
    @PostMapping("/movie")
    public ResponseEntity<Movie> addMovie(@ApiParam(value = "movie as json format") @RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.addAndPrintMovie(movie));
    }

    @ApiOperation(value = "Update movie by id", notes = "provide information about movie by id")
    @PutMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(
            @ApiParam(value = "movie as json format") @RequestBody Movie movie,
            @ApiParam(value = "unique id of movie") @PathVariable Long id
    ) {
        return ResponseEntity.ok(movieService.updateExistMovie(movie, id));
    }

    @ApiOperation(value = "Delete movie by id", notes = "provide information about movie by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovie(@ApiParam(value = "unique id of movie") @PathVariable Long id) {
        movieService.deleteExistMovie(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Rent movie by id", notes = "provide information about movie by id")
    @PutMapping("/rent/{id}")
    public ResponseEntity<Movie> rentMovie(@ApiParam(value = "unique id of movie") @PathVariable Long id) {
        return ResponseEntity.status(200)
                .body(movieService.rentMovie(id));
    }

    @ApiOperation(value = "Return movie by id", notes = "provide information about movie by id")
    @PutMapping("/return/{id}")
    public ResponseEntity<Movie> returnMovie(@ApiParam(value = "unique id of movie") @PathVariable Long id) {
        return ResponseEntity.status(200)
                .body(movieService.returnMovie(id));
    }
}
