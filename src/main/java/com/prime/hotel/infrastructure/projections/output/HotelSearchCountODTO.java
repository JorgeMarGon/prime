package com.prime.hotel.infrastructure.projections.output;

import com.prime.hotel.infrastructure.projections.HotelSearchDTO;

public record HotelSearchCountODTO(String searchId, HotelSearchDTO search, int count) {}
