package vn.softwaredesign.offlineconcurrency.layeredservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.softwaredesign.offlineconcurrency.layeredservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Query("UPDATE products p SET p.description = :#{#product.description}, " +
            "p.quantity = :#{#product.quantity}, " +
            "p.version = :#{#product.version}, " +
            "p.updatedAt = :#{#product.updatedAt} " +
            "WHERE p.id = :#{#product.id} AND p.version = :#{#product.oldVersion}")
    int updateProductWithVersion(@Param("product") Product product);
}
