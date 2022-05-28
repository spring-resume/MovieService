package pl.edu.pjwstk.movieService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.edu.pjwstk.movieService.model.Movie;

import javax.transaction.Transactional;
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

    @Transactional
    @Modifying
    @Query("UPDATE Movie m SET m.isAvailable=:b WHERE m.id=:id")
    void changeAvailable(boolean b, Long id);

    @Query("SELECT m.isAvailable FROM Movie m WHERE m.id=:id ")
    boolean checkByAvailable(Long id);

}
