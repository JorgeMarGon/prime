package com.prime.hotel.application.get_search;

import com.prime.hotel.application.common.SearchRepository;
import com.prime.hotel.application.common.handlers.SearchQueryHandler;
import com.prime.hotel.domain.models.Search;
import org.springframework.stereotype.Service;

@Service
public class GetSearchQueryHandler implements SearchQueryHandler {

  private final SearchRepository searchRepository;

  public GetSearchQueryHandler(SearchRepository searchRepository) {
    this.searchRepository = searchRepository;
  }

  public Search handle(GetSearchQuery query) {

    return searchRepository.getById(query.id());
  }
}
