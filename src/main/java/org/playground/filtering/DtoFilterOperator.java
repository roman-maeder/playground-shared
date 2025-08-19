package org.playground.filtering;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.EnumSet.allOf;

/**
 * Defines the supported filter operators.
 */
public enum DtoFilterOperator {
  EQUAL,
  NOT_EQUAL,
  LESS_THAN,
  LESS_THAN_OR_EQUAL,
  GREATER_THAN,
  GREATER_THAN_OR_EQUAL,
  CONTAINS,
  NOT_CONTAINS,
  STARTS_WITH,
  ENDS_WITH,
  IN;

  public static final DtoFilterOperator DEFAULT_FILTER_OPERATOR = EQUAL;
  public static final Set<DtoFilterOperator> SUPPORTED_FILTER_OPERATORS = unmodifiableSet(
    allOf(DtoFilterOperator.class)
  );

  /**
   * The filter operators which accept {@code null} as a value.
   */
  public static final Set<DtoFilterOperator> NULL_VALUE_OPERATORS = Set.of(EQUAL, NOT_EQUAL);
}
