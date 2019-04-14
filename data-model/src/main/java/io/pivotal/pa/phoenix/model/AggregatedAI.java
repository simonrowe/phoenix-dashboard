package io.pivotal.pa.phoenix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "aggregated_ai")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AggregatedAI {

    public AggregatedAI(Long count) {
        this.aiCount = count.intValue();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer aiCount;

    @OneToOne
    private Time time;
}
