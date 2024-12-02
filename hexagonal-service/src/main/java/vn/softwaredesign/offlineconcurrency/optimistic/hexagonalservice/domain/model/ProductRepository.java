package vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.domain.model;

import java.util.Optional;

public interface ProductRepository {
    Optional<BusinessProduct> read(Long id);
    void save(BusinessProduct product);
}
