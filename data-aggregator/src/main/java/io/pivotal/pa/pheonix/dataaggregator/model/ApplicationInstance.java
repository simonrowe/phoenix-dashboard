package io.pivotal.pa.pheonix.dataaggregator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(indexes = {
        @Index(name = "idx_appGuidByFoundation", columnList = "appGuidId, foundationId"),
        @Index(name = "idx_foundationId", columnList = "foundationId"),
        @Index(name = "idx_time", columnList = "timeId")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appGuidId;

    private Integer instances;

    private String foundationId;

    @ManyToOne
    @JoinColumn(name = "timeId")
    private Time time;
}
