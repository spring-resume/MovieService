package pl.edu.pjwstk.movieService.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel("Data of Movie")
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(position = 1, value = "unique id of movie")
    private Long id;
    @ApiModelProperty(position = 2, value = "name of movie")
    private String name;
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(position = 3, value = "category of movie")
    private CategoryMovie category;
    @Column(columnDefinition = "TINYINT")
    @ApiModelProperty(position = 4, value = "availability of movie")
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
