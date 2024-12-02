package vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.domain.model;

import java.time.LocalDateTime;

public interface OperationalProduct {

    void setId(Long id);
    Long getId();

    void setName(String name);
    String getName();

    void setDescription(String description);
    String getDescription();

    void setQuantity(Integer quantity);
    Integer getQuantity();

    void setVersion(Integer version);
    Integer getVersion();

    void setUpdatedAt(LocalDateTime updatedAt);
    LocalDateTime getUpdatedAt();

}
