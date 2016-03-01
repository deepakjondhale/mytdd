package com.tdd.shoppingcartWithInterfaces;

public class Sports implements Category {

    @Override
    public double getDiscountPercent() {
        return 0.05;
    }

}
