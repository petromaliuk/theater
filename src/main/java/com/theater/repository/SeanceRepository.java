package com.theater.repository;

import com.theater.entity.Seance;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeanceRepository extends CrudRepository<Seance, Integer> {

    List<Seance> findAllByFilmId(int filmId);

    @EntityGraph(attributePaths = {"film", "hall"})
    List<Seance> findAll();

    Seance findById(int id);

}
