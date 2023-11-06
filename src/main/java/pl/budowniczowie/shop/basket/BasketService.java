package pl.budowniczowie.shop.basket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.budowniczowie.shop.basket.dto.ProductsInBasket;
import pl.budowniczowie.shop.product.ProductRepository;
import pl.budowniczowie.shop.product.Products;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    @Value("${spring.application.settings.vat}")
    private BigDecimal vat;

    @Value( "${spring.application.settings.discount}")
    private BigDecimal discount;

    @Value( "${spring.application.settings.variant}")
    private String variant;

    public BasketService(BasketRepository basketRepository, ProductRepository productRepository) {
        this.basketRepository = basketRepository;

        this.productRepository = productRepository;
    }

    public List<Basket> getBaskets() {
        return basketRepository.findAll();
    }

    public Basket getBasket(Long id) {
        return basketRepository.findById(id).orElseThrow();
    }

    public Basket createBasket(List<ProductsInBasket> productsListId) {
        List<Products> productsList = new ArrayList<>();
        for (ProductsInBasket productsInBasket : productsListId) {
            productsList.add(productRepository.findById(productsInBasket.id()).orElseThrow());
        }
        Basket basket = Basket.builder()
                .products((List<Products>) productsList)
                .totalPrice(productsList.stream().map(Products::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();
        Basket basketToReturn = basketRepository.save(basket);
        return basketRepository.save(basket);
    }

    public Basket updateBasket(Long id, Basket basket) {
        Basket basketToUpdate = basketRepository.findById(id).orElseThrow();
        basketToUpdate.setProducts(basket.getProducts());
        return basketRepository.save(basketToUpdate);
    }

    public void deleteBasket(Long id) {
        basketRepository.deleteById(id);
    }
}
