package com.tdd.shoppingcartWithInterfaces;

public class SwatchBharatCess implements TaxType {

    @Override
    public double getTaxPercent() {
        return 0.5;
    }

}
