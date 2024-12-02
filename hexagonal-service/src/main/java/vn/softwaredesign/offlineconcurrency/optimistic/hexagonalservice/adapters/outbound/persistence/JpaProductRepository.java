package vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaProductRepository extends JpaRepository<JpaProduct, Long> {
    @Modifying
    @Query("UPDATE JpaProduct p SET p.description = :#{#product.description}, " +
            "p.quantity = :#{#product.quantity}, " +
            "p.version = :#{#product.version}, " +
            "p.updatedAt = :#{#product.updatedAt} " +
            "WHERE p.id = :#{#product.id} AND p.version = :#{#product.oldVersion}")
    int updateProductWithVersion(@Param("product") JpaProduct product);
}
