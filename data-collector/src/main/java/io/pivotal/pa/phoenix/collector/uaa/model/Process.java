package io.pivotal.pa.phoenix.collector.uaa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Process {

    @JsonProperty("guid")
    private String guid;

    @JsonProperty("instances")
    private Integer instances;
}
