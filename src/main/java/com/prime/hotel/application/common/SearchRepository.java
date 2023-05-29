package com.prime.hotel.application.common;

import com.prime.hotel.domain.models.Search;

public interface SearchRepository {

  void save(Search search);

  Search getById(String id);
}
