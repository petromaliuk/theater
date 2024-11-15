package com.theater.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.theater.entity.*;
import com.theater.helper.TicketsDto;
import com.theater.repository.FeedbackRepository;
import com.theater.service.TicketService;
import com.theater.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {
    private FeedbackRepository feedbackRepository;
    private TicketService ticketService;
    private UserService userService;

    @GetMapping("/viewer/data")
    public User getUser(@AuthenticationPrincipal User user){
        return user;
    }

    @GetMapping("/viewer/Ticket")
    @JsonView(Views.PartTicket.class)
    public List<Ticket> getTickets(@AuthenticationPrincipal User user){
        return ticketService.getTicketsByUser(user.getId());
    }

    @PutMapping("/user/{id}")
    public User editUser(@AuthenticationPrincipal User userFromContext,
                         @Valid @RequestBody User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) throw new RuntimeException("BRError");
        return userService.editUser(userFromContext, user);
    }

    @PostMapping("/user")
    public User addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) throw new RuntimeException("BRError");
        return userService.addUser(user);
    }
    @GetMapping("/getAllUsers")
    public String getAllUsers() {
        StringBuilder builder = new StringBuilder();
        userService.getAll().forEach(user -> builder.append("<li>").append(user.toString()).append("</li>"));
        return "<html> <body> <h1> Users: </h1> <ul> " + builder + " </ul> </body> </html>";
    }

    @PostMapping("/viewer/Seance/{id}/tickets")
    @JsonView(Views.IdName.class)
    public List<Ticket> postTickets(@PathVariable Seance id, @RequestBody List<Ticket> tickets,
                                    @AuthenticationPrincipal User user){
        tickets.forEach(el -> {
            el.setUser(user);
            el.setSeance(id);
            ticketService.save(el);
        });
        return tickets;
    }

    @PostMapping("/viewer/Film/{id}/comment")
    @JsonView(Views.FullFilm.class)
    public Feedback addComm(@PathVariable("id") Film film,
                            @AuthenticationPrincipal User user,
                            @Valid @RequestBody Feedback feedback,
                            BindingResult bindingResult){
        if(bindingResult.hasErrors()) throw new RuntimeException("BRError");
        feedback.setFilm(film);
        feedback.setUser(user);
        feedbackRepository.save(feedback);
        return feedback;
    }
    @GetMapping("/Film/{id}/comment")
    @JsonView(Views.FullFilm.class)
    public List<Feedback> getComm(@PathVariable("id") Film film){
        return film.getFeedback();
    }

}
