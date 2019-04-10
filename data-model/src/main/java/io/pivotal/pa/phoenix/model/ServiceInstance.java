package io.pivotal.pa.phoenix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(indexes = {
        @Index(name = "idx_guid_foundation", columnList = "serviceGuidId, foundationId"),
        @Index(name = "idx_foundationId", columnList = "foundationId"),
        @Index(name = "idx_time", columnList = "timeId")
})
@Data
@NoArgsConstructor
public class ServiceInstance extends AbstractInstance {


    @Column(length = 50)
    @NotNull
    private String serviceGuidId;

    @Column(length = 100)
    @NotNull
    private String serviceName;

    public ServiceInstance(Long id, @NotNull String serviceGuidId, @NotNull String serviceName, @NotNull String foundationId, Time time) {
        super(id, foundationId, time);
        this.serviceGuidId = serviceGuidId;
        this.serviceName = serviceName;
    }
}
