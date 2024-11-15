package com.theater.service;

import com.theater.entity.Hall;
import com.theater.repository.HallRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HallService {

    private HallRepository hallRepository;

    public Hall addHall(Hall hall){
        return hallRepository.save(hall);
    }

    public List<Hall> getHalls(){
        return (List<Hall>) hallRepository.findAll();
    }


    public Hall editHall(Hall id, Hall hall) {
        BeanUtils.copyProperties(hall, id, "id");
        return hallRepository.save(id);
    }

    public Hall deleteHall(Hall id) {
        hallRepository.delete(id);
        return id;
    }
}
