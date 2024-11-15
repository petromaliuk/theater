package com.theater.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "feedbacks")
@Data
public class Feedback {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "film", nullable = false, referencedColumnName = "id")
    private Film film;

    @ManyToOne()
    @JoinColumn(name = "user", nullable = false, referencedColumnName = "id")
    @JsonView(Views.FullFilm.class)
    private User user;

    @Column(name = "grade", nullable = false)
    @Min(1) @Max(10)
    @JsonView(Views.IdName.class)
    private int grade;

    @Column(name = "description", nullable = false, length = 400)
    @Size(min = 1, max= 400, message = "Comment should be from 1 to 400 characters!")
    @JsonView(Views.IdName.class)
    private String description;

}
