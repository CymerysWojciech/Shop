package pl.budowniczowie.shop.product.dto;

import java.math.BigDecimal;

public record ProductWithTaxAndDiscount(
        Long id,
        String name,
        String description,
        BigDecimal price,
        BigDecimal priceWithTax,
        BigDecimal priceWithTaxAndDiscount
) {
}
