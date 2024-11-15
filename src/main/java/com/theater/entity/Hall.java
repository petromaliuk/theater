package com.theater.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "halls")
@Data
public class Hall {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private int id;

    @Column(name = "name", nullable = false, length = 30)
    @NotBlank @Size(min = 2, max = 30, message = "Name should be form 2 to 30 characters")
    @JsonView(Views.IdName.class)
    private String name;

    @Column(name = "address", nullable = false, length = 30)
    @NotBlank @Size(min = 2, max = 30, message = "Name should be form 2 to 30 characters")
    @JsonView(Views.IdName.class)
    private String address;

    @Column(name = "places")
    @JsonView(Views.FullSeance.class)
    private int[] places;

    @Column(name = "shifts")
    @JsonView(Views.FullSeance.class)
    private float[] shifts;

}
