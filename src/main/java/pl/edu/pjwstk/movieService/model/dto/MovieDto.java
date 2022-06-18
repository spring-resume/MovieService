package pl.edu.pjwstk.movieService.model.dto;

import pl.edu.pjwstk.movieService.model.CategoryMovie;

public class MovieDto {
    private Long id;
    private String name;
    private CategoryMovie category;
    private boolean isAvailable;

    public MovieDto() {
    }

    public MovieDto(Long id, String name, CategoryMovie category, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryMovie getCategory() {
        return category;
    }

    public void setCategory(CategoryMovie category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
