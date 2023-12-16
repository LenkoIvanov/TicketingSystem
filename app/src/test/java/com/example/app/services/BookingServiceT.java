package com.example.app.services;

import com.example.app.entities.enums.PassengerType;
import com.example.app.services.dtos.BookingDTO;
import com.example.app.services.dtos.DestinationDTO;
import com.example.app.services.dtos.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
public class BookingServiceT {

    @MockBean
    private UserService userService;

    @MockBean
    private DestinationService destinationService;

    @Autowired
    private BookingService bookingService;

    private AtomicLong destinationId = new AtomicLong(0);

    @Test
    void test() {
        UserDTO userDTO = createUser(PassengerType.NONE, 22);
        Mockito.when(userService.getOne(anyLong())).thenReturn(userDTO);

        DestinationDTO firstDestination = createDestination("Sofia");
        Mockito.when(destinationService.getOne(firstDestination.getId())).thenReturn(firstDestination);
        DestinationDTO secondDestination = createDestination("Plovdiv");
        Mockito.when(destinationService.getOne(secondDestination.getId())).thenReturn(secondDestination);

        BookingDTO bookingDTO = createBooking(
                userDTO,
                Boolean.FALSE,
                LocalTime.of(8, 30),
                firstDestination.getId()
        );

        BigDecimal price = bookingService.calculatePrice(bookingDTO);

        Assertions.assertEquals(BigDecimal.valueOf(0), price);
    }

    private UserDTO createUser(PassengerType type, int age) {
         return new UserDTO(
                1L,
                "aaaa",
                "bbbb",
                age,
                type.toString()
        );
    }

    private DestinationDTO createDestination(String name) {
        return new DestinationDTO(destinationId.incrementAndGet(), name);
    }

    private BookingDTO createBooking(UserDTO userDTO, Boolean withChild, LocalTime departureTime, Long... destinationIds){
        return new BookingDTO(userDTO.getId(), withChild, departureTime, List.of(destinationIds));
    }


}
