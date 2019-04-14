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
public class ApplicationInstance extends AbstractInstance{

    @NotNull
    private Integer instances;
    @Column(length = 50)
    @NotNull
    private String appGuidId;

    public ApplicationInstance(Long id, @NotNull String appGuidId, @NotNull Integer instances, @NotNull String foundationId, Time time) {
        super(id, foundationId, time);
        this.instances = instances;
        this.appGuidId = appGuidId;
    }
}
