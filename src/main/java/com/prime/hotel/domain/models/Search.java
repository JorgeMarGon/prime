package com.prime.hotel.domain.models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import org.bson.types.ObjectId;

public class Search {
  private ObjectId id;
  private String hotelId;
  private LocalDate checkOut;
  private LocalDate checkIn;
  private Set<Integer> ages;
  private int count;

  public Search() {}

  public void generateId() {
    this.id = new ObjectId();
  }

  public ObjectId getId() {
    return this.id;
  }

  public String getHotelId() {
    return this.hotelId;
  }

  public LocalDate getCheckOut() {
    return this.checkOut;
  }

  public LocalDate getCheckIn() {
    return this.checkIn;
  }

  public Set<Integer> getAges() {
    return this.ages;
  }

  public long getCount() {
    return this.count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setHotelId(String hotelId) {
    this.hotelId = hotelId;
  }

  public void setCheckOut(LocalDate checkOut) {
    this.checkOut = checkOut;
  }

  public void setCheckIn(LocalDate checkIn) {
    this.checkIn = checkIn;
  }

  public void setAges(Set<Integer> ages) {
    this.ages = ages;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Search search = (Search) o;
    return Objects.equals(id, search.id)
        && Objects.equals(hotelId, search.hotelId)
        && Objects.equals(checkOut, search.checkOut)
        && Objects.equals(checkIn, search.checkIn)
        && Objects.equals(ages, search.ages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, hotelId, checkOut, checkIn, ages);
  }

  @Override
  public String toString() {
    return "Search{"
        + "id="
        + id
        + ", hotelId='"
        + hotelId
        + '\''
        + ", checkOut="
        + checkOut
        + ", checkIn="
        + checkIn
        + ", ages="
        + ages
        + ", count="
        + count
        + '}';
  }
}
