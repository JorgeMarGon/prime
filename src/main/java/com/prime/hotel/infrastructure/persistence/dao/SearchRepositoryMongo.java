package com.prime.hotel.infrastructure.persistence.dao;

import com.prime.hotel.infrastructure.persistence.entities.SearchMongo;
import java.time.LocalDate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepositoryMongo extends MongoRepository<SearchMongo, String> {
  int countByHotelIdAndCheckInGreaterThanEqualAndCheckOutLessThanEqual(
      String hotelId, LocalDate checkIn, LocalDate checkOut);
}
