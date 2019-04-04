package io.pivotal.pa.phoenix.collector.capi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ProcessResponse {

    @JsonProperty("pagination")
    private Pagination pagination;

    @JsonProperty("resources")
    private List<Process> processes;

    public String getNextPageUri() {
        return hasNextPage() ? getPagination().getNext().getHref() : null;
    }
    
    public boolean hasNextPage() {
        return pagination != null && getPagination().getNext() != null;
    }
}
