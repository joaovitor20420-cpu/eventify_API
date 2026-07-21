package com.example.eventify_API.infra.persistence.repository;

import com.example.eventify_API.infra.dataBase.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEventRepository extends JpaRepository<EventEntity, Long> {
}
