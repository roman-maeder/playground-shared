package org.playground.paging;

import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * Page DTO containing paging information for a resource query.
 * Starts querying resources at a given page number and takes a number of results from there.
 */
public final class PageDto {

  public static final String PAGE_NUMBER_PROPERTY_NAME = "pageNumber";
  public static final String PAGE_SIZE_PROPERTY_NAME = "pageSize";

  private final String pageNumber;
  private final String pageSize;

  /**
   * Required by frameworks like Spring with Jackson when deserializing a page DTO.
   */
  private PageDto() {
    this(null, null);
  }

  /**
   * Creates a page DTO with the given page number and page size values.
   *
   * @param pageNumberValue The page number value.
   * @param pageSizeValue The page size value.
   */
  public PageDto(final String pageNumberValue, final String pageSizeValue) {
    this.pageNumber = pageNumberValue;
    this.pageSize = pageSizeValue;
  }

  /**
   * Creates a page DTO with the given page number and page size.
   *
   * @param pageNumber The page number.
   * @param pageSize The page size.
   */
  public PageDto(final int pageNumber, final int pageSize) {
    this.pageNumber = String.valueOf(pageNumber);
    this.pageSize = String.valueOf(pageSize);
  }

  public Optional<String> getPageNumber() {
    return ofNullable(pageNumber);
  }

  public Optional<String> getPageSize() {
    return ofNullable(pageSize);
  }

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof PageDto that)) return false;
    return Objects.equals(pageNumber, that.pageNumber) && Objects.equals(pageSize, that.pageSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pageNumber, pageSize);
  }
}
