package com.prime.hotel.application.common.handlers;

import com.prime.hotel.application.get_search.GetSearchQuery;
import com.prime.hotel.domain.models.Search;

public interface SearchQueryHandler {

  Search handle(GetSearchQuery query);
}
