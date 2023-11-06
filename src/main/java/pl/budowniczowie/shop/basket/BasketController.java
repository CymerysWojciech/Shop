package pl.budowniczowie.shop.basket;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.budowniczowie.shop.basket.dto.ProductsInBasket;
import pl.budowniczowie.shop.product.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/basket")
public class BasketController {

    private final BasketService service;
    private final ProductService productService;

    public BasketController(BasketService service, ProductService productService) {
        this.service = service;
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Basket>> getBaskets() {
        return ResponseEntity.ok(service.getBaskets());
    }

    @GetMapping("{id}")
    public ResponseEntity<Basket> getBasket(@RequestParam Long id) {
        return ResponseEntity.ok(service.getBasket(id));
    }
    @PostMapping("")
    public ResponseEntity<?> createBasket(@RequestBody List<ProductsInBasket> productsList) {

        return ResponseEntity.status(202).body(service.createBasket(productsList));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBasket(@RequestParam Long id) {
        service.deleteBasket(id);
        return ResponseEntity.status(204).build();
    }
    @PutMapping("{id}")
    public ResponseEntity<Basket> updateBasket(@RequestParam Long id, @RequestBody Basket basket) {
        return ResponseEntity.ok(service.updateBasket(id, basket));
    }
}
