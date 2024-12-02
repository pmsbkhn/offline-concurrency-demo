package vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.domain.model;

public class InvalidProductException extends Exception {
    public InvalidProductException(String message) {
        super(message);
    }
}
