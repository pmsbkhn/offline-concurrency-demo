package vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.application.ports.inbound;

import vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.domain.model.InvalidProductException;

public interface ProductManagement {
    void updateProduct(Long id, String description, Integer quantity, Integer version)
            throws InvalidProductException;
}
