package org.playground.filtering;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.EnumSet.allOf;

/**
 * Defines the supported filter logic values.
 */
public enum DtoFilterLogic {
  AND,
  OR,
  NOT;

  public static final DtoFilterLogic DEFAULT_FILTER_LOGIC = AND;
  public static final Set<DtoFilterLogic> SUPPORTED_FILTER_LOGIC = unmodifiableSet(allOf(DtoFilterLogic.class));
}
