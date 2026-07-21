package com.example.eventify_API.core.ports;

import com.example.eventify_API.core.entities.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepositoryPort {

    Event save(Event event);

    Optional<Event> findById(Long id);

    List<Event> findAll();

    void deleteById(Long id);
}
