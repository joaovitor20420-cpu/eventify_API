package com.example.eventify_API.core.entities;



import com.example.eventify_API.core.Enuns.EventType;
import java.time.LocalDateTime;
import java.util.UUID;

public record Event(  Long Id,
         String name,
         String description,
         String local,
         LocalDateTime init,
         LocalDateTime end,
         Integer capacity,
         EventType type){}