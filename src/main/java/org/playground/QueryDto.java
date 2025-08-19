package org.playground;

import org.playground.filtering.FilterDto;
import org.playground.paging.OffsetPageDto;
import org.playground.sorting.SortDto;

import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

public final class QueryDto {

  public static final String PAGE_DTO_PROPERTY_NAME = "page";
  public static final String SORT_DTO_PROPERTY_NAME = "sort";
  public static final String FILTER_DTO_PROPERTY_NAME = "filter";
  public static final String SEARCH_TEXT_PROPERTY_NAME = "searchText";
  public static final String TOTAL_COUNT_PROPERTY_NAME = "totalCount";

  private final OffsetPageDto page;
  private final SortDto sort;
  private final FilterDto filter;
  private String searchText;
  private boolean totalCount;

  /**
   * Required by frameworks like Spring with Jackson when deserializing a query DTO.
   */
  private QueryDto() {
    this(null, null, null, null, false);
  }

  public QueryDto(final OffsetPageDto pageDto) {
    this(pageDto, null, null, null, false);
  }

  public QueryDto(final SortDto sortDto) {
    this(null, sortDto, null, null, false);
  }

  public QueryDto(final FilterDto filterDto) {
    this(null, null, filterDto, null, false);
  }

  public QueryDto(final OffsetPageDto pageDto, final SortDto sortDto) {
    this(pageDto, sortDto, null, null, false);
  }

  public QueryDto(final OffsetPageDto pageDto, final SortDto sortDto, final FilterDto filterDto) {
    this(pageDto, sortDto, filterDto, null, false);
  }

  public QueryDto(final QueryDto queryDto, final SortDto sortDto) {
    this(queryDto.page, sortDto, queryDto.filter, queryDto.searchText, queryDto.totalCount);
  }

  public QueryDto(final QueryDto queryDto, final FilterDto filterDto) {
    this(queryDto.page, queryDto.sort, filterDto, queryDto.searchText, queryDto.totalCount);
  }

  public QueryDto(final QueryDto queryDto, final SortDto sortDto, final FilterDto filterDto) {
    this(queryDto.page, sortDto, filterDto, queryDto.searchText, queryDto.totalCount);
  }

  public QueryDto(
    final OffsetPageDto pageDto,
    final SortDto sortDto,
    final FilterDto filterDto,
    final String searchText,
    final boolean totalCount
  ) {
    this.page = pageDto;
    this.sort = sortDto;
    this.filter = filterDto;
    this.searchText = searchText;
    this.totalCount = totalCount;
  }

  public Optional<OffsetPageDto> getPage() {
    return ofNullable(page);
  }

  public Optional<SortDto> getSort() {
    return ofNullable(sort);
  }

  public Optional<FilterDto> getFilter() {
    return ofNullable(filter);
  }

  public boolean hasNoFilter() {
    return isNull(filter) || filter.isEmpty();
  }

  public Optional<String> getSearchText() {
    return ofNullable(searchText);
  }

  public QueryDto setSearchText(final String searchText) {
    this.searchText = searchText;
    return this;
  }

  public boolean getTotalCount() {
    return totalCount;
  }

  public QueryDto setTotalCount(final boolean totalCount) {
    this.totalCount = totalCount;
    return this;
  }
}
