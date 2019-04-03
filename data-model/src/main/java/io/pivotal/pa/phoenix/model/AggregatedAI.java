package io.pivotal.pa.phoenix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static org.hibernate.annotations.CascadeType.PERSIST;


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
