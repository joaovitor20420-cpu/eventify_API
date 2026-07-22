package com.example.eventify_API.infra.persistence.mapper;

import com.example.eventify_API.core.entities.Event;
import com.example.eventify_API.infra.dataBase.EventEntity;

public class EventEntityMapper {
    public static EventEntity toEntity(Event event){
        EventEntity entity = new EventEntity();

        entity.setId(event.Id());
        entity.setInit(event.init());
        entity.setEnd(event.end());
        entity.setLocal(event.local());
        entity.setName(event.name());
        entity.setCapacity(event.capacity());
        entity.setDescription(event.description());
        entity.setType(event.type());

        return entity;
    }
    public static Event toDomain(EventEntity entity){
        return new Event(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getLocal(),
                entity.getInit(),
                entity.getEnd(),
                entity.getCapacity(),
                entity.getType());

    }
}
