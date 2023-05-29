package com.prime.hotel.infrastructure.adapters;

import com.prime.common.exceptions.RestApiException;
import com.prime.hotel.application.common.SearchRepository;
import com.prime.hotel.domain.models.Search;
import com.prime.hotel.infrastructure.errors.SearchErrorCode;
import com.prime.hotel.infrastructure.mappers.SearchMapper;
import com.prime.hotel.infrastructure.persistence.dao.SearchRepositoryMongo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SearchRepositoryAdapter implements SearchRepository {

  private final SearchRepositoryMongo searchRepositoryMongo;

  private final SearchMapper searchMapper;

  public SearchRepositoryAdapter(
      SearchRepositoryMongo searchRepositoryMongo, SearchMapper searchMapper) {
    this.searchRepositoryMongo = searchRepositoryMongo;
    this.searchMapper = searchMapper;
  }

  @Override
  public void save(Search search) {
    searchRepositoryMongo.save(searchMapper.domainToMongo(search));
  }

  @Override
  public Search getById(String id) {

    Search search =
        searchRepositoryMongo
            .findById(id)
            .map(searchMapper::mongoToDomain)
            .orElseThrow(
                () -> new RestApiException(SearchErrorCode.SEARCH_NOT_FOUND, HttpStatus.NOT_FOUND));
    findSimilarEntries(search);
    return search;
  }

  private void findSimilarEntries(Search search) {
    search.setCount(
        searchRepositoryMongo.countByHotelIdAndCheckInGreaterThanEqualAndCheckOutLessThanEqual(
            search.getHotelId(), search.getCheckIn(), search.getCheckOut()));
  }
}
