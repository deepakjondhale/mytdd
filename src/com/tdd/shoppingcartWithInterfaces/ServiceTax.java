package com.tdd.shoppingcartWithInterfaces;

public class ServiceTax implements TaxType {
    @Override
    public double getTaxPercent() {
        return 12.5;
    }

}
