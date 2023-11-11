package com.example.app.repos;

import com.example.app.entities.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DummyRepo extends JpaRepository<Dummy, Long> {

}
