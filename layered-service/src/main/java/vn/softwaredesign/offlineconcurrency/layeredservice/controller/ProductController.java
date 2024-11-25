package vn.softwaredesign.offlineconcurrency.layeredservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.softwaredesign.offlineconcurrency.layeredservice.entity.Product;
import vn.softwaredesign.offlineconcurrency.layeredservice.service.ProductServiceV1;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceV1 productServiceV1;

    public ProductController(ProductServiceV1 productServiceV1) {
        this.productServiceV1 = productServiceV1;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productServiceV1.getProductById(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer quantity,
            @RequestParam Integer version) {
        productServiceV1.updateProductV1(id, description, quantity, version);
        return ResponseEntity.noContent().build();
    }
}
