package pl.edu.pjwstk.movieService.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Should invoke /movies and return movies")
    @Test
    void shouldReturnMovies() throws Exception {
        mockMvc.perform(get("/movies"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @DisplayName("Should match content of Movie")
    @Test
    void shouldMatchContentOfMovie() throws Exception {
        mockMvc.perform(get("/movie/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"The Green Mile\"," +
                        "\"category\":\"DRAMA\",\"available\":false}"));


    }


}