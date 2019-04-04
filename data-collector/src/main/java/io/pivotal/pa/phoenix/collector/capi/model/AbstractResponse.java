package io.pivotal.pa.phoenix.collector.capi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public abstract class AbstractResponse {
    @JsonProperty("pagination")
    private Pagination pagination;

    public String getNextPageUri() {
        return hasNextPage() ? getPagination().getNext().getHref() : null;
    }

    public boolean hasNextPage() {
        return pagination != null && getPagination().getNext() != null;
    }
}
