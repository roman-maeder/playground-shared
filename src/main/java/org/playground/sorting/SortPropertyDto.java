package org.playground.sorting;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import static org.playground.sorting.DtoSortOrder.DEFAULT_SORT_ORDER;

/**
 * Value object containing the sort property information for a resource query.
 * Contains the name of the sort property and its sort order.
 */
public final class SortPropertyDto {

  public static final String SORT_BY_PROPERTY_NAME = "name";
  public static final String SORT_ORDER_PROPERTY_NAME = "order";

  private final String name;
  private final String order;

  /**
   * Required by frameworks like Spring with Jackson when deserializing a sort property DTO.
   */
  private SortPropertyDto() {
    this.name = null;
    this.order = null;
  }

  /**
   * Creates a sort property DTO with the given name and default sort order.
   *
   * @param sortBy The name of the property to sort by. Must not be {@code null}.
   * @throws IllegalArgumentException If the argument is invalid.
   */
  public SortPropertyDto(final String sortBy) {
    this(sortBy, DEFAULT_SORT_ORDER);
  }

  /**
   * Creates a sort property with the given name and sort order.
   *
   * @param sortBy The name of the property to sort by. Must not be {@code null}.
   * @param sortOrder The sort order. Must not be {@code null}.
   * @throws IllegalArgumentException If the arguments are invalid.
   */
  public SortPropertyDto(final String sortBy, final DtoSortOrder sortOrder) {
    this.name = requireNonNull(sortBy);
    this.order = requireNonNull(sortOrder).name();
  }

  public Optional<String> getName() {
    return ofNullable(name);
  }

  public Optional<String> getOrder() {
    return ofNullable(order);
  }

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof SortPropertyDto that)) return false;
    return Objects.equals(name, that.name) && Objects.equals(order, that.order);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, order);
  }
}
