package com.example.eventify_API.infra.persistence.gateway;

import com.example.eventify_API.core.entities.Event;
import com.example.eventify_API.core.ports.EventRepositoryPort;
import com.example.eventify_API.infra.persistence.repository.JpaEventRepository;

import java.util.List;
import java.util.Optional;

public class EventRepositoryGateway implements EventRepositoryPort {

    private final JpaEventRepository jpaRepository;

    public EventRepositoryGateway(JpaEventRepository jpaRepository){
        this.jpaRepository = jpaRepository;
    }
    @Override
    public Event save(Event event) {
        return null;
    }

    @Override
    public Optional<Event> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Event> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }
}
