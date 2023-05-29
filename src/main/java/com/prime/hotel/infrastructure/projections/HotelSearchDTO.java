package com.prime.hotel.infrastructure.projections;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Set;

public record HotelSearchDTO(
    @NotBlank(message = "Hotel identifier is mandatory") String hotelId,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
        @FutureOrPresent(message = "Check-in date must be in the present or future")
        LocalDate checkIn,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
        @FutureOrPresent(message = "Check-out date must be in the present or future")
        LocalDate checkOut,
    @NotEmpty(message = "Ages cannot be empty")
        Set<@Positive(message = "Age must be a positive number") Integer> ages) {}
