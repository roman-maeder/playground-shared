package org.playground;

import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * Creates query resources request DTOs from the HTTP request parameters.
 * <p>
 * Offset Paging:
 *
 * <ul>
 *     <li><em>https://example.com?skip=0&take=20</em></li>
 *     <li><em>https://example.com?skip=0&take=20&totalCount=true</em></li>
 * </ul>
 * <p>
 * Sorting:
 *
 * <ul>
 *     <li><em>https://example.com?skip=0&take=20&sortBy=firstName</em></li>
 *     <li><em>https://example.com?skip=0&take=20&sortBy=firstName,-lastName</em></li>
 * </ul>
 * <p>
 * Searching:
 *
 * <ul>
 *     <li><em>https://example.com?skip=0&take=20&sortBy=firstName,-lastName&search=John</em></li>
 * </ul>
 * <p>
 * Filtering:
 *
 * <ul>
 *     <li><em>https://example.com?skip=0&take=20&sortBy=firstName,-lastName&age=23</em></li>
 *     <li><em>https://example.com?skip=0&take=20&sortBy=firstName,-lastName&age_GREATER_THAN=18&gender_CONTAINS=male</em></li>
 * </ul>
 */
public final class QueryDtoFactory {

    public static final String PAGE_NUMBER_PARAMETER_NAME = "pageNumber";
    public static final String PAGE_SIZE_PARAMETER_NAME = "pageSize";
    public static final String SKIP_PARAMETER_NAME = "skip";
    public static final String TAKE_PARAMETER_NAME = "take";
    public static final String SORT_BY_PARAMETER_NAME = "sortBy";
    public static final String SEARCH_PARAMETER_NAME = "search";
    public static final String TOTAL_COUNT_PARAMETER_NAME = "totalCount";
    public static final List<String> SUPPORTED_REQUEST_PARAMETERS = List.of(
            PAGE_NUMBER_PARAMETER_NAME,
            PAGE_SIZE_PARAMETER_NAME,
            SKIP_PARAMETER_NAME,
            TAKE_PARAMETER_NAME,
            SORT_BY_PARAMETER_NAME,
            SEARCH_PARAMETER_NAME,
            TOTAL_COUNT_PARAMETER_NAME
    );

    public QueryDto create(final Map<String, String> requestParameters) {
        requireNonNull(requestParameters);
        return null;
    }
}
