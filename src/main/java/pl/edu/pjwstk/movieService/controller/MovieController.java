package pl.edu.pjwstk.movieService.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.movieService.model.Movie;
import pl.edu.pjwstk.movieService.model.dto.MovieDto;
import pl.edu.pjwstk.movieService.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @ApiOperation(value = "Find all movies")
    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.status(200)
                .body(movieService.takeAllMovies());
    }
    @GetMapping("/movies-test")
    public ResponseEntity<List<Movie>> test() {
        return ResponseEntity.status(200)
                .body(movieService.takeAllMoviesTest());
    }

    @ApiOperation(value = "Find movie by id", notes = "provide information about movie by id")
    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieDto> getMovieWithId(@ApiParam(value = "unique id of movie") @PathVariable Long id) {
        return ResponseEntity.ok(movieService.findMovie(id));
    }

    @ApiOperation(value = "Add movie", notes = "provide movie by json")
    @PostMapping("/movie")
    public ResponseEntity<MovieDto> addMovie(@ApiParam(value = "movie as json format") @RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.addAndPrintMovie(movie));
    }

    @ApiOperation(value = "Update movie by id", notes = "provide information about movie by id")
    @PutMapping("/update/{id}")
    public ResponseEntity<MovieDto> updateMovie(
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
    public ResponseEntity<MovieDto> rentMovie(@ApiParam(value = "unique id of movie") @PathVariable Long id) {
        return ResponseEntity.status(200)
                .body(movieService.rentMovie(id));
    }

    @ApiOperation(value = "Return movie by id", notes = "provide information about movie by id")
    @PutMapping("/return/{id}")
    public ResponseEntity<MovieDto> returnMovie(@ApiParam(value = "unique id of movie") @PathVariable Long id) {
        return ResponseEntity.status(200)
                .body(movieService.returnMovie(id));
    }
}
