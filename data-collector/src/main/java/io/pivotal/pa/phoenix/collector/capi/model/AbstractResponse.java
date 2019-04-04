package io.pivotal.pa.phoenix.collector.capi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

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

    @JsonIgnore
    public abstract List getResources();
}
