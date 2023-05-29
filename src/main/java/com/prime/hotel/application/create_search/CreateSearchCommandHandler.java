package com.prime.hotel.application.create_search;

import com.prime.hotel.application.common.SearchRepository;
import com.prime.hotel.application.common.handlers.SearchCommandHandler;
import com.prime.hotel.domain.models.Search;
import org.springframework.stereotype.Service;

@Service
public class CreateSearchCommandHandler implements SearchCommandHandler {

  private final PublishSearchRepository publishSearchRepository;
  private final SearchRepository searchRepository;

  public CreateSearchCommandHandler(
      PublishSearchRepository publishSearchRepository, SearchRepository searchRepository) {
    this.publishSearchRepository = publishSearchRepository;
    this.searchRepository = searchRepository;
  }

  public void handle(CreateSearchCommand command) {
    searchRepository.save(command.search());
  }

  public Search handle(PublishCreateSearchCommand command) {
    command.search().generateId();

    publishSearchRepository.send(command.search());
    return command.search();
  }
}
