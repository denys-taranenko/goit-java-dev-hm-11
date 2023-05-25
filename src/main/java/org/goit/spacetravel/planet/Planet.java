package org.goit.spacetravel.planet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Planet {
    @Id
    private String id;
    @Column
    private String name;
}
