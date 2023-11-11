package com.example.app.controllers;



import com.example.app.services.DummyService;
import com.example.app.services.dtos.DummyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j // generates private final Logger log = LoggerFactory.getLogger(DummyController.class);
@RestController
@RequestMapping(value = "/api/dummy/", produces = MediaType.APPLICATION_JSON_VALUE)
public class DummyController {
    private final DummyService dummyService;

    public DummyController(DummyService dummyService) { // dependencies go in the constructor
        this.dummyService = dummyService;
    }

    @GetMapping
    public ResponseEntity<List<DummyDTO>> getAll() {
        log.debug("Rest request to get all dummies");

        //dynamic typing
        final var dummies = dummyService.getAll();
        return ResponseEntity.ok().body(dummies);
    }
}
