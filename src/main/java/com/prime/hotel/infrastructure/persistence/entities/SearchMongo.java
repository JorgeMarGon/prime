package com.prime.hotel.infrastructure.persistence.entities;

import java.time.LocalDate;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "search")
public record SearchMongo(
    @Id String searchId,
    @Indexed String hotelId,
    LocalDate checkIn,
    LocalDate checkOut,
    Set<Integer> ages) {}
