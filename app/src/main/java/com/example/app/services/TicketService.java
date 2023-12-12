package com.example.app.services;

import com.example.app.entities.Ticket;
import com.example.app.repos.TicketRepo;
import com.example.app.services.dtos.TicketDTO;
import com.example.app.services.mappers.TicketMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TicketService {
    private final TicketRepo ticketRepo;
    private final TicketMapper ticketMapper;

    public TicketService(TicketRepo ticketRepo, TicketMapper ticketMapper) {
        this.ticketRepo = ticketRepo;
        this.ticketMapper = ticketMapper;
    }

    public Page<TicketDTO> getAll(Pageable pageable) {
        log.debug("Request to get all TICKETS");
        return ticketRepo.findAll(pageable).map(ticketMapper::toDTO);
    }

    public TicketDTO getOne(Long id) {
        log.debug("Request to get TICKET by ID: {}", id);
        return ticketMapper.toDTO(ticketRepo.findById(id).orElse(null));
    }

    public TicketDTO save(TicketDTO ticketDTO) {
        log.debug("Request to save TICKET: {}", ticketDTO);
        if (ticketDTO.getId() != null) {
            throw new IllegalArgumentException();
        }
        final var ticket = ticketMapper.toEntity(ticketDTO);
        return save(ticket);
    }

    public TicketDTO update(TicketDTO ticketDTO) {
        log.debug("Request to update TICKET: {}", ticketDTO);
        if (ticketDTO.getId() == null) {
            throw new IllegalArgumentException();
        }
        final var ticket = ticketMapper.toEntity(ticketDTO);
        return save(ticket);
    }

    private TicketDTO save(Ticket ticket) {
        final var savedTicket = ticketRepo.save(ticket);
        return ticketMapper.toDTO(savedTicket);
    }

    public void delete(Long id) {
        log.debug("Request to delete TICKET with ID: {}", id);
        ticketRepo.deleteById(id);
    }
}
