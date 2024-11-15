package com.theater.service;

import com.theater.entity.Seance;
import com.theater.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeanceService {

    private SeanceRepository seanceRepository;

    public List<Seance> getAll(){
        return (List<Seance>) seanceRepository.findAll();
    }

    public List<Seance> getAllByFilmId(int filmId){
        return (List<Seance>) seanceRepository.findAllByFilmId(filmId);
    }

    public void save(Seance seance) { seanceRepository.save(seance); }

    public Seance get(int id){ return seanceRepository.findById(id); }
}
