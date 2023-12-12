package com.example.app.services.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDTO {
    private Long id;
    private UserDTO user;
    private BigDecimal price;
    private List<DestinationDTO> destinationList = new ArrayList<>();
}
