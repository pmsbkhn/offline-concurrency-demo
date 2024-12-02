package vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.domain.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product implements BusinessProduct, OperationalProduct {
    private Long id;

    private String name;
    private String description;
    private Integer quantity;
    private Integer version;
    private java.time.LocalDateTime updatedAt;

    public void updateInfo(String description, Integer quantity, Integer version) {
        if (version != this.version) {
            throw new IllegalArgumentException("Version does not match.");
        }

        this.description = description;
        this.quantity = quantity;
    }
}
