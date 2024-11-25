package vn.softwaredesign.offlineconcurrency.layeredservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.softwaredesign.offlineconcurrency.layeredservice.entity.Product;
import vn.softwaredesign.offlineconcurrency.layeredservice.repository.ProductRepository;


@Service
@RequiredArgsConstructor
public class ProductServiceV1 {
    private final ProductRepository productRepository;

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
    }

    @Transactional
    public void updateProductV1(Long id, String description, Integer quantity, Integer version) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        if (!product.getVersion().equals(version)) {
            throw new RuntimeException("Conflict detected! The product has been modified by another user.");
        }

        if (description != null) {
            product.setDescription(description);
        }
        if (quantity != null) {
            product.setQuantity(quantity);
        }

        product.setVersion(product.getVersion() + 1);
        product.setUpdatedAt(java.time.LocalDateTime.now());

        productRepository.save(product);
    }

    @Transactional
    public void updateProductV2(Long id, String description, Integer quantity, Integer version) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        if (!product.getVersion().equals(version)) {
            throw new RuntimeException("Conflict detected! The product has been modified by another user.");
        }

        if (description != null) {
            product.setDescription(description);
        }
        if (quantity != null) {
            product.setQuantity(quantity);
        }

        product.setOldVersion(product.getVersion());
        product.setVersion(product.getVersion() + 1);
        product.setUpdatedAt(java.time.LocalDateTime.now());

        int updatedRows = productRepository.updateProductWithVersion(product);

        if (updatedRows == 0) {
            throw new RuntimeException("Conflict detected! The product has been modified by another user.");
        }
    }


}
