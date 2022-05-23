package pl.edu.pjwstk.MovieService.model;

import javax.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CategoryMovie category;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean isAvailable;

    public Movie() {
    }

    public Movie(String name, CategoryMovie category, boolean isAvailable) {
        this.name = name;
        this.category = category;
        this.isAvailable = isAvailable;
    }


    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
