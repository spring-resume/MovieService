package pl.edu.pjwstk.MovieService.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pjwstk.MovieService.repository.MovieRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    @Mock private MovieRepository movieRepository;
    @InjectMocks private MovieService movieService;

    @Test
    public void testSth() {
        //given


        //when

        //then
    }

}