package com.tdd.shoppingcartWithInterfaces;

public class Books implements Category {

    @Override
    public double getDiscountPercent() {
        return -0.05;
    }

}
