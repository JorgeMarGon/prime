package com.prime.hotel.infrastructure.projections;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.Set;

public record HotelSearchKafkaDTO(
    String searchId,
    String hotelId,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC")
        LocalDate checkIn,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC")
        LocalDate checkOut,
    Set<Integer> ages) {}
