package com.prime.hotel.application.common.handlers;

import com.prime.hotel.application.create_search.CreateSearchCommand;
import com.prime.hotel.application.create_search.PublishCreateSearchCommand;
import com.prime.hotel.domain.models.Search;

public interface SearchCommandHandler {

  void handle(CreateSearchCommand command);

  Search handle(PublishCreateSearchCommand command);
}
