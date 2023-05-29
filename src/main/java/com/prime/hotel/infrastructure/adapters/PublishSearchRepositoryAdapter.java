package com.prime.hotel.infrastructure.adapters;

import com.prime.hotel.application.create_search.PublishSearchRepository;
import com.prime.hotel.domain.models.Search;
import com.prime.hotel.infrastructure.mappers.SearchMapper;
import com.prime.hotel.infrastructure.projections.HotelSearchKafkaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PublishSearchRepositoryAdapter implements PublishSearchRepository {

  @Value("${kafka.topic.hotel.persist.search}")
  private String persistSearchTopic;

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final SearchMapper searchMapper;

  public PublishSearchRepositoryAdapter(
      KafkaTemplate<String, Object> kafkaTemplate, SearchMapper searchMapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.searchMapper = searchMapper;
  }

  @Override
  public void send(Search search) {
    HotelSearchKafkaDTO hotelSearchKafkaDTO = searchMapper.domainToKafkaODTO(search);
    kafkaTemplate.send(persistSearchTopic, hotelSearchKafkaDTO);
  }
}
