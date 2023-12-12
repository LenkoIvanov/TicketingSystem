package com.example.app.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ticket")
public class Ticket {

    @Id
    @GeneratedValue(generator = "ticket_sequence_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ticket_sequence_generator", initialValue = 1000, allocationSize = 1)
    private Long id;

    @ManyToOne
    private User user;

    private BigDecimal price;

    @ManyToMany
    @JoinTable(name = "ticket_2_destination",
            joinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "destination_id", referencedColumnName = "id"))
    private List<Destination> destinationList = new ArrayList<>();
}