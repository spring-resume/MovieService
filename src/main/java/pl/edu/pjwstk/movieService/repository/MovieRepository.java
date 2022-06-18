package pl.edu.pjwstk.movieService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.edu.pjwstk.movieService.model.Movie;

import javax.transaction.Transactional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Movie m SET m.isAvailable=:b WHERE m.id=:id")
    void changeAvailable(boolean b, Long id);

    @Query("SELECT m.isAvailable FROM Movie m WHERE m.id=:id")
    boolean checkByAvailable(Long id);

}
