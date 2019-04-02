package io.pivotal.pa.phoenix.dataaggregator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
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

    private Integer aiCount;

    @OneToOne
    private Time time;
}
