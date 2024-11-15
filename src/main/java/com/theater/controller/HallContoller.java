package com.theater.controller;

import com.theater.entity.Hall;
import com.theater.repository.HallRepository;
import com.theater.service.HallService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class HallContoller {
    private HallService hallService;
    private HallRepository hallRepository;

    @GetMapping("/Hall")
    public List<Hall> getHalls(){ return hallService.getHalls(); }

    @PostMapping("/admin/Hall")
    public Hall addHall(@Valid @RequestBody Hall hall, BindingResult br){
        if(br.hasErrors()) throw new RuntimeException("BRError");
        return hallService.addHall(hall);
    }

    @PutMapping("/admin/Hall/{id}")
    public Hall editHall(@PathVariable Hall id, @Valid @RequestBody Hall hall, BindingResult br){
        if(br.hasErrors()) throw new RuntimeException("BRError");
        return hallService.editHall(id, hall);
    }

    @DeleteMapping("/admin/Hall/{id}")
    public Hall deleteHall(@PathVariable Hall id){ return hallService.deleteHall(id); }
}
