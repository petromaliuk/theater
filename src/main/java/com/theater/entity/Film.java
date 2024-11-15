package com.theater.entity;


import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "films")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
public class Film {
    //@EntityGraph(attributePaths = {"fieldName"}) - для жадної загрузки філда в якого є своя таблиця

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Integer id;

    @OneToMany(mappedBy = "film", orphanRemoval = true)
    @JsonView(Views.FullFilm.class)
    private List<Seance> seance;

    @OneToMany(mappedBy = "film", orphanRemoval = true)
    @JsonView(Views.FullFilm.class)
    private List<Feedback> feedback;

    @Column(name = "title", nullable = false, length = 100)
    @NotBlank @Size(min = 2, max = 30, message = "Title should be form 2 to 30 characters")
    @JsonView(Views.IdName.class)
    private String title;

    @Column(name = "description", nullable = false, length = 400)
    @NotBlank @Size(min = 2, max = 400, message = "Description should be form 2 to 400 characters")
    @JsonView(Views.FullFilm.class)
    private String description;

    @Column(name = "category", nullable = false, length = 50)
    @NotBlank @Size(min = 2, max = 30, message = "Category should be form 2 to 30 characters")
    @JsonView(Views.FullFilm.class)
    private String category;

    @Column(name = "pic", length = 100)
    @JsonView(Views.IdName.class)
    private String pic;

    @Column(name = "duration", nullable = false)
    @Min(0) @Max(1440)
    @JsonView(Views.FullFilm.class)
    private int duration;

}