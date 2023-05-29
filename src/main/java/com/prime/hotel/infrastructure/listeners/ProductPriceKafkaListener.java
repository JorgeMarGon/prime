package com.prime.hotel.infrastructure.listeners;

import com.prime.hotel.application.common.handlers.SearchCommandHandler;
import com.prime.hotel.application.create_search.CreateSearchCommand;
import com.prime.hotel.domain.models.Search;
import com.prime.hotel.infrastructure.mappers.SearchMapper;
import com.prime.hotel.infrastructure.projections.HotelSearchKafkaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class ProductPriceKafkaListener {

  private final SearchMapper searchMapper;
  private final SearchCommandHandler searchCommandHandler;

  public ProductPriceKafkaListener(
      SearchMapper searchMapper, SearchCommandHandler searchCommandHandler) {
    this.searchMapper = searchMapper;
    this.searchCommandHandler = searchCommandHandler;
  }

  @KafkaListener(topics = "${kafka.topic.hotel.persist.search}")
  public void persistSearchEvent(@Payload HotelSearchKafkaDTO message) {

    Search search = searchMapper.kafkaDTOToDomain(message);
    searchCommandHandler.handle(new CreateSearchCommand(search));
  }
}
