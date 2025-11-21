package com.store.graph.dto;

public record FilterInput(
        Integer limit,
        Integer offset,
        String sortField
) {
    public int getLimitOrDefault() {
        return limit != null ? limit : 10;
    }

    public int getOffsetOrDefault() {
        return offset != null ? offset : 0;
    }

    public String getSortFieldOrDefault() {
        return sortField != null ? sortField : "name";
    }
}