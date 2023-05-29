package com.prime.hotel.infrastructure.mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.prime.hotel.domain.models.Search;
import com.prime.hotel.infrastructure.persistence.entities.SearchMongo;
import com.prime.hotel.infrastructure.projections.HotelSearchKafkaDTO;
import com.prime.hotel.infrastructure.projections.output.HotelSearchCountODTO;
import java.time.LocalDate;
import java.util.Set;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
class SearchMapperTest {

  private static PodamFactory factory;

  @InjectMocks private SearchMapperImpl searchMapper;

  @BeforeAll
  public static void setUp() {
    factory = new PodamFactoryImpl();
  }

  @Test
  void kafkaDTOToDomain() {
    HotelSearchKafkaDTO lol =
        new HotelSearchKafkaDTO(
            new ObjectId().toString(), "123", LocalDate.now(), LocalDate.now(), Set.of(1));
    Search search = searchMapper.kafkaDTOToDomain(lol);
    assertNotNull(search);
    assertEquals(search.getId().toString(), lol.searchId());
  }

  @Test
  void domainToMongo() {
    Search search = new Search();
    search.generateId();

    SearchMongo searchMongo = searchMapper.domainToMongo(search);

    assertNotNull(searchMongo);
    assertEquals(searchMongo.searchId(), search.getId().toString());
  }

  @Test
  void domainToSearchCountODTO() {
    Search search = factory.manufacturePojo(Search.class);

    HotelSearchCountODTO hotelSearchCountODTO = searchMapper.domainToSearchCountODTO(search);

    assertNotNull(hotelSearchCountODTO);
    assertAll(
        () -> assertThat(hotelSearchCountODTO.searchId()).isEqualTo(search.getId().toString()),
        () -> assertThat(hotelSearchCountODTO.search().checkIn()).isEqualTo(search.getCheckIn()),
        () -> assertThat(hotelSearchCountODTO.search().checkOut()).isEqualTo(search.getCheckOut()),
        () -> assertThat(hotelSearchCountODTO.search().hotelId()).isEqualTo(search.getHotelId()),
        () -> assertThat(hotelSearchCountODTO.search().ages()).isEqualTo(search.getAges()),
        () -> assertThat(hotelSearchCountODTO.count()).isEqualTo(search.getCount()));
  }
}
