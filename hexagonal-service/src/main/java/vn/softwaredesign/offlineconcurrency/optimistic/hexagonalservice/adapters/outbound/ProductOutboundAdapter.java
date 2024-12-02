package vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.adapters.outbound;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.adapters.outbound.persistence.JpaProduct;
import vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.adapters.outbound.persistence.JpaProductRepository;
import vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.domain.model.BusinessProduct;
import vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.domain.model.OperationalProduct;
import vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.domain.model.Product;
import vn.softwaredesign.offlineconcurrency.optimistic.hexagonalservice.domain.model.ProductRepository;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class ProductOutboundAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public Optional<BusinessProduct> read(Long id) {
        JpaProduct jpaProduct = jpaProductRepository.findById(id).orElse(null);
        if (jpaProduct == null) {
            return Optional.empty();
        }

        BusinessProduct businessProduct = convertToDomainModel(jpaProduct);
        
        return Optional.ofNullable(businessProduct);
    }

    @Override
    public void save(BusinessProduct product) {
        OperationalProduct operationalProduct = (OperationalProduct) product;
        JpaProduct jpaProduct = convertToDatabaseModel(operationalProduct);
        jpaProduct.setVersion(jpaProduct.getVersion() + 1);

        int updatedRows = jpaProductRepository.updateProductWithVersion(jpaProduct);
        if (updatedRows == 0) {
            throw new RuntimeException("Conflict detected! The product has been modified by another user.");
        }
    }

    private JpaProduct convertToDatabaseModel(OperationalProduct operationalProduct) {
        JpaProduct jpaProduct = new JpaProduct();
        jpaProduct.setId(operationalProduct.getId());
        jpaProduct.setName(operationalProduct.getName());
        jpaProduct.setDescription(operationalProduct.getDescription());
        jpaProduct.setVersion(operationalProduct.getVersion());
        jpaProduct.setUpdatedAt(operationalProduct.getUpdatedAt());

        return jpaProduct;
    }

    private BusinessProduct convertToDomainModel(JpaProduct jpaProduct) {
        OperationalProduct operationalProduct = new Product();
        operationalProduct.setId(jpaProduct.getId());
        operationalProduct.setName(jpaProduct.getName());
        operationalProduct.setDescription(jpaProduct.getDescription());
        operationalProduct.setVersion(jpaProduct.getVersion());
        operationalProduct.setUpdatedAt(jpaProduct.getUpdatedAt());

        return (BusinessProduct) operationalProduct;
    }
}
