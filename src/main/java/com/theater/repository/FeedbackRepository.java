package com.theater.repository;

import com.theater.entity.Feedback;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {
    //@EntityGraph(attributePaths = {"user"})
    List<Feedback> findAllByFilmId(int filmId);

    List<Feedback> findAllByUserId(int userId);
}
