package org.playground.sorting;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.EnumSet.allOf;

/**
 * Defines the supported sort order values.
 */
public enum DtoSortOrder {
  ASCENDING,
  DESCENDING;

  public static final DtoSortOrder DEFAULT_SORT_ORDER = ASCENDING;
  public static final Set<DtoSortOrder> SUPPORTED_SORT_ORDERS = unmodifiableSet(allOf(DtoSortOrder.class));
}
