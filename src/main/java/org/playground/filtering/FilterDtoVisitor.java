package org.playground.filtering;

/**
 * Defines a filter visitor interface.
 *
 * @param <RESULT> The return type of the filter visitor.
 */
public interface FilterDtoVisitor<RESULT> {
  RESULT visitComposite(FilterDto filterComposite);
  RESULT visitCriterion(FilterDto filterCriterion);
}
