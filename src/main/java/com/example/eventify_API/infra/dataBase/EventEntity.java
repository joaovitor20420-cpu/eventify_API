package com.example.eventify_API.infra.dataBase;

import com.example.eventify_API.core.Enuns.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Eventos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long Id;
   private String description;
   private String name;
   private String local;
   private LocalDateTime init;
   private LocalDateTime end;
   private Integer capacity;
   private EventType type;
}
