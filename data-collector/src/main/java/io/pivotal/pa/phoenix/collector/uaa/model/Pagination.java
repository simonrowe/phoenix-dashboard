package io.pivotal.pa.phoenix.collector.uaa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Pagination {

    @JsonProperty("next")
    private String next;
}
