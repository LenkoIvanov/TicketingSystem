package com.example.app.services;

import com.example.app.entities.Dummy;
import com.example.app.repos.DummyRepo;
import com.example.app.services.dtos.DummyDTO;
import com.example.app.services.mappers.DummyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DummyService {
    private final DummyRepo dummyRepo;
    private final DummyMapper dummyMapper;

    public DummyService(DummyRepo dummyRepo, DummyMapper dummyMapper) {
        this.dummyRepo = dummyRepo;
        this.dummyMapper = dummyMapper;
    }

    public List<DummyDTO> getAll() {
        log.debug("Request to get all DUMMIES");
//        return dummyRepo.findAll().stream()
//                .map(e -> dummyMapper.toDTO(e))
//                .collect(Collectors.toList());
        List<DummyDTO> dummyList = new ArrayList<>();
        dummyList.add(new DummyDTO(1L, "FIRST", 23));
        dummyList.add(new DummyDTO(2L, "SECOND", 24));
        dummyList.add(new DummyDTO(3L, "THIRD", 25));
        dummyList.add(new DummyDTO(4L, "FOURTH", 26));
        return dummyList;
    }
}
