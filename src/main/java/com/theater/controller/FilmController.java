package com.theater.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.theater.entity.Film;
import com.theater.entity.Views;
import com.theater.helper.FilmDTO;
import com.theater.repository.FilmRepository;
import com.theater.service.FilmService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class FilmController {

    private FilmRepository filmRepository;
    private FilmService filmService;

    @GetMapping("/Film")
    @JsonView(Views.IdName.class)
    public List<Film> films(){
        return filmRepository.findAll();
    }

    @GetMapping("/Film/{id}")
    @JsonView(Views.FullFilm.class)
    public Film films(@PathVariable Film id){ return id; }

    @PostMapping("/admin/Film")
    @JsonView(Views.IdName.class)
    public Film addFilm(@Valid FilmDTO param, Errors errors) throws IOException {
        if(errors.hasErrors()) throw new RuntimeException("BRError");
        return filmService.save(param.getFile(), param.fillFilm(new Film()));
    }

    @PutMapping("/admin/Film/{id}")
    @JsonView(Views.IdName.class)
    public Film editFilm(@PathVariable Film id, @Valid FilmDTO param, Errors errors) throws IOException {
        if(errors.hasErrors()) throw new RuntimeException("BRError");
        return filmService.save(param.getFile(), param.fillFilm(id));
    }

    @DeleteMapping("/admin/Film/{id}")
    @JsonView(Views.IdName.class)
    public Film deleteFilm(@PathVariable Film id){ return filmService.delete(id); }



}
