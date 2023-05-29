package com.prime.hotel.infrastructure.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.prime.common.exceptions.RestApiException;
import com.prime.hotel.domain.models.Search;
import com.prime.hotel.infrastructure.errors.SearchErrorCode;
import com.prime.hotel.infrastructure.mappers.SearchMapper;
import com.prime.hotel.infrastructure.persistence.dao.SearchRepositoryMongo;
import com.prime.hotel.infrastructure.persistence.entities.SearchMongo;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchRepositoryAdapterTest {

  @Mock private SearchRepositoryMongo searchRepositoryMongo;
  @Mock private SearchMapper searchMapper;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Search search;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private SearchMongo searchMongo;

  @InjectMocks SearchRepositoryAdapter mockAdapter;

  @Test
  void save_shouldCallSaveOnSearchRepositoryMongo() {
    mockAdapter.save(search);
    verify(searchRepositoryMongo).save(any());
  }

  @Test
  void getById_shouldReturnSearchWithSimilarEntriesCount() {

    Search sch = new Search();
    search.generateId();

    when(searchRepositoryMongo.findById(any())).thenReturn(Optional.of(searchMongo));
    when(searchMapper.mongoToDomain(any())).thenReturn(sch);
    when(searchRepositoryMongo.countByHotelIdAndCheckInGreaterThanEqualAndCheckOutLessThanEqual(
            any(), any(), any()))
        .thenReturn(5);

    Search result = mockAdapter.getById("3");

    assertEquals(sch, result);
    assertEquals(5, result.getCount());

    verify(searchRepositoryMongo).findById(any());
    verify(searchMapper).mongoToDomain(any());
    verify(searchRepositoryMongo)
        .countByHotelIdAndCheckInGreaterThanEqualAndCheckOutLessThanEqual(any(), any(), any());
  }

  @Test
  void getById_shouldThrowRestApiExceptionWhenSearchNotFound() {

    when(searchRepositoryMongo.findById(any())).thenReturn(Optional.empty());

    RestApiException ex = assertThrows(RestApiException.class, () -> mockAdapter.getById("123"));
    verify(searchMapper, never()).mongoToDomain(any());
    assertEquals(ex.getError().getMessage(), SearchErrorCode.SEARCH_NOT_FOUND.getMessage());
  }
}
