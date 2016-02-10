package com.tdd.shoppingcart;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tdd.shoppingcart.ShoppingCart.TAX_TYPE;

public class ShoppingCartTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void itemsCanBeAddedToCart() {
        ShoppingCart cart=new ShoppingCart();
        cart.add(new Product(null, new BigDecimal("0.00")));
        cart.add(new Product(null, new BigDecimal("0.00")));
        assertEquals(2, cart.size());
    }
    
    @Test
    public void canShoppingCartCalculateTheSum() {
        ShoppingCart cart=new ShoppingCart();
        cart.add(new Product(null, new BigDecimal("10")));
        cart.add(new Product(null, new BigDecimal("10")));
        assertEquals(new BigDecimal("20"), cart.total());
    }
    
    @Test(expected= ArithmeticException.class)
    public void shoppingCartDoesNotAcceptProductWithNegativePrice() {
        ShoppingCart cart=new ShoppingCart();
        cart.add(new Product(null, new BigDecimal("-10")));
    }
    
    @Test
    public void shoppingCartCanAcceptProductWithZeroPrice() {
        ShoppingCart cart=new ShoppingCart();
        cart.add(new Product(null, new BigDecimal("0.00")));
        cart.add(new Product(null, new BigDecimal("40.00")));
    } 
    
    @Test
    public void applySalesTaxToShoppingCartTotal() {
        ShoppingCart cart=new ShoppingCart();
        cart.add(new Product(null, new BigDecimal("40.00")));
        cart.add(new Product(null, new BigDecimal("60.0")));
        cart.setSALES_TAX(new BigDecimal("10"));
        assertEquals(new BigDecimal("110.00"), cart.total());
    }
    
    @Test
    public void applyPercentSalesTaxToShoppingCartTotal() {
        ShoppingCart cart=new ShoppingCart();
        cart.add(new Product(null, new BigDecimal("40.00")));
        cart.add(new Product(null, new BigDecimal("60.0")));
        cart.setSALES_TAX(new BigDecimal("5"));
        cart.setTX(TAX_TYPE.PERCENT);
        assertEquals(new BigDecimal("105.00"), cart.total());
    }
    
    @Test
    public void itemsCanBeRemovedFromCart() {
        ShoppingCart cart=new ShoppingCart();
        Product product = new Product("1", new BigDecimal("40.00"));
        cart.add(product);
        cart.add(new Product("2", new BigDecimal("60.0")));
        //cart.remove(product);
        Product productToBeRemvoed = new Product("1", new BigDecimal("40.00"));
        cart.remove(productToBeRemvoed);
        assertEquals(1, cart.size());
    }
    
}
