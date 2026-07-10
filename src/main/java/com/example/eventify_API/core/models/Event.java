package com.example.eventify_API.core.models;



import java.time.LocalDateTime;
import java.util.UUID;

public record Event(  UUID Id,
         String name,
         String description,
         String local,
         LocalDateTime init,
         LocalDateTime end,
         Integer capacity,
         EventType type){}