package io.pivotal.pa.phoenix.collector.capi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data

public class ServiceResponse extends AbstractResponse {
    @JsonProperty("resources")
    private List<Services> services;
}
