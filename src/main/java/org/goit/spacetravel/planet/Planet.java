package org.goit.spacetravel.planet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;
import org.goit.spacetravel.ticket.Ticket;

import java.util.List;

@Entity
@Data
public class Planet {
    @Id
    private String id;
    @Column
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "planetFrom")
    private List<Ticket> ticketsFromList;

    @ToString.Exclude
    @OneToMany(mappedBy = "planetTo")
    private List<Ticket> ticketsToList;
}
