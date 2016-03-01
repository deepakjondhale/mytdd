package com.tdd.shoppingcartWithInterfaces;

import java.math.BigDecimal;

public class Product {
    private BigDecimal price;
    private String productId;
    private Category category;
    public Product(String productId,BigDecimal bigDecimal, Category category) {
        this.productId=productId;
        this.price=bigDecimal;
        this.category=category;
    }

    public BigDecimal getPrice() {
        BigDecimal priceWithCategoryDiscounts=price;
        if(category!=null) {
            priceWithCategoryDiscounts =price.add(priceWithCategoryDiscounts.multiply(new BigDecimal(category.getDiscountPercent())));
        }
        return priceWithCategoryDiscounts;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        return true;
    }

   
}