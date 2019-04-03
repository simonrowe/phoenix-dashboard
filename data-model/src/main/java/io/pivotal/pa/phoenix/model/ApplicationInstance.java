package io.pivotal.pa.phoenix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(indexes = {
        @Index(name = "idx_guid_foundation", columnList = "appGuidId, foundationId"),
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

    @Column(length = 50)
    @NotNull
    private String appGuidId;

    @NotNull
    private Integer instances;

    @Column(length = 50)
    @NotNull
    private String foundationId;

    @ManyToOne
    @JoinColumn(name = "timeId")
    private Time time;
}
