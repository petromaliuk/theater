package com.theater.repository;

import com.theater.entity.Film;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilmRepository extends CrudRepository<Film, Integer> {
    List<Film> findAll();

    @EntityGraph(attributePaths = {"seance", "feedback"})
    Film findById(int id);
}
