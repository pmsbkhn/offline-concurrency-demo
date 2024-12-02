package vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.adapters.outbound.persistence;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class JpaProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer quantity;

    @Column(nullable = false)
    private Integer version;

    @Transient
    private Integer oldVersion;

    @Column(name = "updated_at", nullable = false)
    private java.time.LocalDateTime updatedAt;

    public void setVersion(Integer version) {
        this.oldVersion = this.version;
        this.version = version;
    }
}
