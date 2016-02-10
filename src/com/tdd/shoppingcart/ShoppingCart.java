package com.tdd.shoppingcart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<Product> items=new ArrayList<Product>();
    BigDecimal SALES_TAX=new BigDecimal("0");
    Enum<TAX_TYPE> t=TAX_TYPE.FIXED;
    
    public void setTX(Enum<TAX_TYPE> t)
    {
        this.t=t;
    }
    public enum TAX_TYPE{
        //TODO Try to Replace Lambda Here  
        FIXED{
            @Override
            public BigDecimal getTax(BigDecimal number,BigDecimal total) {
                return number;
            }
            
        } ,PERCENT{
            @Override
            public BigDecimal getTax(BigDecimal number,BigDecimal total) {
                return total.multiply(number).divide(new BigDecimal("100"));
            }
        };
        public abstract BigDecimal getTax(BigDecimal number,BigDecimal total);
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
        BigDecimal total=new BigDecimal("0");
        for (Product item:items) {
            total = total.add(item.getPrice());
        }
        //return total()+salesTax();
        return total.add(((TAX_TYPE) t).getTax(SALES_TAX, total));
    }

    /*public BigDecimal totalWithSalesTax() {
        BigDecimal total=total();
        return total().add(SALES_TAX);
    }*/

    /*private BigDecimal calculateSalesTax(BigDecimal salesTaxPercent) {
        return total().multiply(salesTaxPercent).divide(new BigDecimal("100"));
    }

    public BigDecimal totalWithSalesTax(BigDecimal salesTaxPercent) {
        return total().add(calculateSalesTax(salesTaxPercent));
    }
*/
    public void setSALES_TAX(BigDecimal sALES_TAX) {
        SALES_TAX = sALES_TAX;
    }

    public void remove(Product product) {
        items.remove(product);
    }

}
