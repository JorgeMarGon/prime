package com.prime.hotel.infrastructure.controllers;

import com.prime.hotel.application.common.handlers.SearchCommandHandler;
import com.prime.hotel.application.common.handlers.SearchQueryHandler;
import com.prime.hotel.application.create_search.PublishCreateSearchCommand;
import com.prime.hotel.application.get_search.GetSearchQuery;
import com.prime.hotel.domain.models.Search;
import com.prime.hotel.infrastructure.mappers.SearchMapper;
import com.prime.hotel.infrastructure.projections.HotelSearchDTO;
import com.prime.hotel.infrastructure.projections.output.HotelSearchCountODTO;
import com.prime.hotel.infrastructure.projections.output.HotelSearchODTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductPriceController {

  private final SearchMapper searchMapper;
  private final SearchCommandHandler searchCommandHandler;
  private final SearchQueryHandler searchQueryHandler;

  public ProductPriceController(
      SearchMapper searchMapper,
      SearchCommandHandler searchCommandHandler,
      SearchQueryHandler searchQueryHandler) {
    this.searchMapper = searchMapper;
    this.searchCommandHandler = searchCommandHandler;
    this.searchQueryHandler = searchQueryHandler;
  }

  @PostMapping("/search")
  public ResponseEntity<HotelSearchODTO> createHotelSearch(
      @Valid @RequestBody HotelSearchDTO hotelSearchDTO) {

    Search search = searchMapper.inputToDomain(hotelSearchDTO);
    Search handle = searchCommandHandler.handle(new PublishCreateSearchCommand(search));
    return ResponseEntity.ok(searchMapper.domainToOutput(handle));
  }

  @GetMapping("/count/{id}")
  public ResponseEntity<HotelSearchCountODTO> createHotelSearch(@PathVariable("id") String id) {

    Search handle = searchQueryHandler.handle(new GetSearchQuery(id));
    return ResponseEntity.ok(searchMapper.domainToSearchCountODTO(handle));
  }
}
