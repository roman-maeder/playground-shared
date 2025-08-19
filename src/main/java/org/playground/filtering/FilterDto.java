package org.playground.filtering;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;
import static java.util.Objects.*;
import static java.util.Optional.ofNullable;
import static org.playground.filtering.DtoFilterLogic.DEFAULT_FILTER_LOGIC;
import static org.playground.filtering.DtoFilterOperator.DEFAULT_FILTER_OPERATOR;

/**
 * Value object containing filtering information for a resource query.
 * When the filter DTO is a criterion, then the property name, property value and filter operator are set.
 * When the filter DTO is a composite, then the filter logic and the list of filters are set.
 */
public final class FilterDto {

  public static final String FILTER_BY_PROPERTY_NAME = "name";
  public static final String FILTER_VALUE_PROPERTY_NAME = "value";
  public static final String FILTER_OPERATOR_PROPERTY_NAME = "operator";
  public static final String FILTER_LOGIC_PROPERTY_NAME = "logic";
  public static final String FILTERS_PROPERTY_NAME = "filters";

  private String name;
  private String value;
  private String operator;
  private String logic;
  private List<FilterDto> filters = emptyList();

  /**
   * Required by frameworks like Spring with Jackson when deserializing a filter DTO.
   */
  private FilterDto() {}

  /**
   * Creates a filter criterion with the given property name, property value and the default filter operator.
   *
   * @param filterBy The name of the property to filter by.
   * @param filterValue The value of the property to filter by.
   */
  public FilterDto(final String filterBy, final String filterValue) {
    this(filterBy, filterValue, DEFAULT_FILTER_OPERATOR.name());
  }

  /**
   * Creates a filter criterion with the given property name, property value and filter operator.
   *
   * @param filterBy The name of the property to filter by. Must not be {@code null} or blank.
   * @param filterValue The value of the property to filter by.
   * @param filterOperator The filter operator. Must not be {@code null}.
   * @throws IllegalArgumentException If the arguments are invalid.
   */
  public FilterDto(final String filterBy, final String filterValue, final DtoFilterOperator filterOperator) {
    this.name = filterBy;
    this.value = filterValue;
    this.operator = filterOperator.name();
  }

  /**
   * Creates a filter criterion with the given property name, property value and filter operator.
   *
   * @param filterBy The name of the property to filter by. Must not be {@code null} or blank.
   * @param filterValue The value of the property to filter by.
   * @param filterOperatorValue The filter operator. Must not be {@code null} or blank.
   * @throws IllegalArgumentException If the arguments are invalid.
   */
  public FilterDto(final String filterBy, final String filterValue, final String filterOperatorValue) {
    this.name = filterBy;
    this.value = filterValue;
    this.operator = filterOperatorValue;
  }

  /**
   * Creates a filter composite with the given filters and default filter logic.
   *
   * @param filters The filters to be combined. Must not be {@code null}.
   * @throws IllegalArgumentException If the argument is invalid.
   */
  public FilterDto(final FilterDto... filters) {
    this(DEFAULT_FILTER_LOGIC, filters);
  }

  /**
   * Creates a filter composite with the given filters and filter logic.
   *
   * @param filterLogic The filter logic. Must not be {@code null}.
   * @param filters The filters to be combined. Must not be {@code null}.
   * @throws IllegalArgumentException If the arguments are invalid.
   */
  public FilterDto(final DtoFilterLogic filterLogic, final FilterDto... filters) {
    this(filterLogic, List.of(filters));
  }

  /**
   * Creates a filter composite with the given filters and default filter logic.
   *
   * @param filters The filters to be combined. Must not be {@code null}.
   * @throws IllegalArgumentException If the argument is invalid.
   */
  public FilterDto(final List<FilterDto> filters) {
    this(DEFAULT_FILTER_LOGIC, filters);
  }

  /**
   * Creates a filter composite with the given filters and filter logic.
   *
   * @param filterLogic The filter logic. Must not be {@code null}.
   * @param filters The filters to be combined. Must not be {@code null}.
   * @throws IllegalArgumentException If the arguments are invalid.
   */
  public FilterDto(final DtoFilterLogic filterLogic, final List<FilterDto> filters) {
    this.logic = requireNonNull(filterLogic).name();
    this.filters = unmodifiableList(requireNonNull(filters));
  }

  /**
   * Accepts a visitor and delegates to the appropriate visit method based on filter type.
   *
   * @param visitor The visitor to accept
   * @param <T> The return type of the visitor
   * @return The result of the visitor operation
   * @throws IllegalStateException if the filter is neither a criterion nor a composite
   */
  public <T> T accept(FilterDtoVisitor<T> visitor) {
    if (isCriterion()) {
      return visitor.visitCriterion(this);
    } else if (isComposite()) {
      return visitor.visitComposite(this);
    } else {
      throw new IllegalStateException("Filter must be either a criterion or a composite");
    }
  }

  /**
   * Checks whether this filter is a criterion or not.
   *
   * @return {@code true} when this filter is a criterion and {@code false} otherwise.
   */
  public boolean isCriterion() {
    return nonNull(name) && isNull(logic) && filters.isEmpty();
  }

  /**
   * Checks whether this filter is a composite or not.
   *
   * @return {@code true} when this filter is a composite and {@code false} otherwise.
   */
  public boolean isComposite() {
    return isNull(name) && isNull(value) && isNull(operator) && nonNull(logic);
  }

  /**
   * Checks whether this filter is a composite and contains no filters.
   *
   * @return {@code true} when this filter is a composite and contains no filters and {@code false} otherwise.
   */
  public boolean isEmpty() {
    return isComposite() && filters.isEmpty();
  }

  public Optional<String> getName() {
    return ofNullable(name);
  }

  public Optional<String> getValue() {
    return ofNullable(value);
  }

  public Optional<String> getOperator() {
    return ofNullable(operator);
  }

  public Optional<String> getLogic() {
    return ofNullable(logic);
  }

  public List<FilterDto> getFilters() {
    return filters;
  }

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof FilterDto filterDto)) return false;
    return (
      Objects.equals(name, filterDto.name) &&
      Objects.equals(value, filterDto.value) &&
      Objects.equals(operator, filterDto.operator) &&
      Objects.equals(logic, filterDto.logic) &&
      Objects.equals(filters, filterDto.filters)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, value, operator, logic, filters);
  }
}
