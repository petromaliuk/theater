package com.theater.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NamedEntityGraph(name = "graph.Ticket.seance",
        attributeNodes = { @NamedAttributeNode(value = "seance", subgraph = "subgraph.seance") },
        subgraphs = {
            @NamedSubgraph(name = "subgraph.seance",
                attributeNodes = { @NamedAttributeNode("film"), @NamedAttributeNode("hall")})
        })
@Entity
@Table(name = "tickets")
@Data
@AllArgsConstructor
//@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
@NoArgsConstructor
public class Ticket {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "seance", nullable = false, referencedColumnName = "id")
    @JsonView(Views.PartTicket.class)
    private Seance seance;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "place", nullable = false)
    @JsonView(Views.IdName.class)
    private int place;

    @Column(name = "rown", nullable = false)
    @JsonView(Views.IdName.class)
    private int rown;
    public String getNameOfFilm(){ return seance.getFilm().getTitle(); }


}
