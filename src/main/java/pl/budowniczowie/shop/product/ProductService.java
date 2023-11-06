package pl.budowniczowie.shop.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.budowniczowie.shop.product.dto.ProductWithTax;
import pl.budowniczowie.shop.product.dto.ProductWithTaxAndDiscount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductService {
    Logger logger = LoggerFactory.getLogger(ProductService.class);
     private ProductRepository repository;

    @Value("${spring.application.settings.vat}")
    private BigDecimal vat;

    @Value( "${spring.application.settings.discount}")
    private BigDecimal discount;

    @Value( "${spring.application.settings.variant}")
    private String variant;

        @Autowired
        public ProductService(ProductRepository repository) {

            this.repository = repository;
        }


    List<?> getAllProducts() {
            switch (variant) {
                case "PLUS":
                    List<ProductWithTax> productWithTaxList = repository.findAll()
                            .stream()
                            .map(product -> new ProductWithTax(
                                    product.getId(),
                                    product.getName(),
                                    product.getDescription(),
                                    product.getPrice(),
                                    (product.getPrice().multiply(vat).add(product.getPrice())).setScale(2, RoundingMode.HALF_DOWN)
                            ))
                            .collect(Collectors.toList());
                    logger.info("productWithTaxList: {}", productWithTaxList);
                    return productWithTaxList;
                case "PRO":
                    List<ProductWithTaxAndDiscount> productWithTaxAndDiscountList= repository.findAll()
                            .stream()
                            .map(product -> new ProductWithTaxAndDiscount(
                                    product.getId(),
                                    product.getName(),
                                    product.getDescription(),
                                    product.getPrice(),
                                    (product.getPrice().multiply(vat).add(product.getPrice()).setScale(2, RoundingMode.HALF_DOWN)),
                                    (product.getPrice().multiply(vat).add(product.getPrice()).subtract(product.getPrice().multiply(discount)).setScale(2, RoundingMode.HALF_DOWN))
                            ))
                            .collect(Collectors.toList());
                    logger.info("productWithTaxAndDiscountList: {}", productWithTaxAndDiscountList);
                    return productWithTaxAndDiscountList;
                default:
                    List<Products> products = repository.findAll();
                    logger.info("products: {}", products);
                    return products;
            }
    }

    public Optional <?> getProductById(Long id) {

        switch (variant) {
            case "PLUS":
                Optional<ProductWithTax> productWithTax = repository.findById(id)
                        .map(product -> new ProductWithTax(
                                product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getPrice(),
                                (product.getPrice().multiply(vat).add(product.getPrice())).setScale(2, RoundingMode.HALF_DOWN)
                        ));
                logger.info("productWithTax: {}", productWithTax);
                return Optional.ofNullable(productWithTax);
            case "PRO":
                Optional<ProductWithTaxAndDiscount> productWithTaxAndDiscount= repository.findById(id)
                        .map(product -> new ProductWithTaxAndDiscount(
                                product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getPrice(),
                                (product.getPrice().multiply(vat).add(product.getPrice()).setScale(2, RoundingMode.HALF_DOWN)),
                                (product.getPrice().multiply(vat).add(product.getPrice()).subtract(product.getPrice().multiply(discount)).setScale(2, RoundingMode.HALF_DOWN))
                        ));
                logger.info("productWithTaxAndDiscount: {}", productWithTaxAndDiscount);
                return Optional.of(productWithTaxAndDiscount);
            default:
                Optional<Products> product = repository.findById(id);
                logger.info("product: {}", product);
                return product;
        }
    }

    Products addProduct(Products product) {
        return repository.save(product);
    }

    void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    Products editProduct(Long id, Products product) {
        Products productToEdit = repository.findById(id).get();
        productToEdit.setName(product.getName());
        productToEdit.setDescription(product.getDescription());
        productToEdit.setPrice(product.getPrice());
        return repository.save(productToEdit);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addProducts() {
        repository.save(Products.builder()
                .name("Kubek")
                .description("Kubek z logo sklepu")
                .price(new BigDecimal(new Random().nextDouble(250)+50 ))
                .build());
        repository.save(Products.builder()
                .name("Koszulka")
                .description("Koszulka z logo sklepu")
                .price(new BigDecimal(new Random().nextDouble(250)+50 ))
                .build());
        repository.save(Products.builder()
                .name("Bluza")
                .description("Bluza z logo sklepu")
                .price(new BigDecimal(new Random().nextDouble(250)+50 ))
                .build());
        repository.save(Products.builder()
                .name("Spodnie")
                .description("Spodnie z logo sklepu")
                .price(new BigDecimal(new Random().nextDouble(250)+50 ))
                .build());
        repository.save(Products.builder()
                .name("Skarpetki")
                .description("Skarpetki z logo sklepu")
                .price(new BigDecimal(new Random().nextDouble(250)+50 ))
                .build());


    }

}
