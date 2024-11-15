package com.theater.helper;

import com.theater.entity.Film;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilmDTO {

    MultipartFile file;
    @NotBlank @Size(min = 2, max = 30, message = "Title should be form 2 to 30 characters")
    private String title;
    @NotBlank @Size(min = 2, max = 400, message = "Description should be form 2 to 400 characters")
    private String description;
    @NotBlank @Size(min = 2, max = 30, message = "Category should be form 2 to 30 characters")
    private String category;
    @Min(0) @Max(1440)
    private int duration;

    public Film fillFilm(Film film){
        film.setTitle(title);
        film.setCategory(category);
        film.setDescription(description);
        film.setDuration(duration);
        return film;
    }
}
