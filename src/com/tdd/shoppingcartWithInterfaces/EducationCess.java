package com.tdd.shoppingcartWithInterfaces;

public class EducationCess implements TaxType {

    @Override
    public double getTaxPercent() {
        return 2;
    }

}
