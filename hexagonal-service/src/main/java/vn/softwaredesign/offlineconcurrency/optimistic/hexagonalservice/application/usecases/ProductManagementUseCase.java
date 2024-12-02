package vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.application.usecases;

import vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.application.ports.inbound.ProductManagement;
import vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.domain.model.BusinessProduct;
import vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.domain.model.InvalidProductException;
import vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.domain.model.Product;
import vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.domain.model.ProductRepository;

import java.util.Optional;

public class ProductManagementUseCase implements ProductManagement {
    private final ProductRepository productRepository;

    public ProductManagementUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void updateProduct(Long id, String description, Integer quantity, Integer version) throws InvalidProductException {
        Optional<BusinessProduct> productOptional = productRepository.read(id);

        if (productOptional.isPresent()) {
            BusinessProduct product = productOptional.get();
            product.updateInfo(description, quantity, version);

            productRepository.save(product);
        } else {
            throw new InvalidProductException("Product id=" + id + " not found.");
        }
    }
}
