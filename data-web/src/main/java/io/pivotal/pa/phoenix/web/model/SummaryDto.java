package io.pivotal.pa.phoenix.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryDto {

    private Integer currentCount;

    @JsonFormat(pattern="dd-MMM-yyyy HH:mm")
    private Date lastReadDate;

    private Integer maxCount;
}
