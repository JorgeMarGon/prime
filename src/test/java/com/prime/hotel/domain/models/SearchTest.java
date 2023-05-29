package com.prime.hotel.domain.models;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchTest {

  private static Search search;

  @BeforeAll
  public static void setUp() {
    search = new Search();
  }

  @Test
  public void generateId_shouldSetGeneratedId() {
    search.generateId();

    assertNotNull(search.getId());
    assertEquals(24, search.getId().toHexString().length());
  }

  @Test
  public void generateId_shouldSetUniqueId() {
    search.generateId();
    ObjectId firstId = search.getId();

    search.generateId();
    ObjectId secondId = search.getId();

    assertNotNull(firstId);
    assertNotNull(secondId);
    assertEquals(
        24, firstId.toHexString().length()); // ObjectId should be a 24-character hexadecimal string
    assertEquals(24, secondId.toHexString().length());
    assertNotEquals(firstId, secondId); // Ids should be unique
  }
}
