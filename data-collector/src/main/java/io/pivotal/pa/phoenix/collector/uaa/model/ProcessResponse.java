package io.pivotal.pa.phoenix.collector.uaa.model;

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
        return getPagination().getNext();
    }


    public boolean hasNextPage() {
        return pagination != null && getNextPageUri() != null;
    }
}
