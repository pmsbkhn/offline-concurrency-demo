package vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.domain.model;

public interface BusinessProduct {
    void updateInfo(String description, Integer quantity, Integer version);
}
