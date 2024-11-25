package vn.softwaredesign.offlineconcurrency.layeredservice.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "products")
@Data
public class Product {
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
}
