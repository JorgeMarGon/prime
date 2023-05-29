package com.prime.hotel.infrastructure.mappers;

import com.prime.hotel.domain.models.Search;
import com.prime.hotel.infrastructure.persistence.entities.SearchMongo;
import com.prime.hotel.infrastructure.projections.HotelSearchDTO;
import com.prime.hotel.infrastructure.projections.HotelSearchKafkaDTO;
import com.prime.hotel.infrastructure.projections.output.HotelSearchCountODTO;
import com.prime.hotel.infrastructure.projections.output.HotelSearchODTO;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SearchMapper {

  Search inputToDomain(HotelSearchDTO hotelSearchDTO);

  @Mapping(target = "id", source = "searchId")
  Search kafkaDTOToDomain(HotelSearchKafkaDTO hotelSearchKafkaDTO);

  @Mapping(target = "searchId", source = "id")
  SearchMongo domainToMongo(Search search);

  @Mapping(target = "id", source = "searchId")
  Search mongoToDomain(SearchMongo searchMongo);

  @Mapping(target = "searchId", source = "id")
  HotelSearchODTO domainToOutput(Search search);

  @Mapping(target = "searchId", source = "id")
  @Mapping(target = "search.hotelId", source = "hotelId")
  @Mapping(target = "search.checkIn", source = "checkIn")
  @Mapping(target = "search.checkOut", source = "checkOut")
  @Mapping(target = "search.ages", source = "ages")
  HotelSearchCountODTO domainToSearchCountODTO(Search search);

  @Mapping(target = "searchId", source = "id")
  HotelSearchKafkaDTO domainToKafkaODTO(Search search);

  default ObjectId map(String value) {
    return new ObjectId(value);
  }

  default String map(ObjectId value) {
    return value.toString();
  }
}
