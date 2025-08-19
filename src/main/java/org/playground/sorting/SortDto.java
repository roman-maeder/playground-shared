package org.playground.sorting;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;

/**
 * Value object containing the sort properties for a resource query.
 */
public class SortDto {

  public static final String PROPERTIES_PROPERTY_NAME = "properties";

  private final List<SortPropertyDto> properties;

  /**
   * Required by frameworks like Spring with Jackson when deserializing a sort DTO.
   */
  private SortDto() {
    this.properties = emptyList();
  }

  /**
   * Creates a sort DTO with the given sort properties.
   *
   * @param properties The sort properties. Must not be {@code null}.
   * @throws IllegalArgumentException If the argument is invalid.
   */
  public SortDto(final List<SortPropertyDto> properties) {
    this.properties = unmodifiableList(requireNonNull(properties));
  }

  public List<SortPropertyDto> getProperties() {
    return properties;
  }

  /**
   * Checks whether this sort DTO contains sort properties or not.
   *
   * @return {@code true} when this sort DTO contains no sort properties and {@code false} otherwise.
   */
  public boolean isEmpty() {
    return properties.isEmpty();
  }

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof SortDto sortDto)) return false;
    return Objects.equals(properties, sortDto.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(properties);
  }
}
