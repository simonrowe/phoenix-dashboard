package io.pivotal.pa.phoenix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "aggregated_si")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AggregatedSI {

    public AggregatedSI(Long count) {
        this.siCount = count.intValue();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer siCount;

    @OneToOne
    private Time time;
}
