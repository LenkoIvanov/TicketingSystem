package com.example.app.services.mappers;

import com.example.app.entities.Ticket;
import com.example.app.services.dtos.TicketDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, DestinationMapper.class})
public interface TicketMapper {
    Ticket toEntity(TicketDTO ticketDTO);
    TicketDTO toDTO(Ticket ticket);
}
