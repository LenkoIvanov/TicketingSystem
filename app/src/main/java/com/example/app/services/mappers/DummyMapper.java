package com.example.app.services.mappers;

import com.example.app.entities.Dummy;
import com.example.app.services.dtos.DummyDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // makes an entity into a DTO and vice versa
public interface DummyMapper {
    Dummy toEntity(DummyDTO dto);
    DummyDTO toDTO(Dummy entity);
}
