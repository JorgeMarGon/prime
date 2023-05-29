package com.prime.hotel.application.create_search;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import com.prime.hotel.application.common.SearchRepository;
import com.prime.hotel.domain.models.Search;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateSearchCommandHandlerTest {

  @Mock private PublishSearchRepository publishSearchRepository;
  @Mock private SearchRepository searchRepository;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Search search;

  @InjectMocks private CreateSearchCommandHandler mockHandler;

  @Test
  public void handle_shouldSaveSearch() {

    mockHandler.handle(new CreateSearchCommand(search));

    verify(searchRepository).save(search);
  }

  @Test
  public void handle_shouldSendSearchAndReturnSearch() {
    PublishCreateSearchCommand command = new PublishCreateSearchCommand(search);

    doNothing().when(publishSearchRepository).send(search);
    Search result = mockHandler.handle(command);

    verify(search).generateId();
    verify(publishSearchRepository).send(search);
    assertEquals(search, result);
  }
}
