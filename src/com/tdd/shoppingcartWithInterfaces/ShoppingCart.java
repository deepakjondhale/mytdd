package com.tdd.shoppingcartWithInterfaces;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//Implementing Interface For Tax Type 

public class ShoppingCart {
    List<Product> items=new ArrayList<Product>();
//    BigDecimal SALES_TAX=new BigDecimal("0");
    List<TaxType> taxes;

    public ShoppingCart() {
    }
    public ShoppingCart(List<TaxType> taxes) {
        super();
        this.taxes = taxes;
    }

    public void add(Product product) {
        BigDecimal zeroBD=new BigDecimal("0.00");
        if(product.getPrice().compareTo(zeroBD)==-1) {
            throw new ArithmeticException("Its -Ve Price");
        }
        items.add(product);
    }

    public int size() {
        return items.size();
    }

    public BigDecimal total() {
        final BigDecimal total= items.stream().map(Product::getPrice).reduce(new BigDecimal("0"),(a,e)->a.add(e));
        BigDecimal totalWithTaxes = calculateTax(total);
        return totalWithTaxes;
    }
    private BigDecimal calculateTax(final BigDecimal total) {
        Double taxesTotal = calculateTaxFor(total);
        BigDecimal totalWithTaxes=null;
        if(taxesTotal!=null){
           totalWithTaxes= new BigDecimal(total.doubleValue()+taxesTotal);
        }else{
            totalWithTaxes= new BigDecimal(total.doubleValue());
        }
        return totalWithTaxes;
    }

    private Double calculateTaxFor(final BigDecimal total) {
        Double taxesTotal=null;
        if(null!=taxes){
            taxesTotal = taxes.stream().map(x -> x.getTaxPercent()* total.doubleValue()/100).reduce(0d,(a,elem)-> a+elem);
        }
        return taxesTotal;
    }

    public void remove(Product product) {
        items.remove(product);
    }
}
