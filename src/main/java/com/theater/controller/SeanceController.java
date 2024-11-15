package com.theater.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.theater.entity.Film;
import com.theater.entity.Hall;
import com.theater.entity.Seance;
import com.theater.entity.Views;
import com.theater.repository.SeanceRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SeanceController {
    private SeanceRepository seanceRepository;

    @GetMapping("/Seance")
    @JsonView(Views.PartSeance.class)
    public List<Seance> getSeances(){
        return seanceRepository.findAll();
    }

    @GetMapping("/Seance/{id}")
    @JsonView(Views.FullSeance.class)
    public Seance seance (@PathVariable int id) { return seanceRepository.findById(id); }

    @PostMapping("/admin/Seance")
    @JsonView(Views.PartSeance.class)
    public Seance addSeance(@RequestParam Film filmId, @RequestParam Hall hallId,
                            @Valid @RequestBody Seance seance, BindingResult bindingResult){
        seance.setFilm(filmId);
        seance.setHall(hallId);
        if(bindingResult.hasErrors()) throw new RuntimeException("BRError");
        return seanceRepository.save(seance);
    }

    @PutMapping("/admin/Seance/{id}")
    @JsonView(Views.PartSeance.class)
    public Seance editSeance(@PathVariable Seance id, @RequestParam Film filmId, @RequestParam Hall hallId,
                            @Valid @RequestBody Seance seance, BindingResult br){
        if(br.hasErrors()) throw new RuntimeException("BRError");
        BeanUtils.copyProperties(seance, id, "id", "film", "hall", "tickets");
        id.setFilm(filmId);
        id.setHall(hallId);
        return seanceRepository.save(id);
    }

    @DeleteMapping("/admin/Seance/{id}")
    @JsonView(Views.PartSeance.class)
    public Seance deleteSeance(@PathVariable Seance id){
        seanceRepository.deleteById(id.getId());
        return id;
    }
}
