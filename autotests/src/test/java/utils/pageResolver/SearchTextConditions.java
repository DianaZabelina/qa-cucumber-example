package utils.pageResolver;

public enum SearchTextConditions {
    CONTAINING_CASE_INSENSITIVE,
    CONTAINING_CASE_SENSITIVE,
    EXACT_MATCH_CASE_INSENSITIVE,
    EXACT_MATCH_CASE_SENSITIVE;

    private SearchTextConditions() {
    }
}