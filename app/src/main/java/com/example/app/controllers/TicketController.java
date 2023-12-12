package com.example.app.controllers;

import com.example.app.services.TicketService;
import com.example.app.services.dtos.TicketDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<Page<TicketDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get all TICKETS sorted by {}, page number: {} and page size: {}",
                pageable.getSort(), pageable.getPageNumber(), pageable.getPageSize());
        final var tickets = ticketService.getAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getOne(@PathVariable Long id) {
        log.debug("REST request to get TICKET by ID: {}", id);
        final var ticket = ticketService.getOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }

    @PostMapping
    public ResponseEntity<TicketDTO> save(@RequestBody TicketDTO ticketDTO) {
        log.debug("REST request to save TICKET with content: {}", ticketDTO);
        final var savedTicket = ticketService.save(ticketDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTicket);
    }

    @PutMapping
    public ResponseEntity<TicketDTO> update(@RequestBody TicketDTO ticketDTO) {
        log.debug("REST request to updated TICKET with ID: {} with content {}", ticketDTO.getId(), ticketDTO);
        final var updatedTicket = ticketService.update(ticketDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete TICKET with ID: {}", id);
        ticketService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
