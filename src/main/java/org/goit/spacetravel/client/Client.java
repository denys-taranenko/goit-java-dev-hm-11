package org.goit.spacetravel.client;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.goit.spacetravel.ticket.Ticket;

import java.util.List;

@Entity
@Data
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "client")
    private List<Ticket> ticketList;
}
