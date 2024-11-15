package com.theater.service;

import com.theater.entity.Ticket;
import com.theater.helper.TicketsDto;
import com.theater.repository.*;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class TicketService {

    private TicketRepository ticketRepository;
    private EntityManager entityManager;

    public List<Ticket> getTicketsBySeance(int seanceId){
        return ticketRepository.findAllBySeanceId(seanceId);
    }

    public List<Ticket> getTicketsByUser(int userId){
        //TicketsDto dto = new TicketsDto();
        //dto.setTickets(ticketRepository.findAllByUserId(userId));
        EntityGraph<?> entityGraph = entityManager.createEntityGraph("graph.Ticket.seance");
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket t WHERE t.user.id = :user", Ticket.class);
        query.setParameter("user", userId);
        query.setHint("jakarta.persistence.fetchgraph", entityGraph);
        return query.getResultList();
        //return new TicketsDto();
    }

    public void save(Ticket ticket){
        ticketRepository.save(ticket);
    }
}
