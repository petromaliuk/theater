package com.theater.repository;

import com.theater.entity.Ticket;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {

    List<Ticket> findAllBySeanceId(int seanceId);

    @EntityGraph(attributePaths = {"seance"})
    List<Ticket> findAllByUserId(int userId);


}
