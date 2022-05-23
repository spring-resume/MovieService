package pl.edu.pjwstk.MovieService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.MovieService.model.Movie;

import java.util.List;
import java.util.Optional;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Override
    List<Movie> findAll();

    @Override
    Optional<Movie> findById(Long id);

    @Override
    void delete(Movie entity);

    @Override
    @SuppressWarnings("unchecked")
    Movie save(Movie entity);
}
