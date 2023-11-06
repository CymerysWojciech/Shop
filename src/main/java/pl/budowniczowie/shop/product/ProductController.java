package pl.budowniczowie.shop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

     private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<?> getAllProducts() {

        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @PostMapping()
    public ResponseEntity<Products> addProduct(@RequestBody Products product) {
        return ResponseEntity.status(201).body(service.addProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        service.deleteProduct(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable("id") Long id, @RequestBody Products product) {
        return ResponseEntity.ok(service.editProduct(id,product));
    }
}
