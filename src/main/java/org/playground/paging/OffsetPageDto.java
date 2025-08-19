package org.playground.paging;

import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * Page DTO containing offset paging information for a resource query.
 * Starts querying resources by skipping a number of entries and then starts taking a number of results from there.
 */
public final class OffsetPageDto {

  public static final String SKIP_PROPERTY_NAME = "skip";
  public static final String TAKE_PROPERTY_NAME = "take";

  private final String skip;
  private final String take;

  /**
   * Required by frameworks like Spring with Jackson when deserializing an offset page DTO.
   */
  private OffsetPageDto() {
    this(null, null);
  }

  /**
   * Creates an offset page DTO with the given skip and take values.
   *
   * @param skipValue The number of entries to skip.
   * @param takeValue The number of results to take.
   */
  public OffsetPageDto(final String skipValue, final String takeValue) {
    this.skip = skipValue;
    this.take = takeValue;
  }

  /**
   * Creates an offset page DTO with the given skip and take value.
   *
   * @param skip The number of entries to skip.
   * @param take The number of results to take.
   */
  public OffsetPageDto(final int skip, final int take) {
    this.skip = String.valueOf(skip);
    this.take = String.valueOf(take);
  }

  public Optional<String> getSkip() {
    return ofNullable(skip);
  }

  public Optional<String> getTake() {
    return ofNullable(take);
  }

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof OffsetPageDto that)) return false;
    return Objects.equals(skip, that.skip) && Objects.equals(take, that.take);
  }

  @Override
  public int hashCode() {
    return Objects.hash(skip, take);
  }
}
