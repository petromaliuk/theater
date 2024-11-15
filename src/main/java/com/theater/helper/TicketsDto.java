package com.theater.helper;

import com.fasterxml.jackson.annotation.JsonView;
import com.theater.entity.Seance;
import com.theater.entity.Ticket;
import com.theater.entity.Views;
import lombok.Data;

import java.util.List;

@Data
public class TicketsDto {
    @JsonView(Views.PartTicket.class)
    private List<Ticket> tickets;
    @JsonView(Views.PartTicket.class)
    private List<Seance> seances;

    public int[] seances(){
        return new int[3];
    }
}
