package com.tdd.shoppingcartWithInterfaces;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ShoppingCartTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        taxes=new LinkedList<>();
        ServiceTax serviceTax=new ServiceTax();
        taxes.add(serviceTax);
        products= new HashMap<String, Product>();
        sportsCategory = new Sports();
        bookCategory = new Books();
        //load productCatagoryMap
        products.put("java 8", new Product("java 8", new BigDecimal("40"), bookCategory));
        products.put("shoes", new Product("Shoes", new BigDecimal("40"), sportsCategory));
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    static Category  sportsCategory;
    static  Category bookCategory;
    private static List<TaxType> taxes;
    private static Map<String,Product> products;
    @Before
    public void setUp() throws Exception {
      
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void itemsCanBeAddedToCart() {
        ShoppingCart cart=new ShoppingCart();
        cart.add(new Product(null, new BigDecimal("0.00"), null));
        cart.add(new Product(null, new BigDecimal("0.00"), null));
        assertEquals(2, cart.size());
    }
    
    @Test
    public void canShoppingCartCalculateTheSum() {
        ShoppingCart cart=new ShoppingCart();
        cart.add(new Product(null, new BigDecimal("10"), null));
        cart.add(new Product(null, new BigDecimal("10"), null));
        assertEquals(new BigDecimal("20"), cart.total());
    }
    
    @Test(expected= ArithmeticException.class)
    public void shoppingCartDoesNotAcceptProductWithNegativePrice() {
        ShoppingCart cart=new ShoppingCart();
        cart.add(new Product(null, new BigDecimal("-10"), null));
    }
    
    @Test
    public void shoppingCartCanAcceptProductWithZeroPrice() {
        ShoppingCart cart=new ShoppingCart();
        cart.add(new Product(null, new BigDecimal("0.00"), null));
        cart.add(new Product(null, new BigDecimal("40.00"), null));
    } 
    
    @Test
    public void applySalesTaxToShoppingCartTotal() {
        ShoppingCart cart=new ShoppingCart(taxes);
        cart.add(new Product(null, new BigDecimal("40.00"), null));
        cart.add(new Product(null, new BigDecimal("60.0"), null));
        //cart.setSALES_TAX(new BigDecimal("10"));
//        cart.setTaxes();
        assertEquals(new BigDecimal("112.5"), cart.total());
    }
    
    @Test
    public void applyPercentSalesTaxToShoppingCartTotal() {
        taxes.remove(0);
        EducationCess eduCess=new EducationCess();
        taxes.add(eduCess);
        SwatchBharatCess swatchBharatCess=new SwatchBharatCess();
        taxes.add(swatchBharatCess);
        ShoppingCart cart=new ShoppingCart(taxes);
        //Category book=getCategoryFactory("Java 8");
        cart.add(fetchBookProduct("java 8"));
        cart.add(fetchShoesProduct("shoes"));
        assertEquals(new BigDecimal("103.525").doubleValue(), cart.total().doubleValue(),0.01);
    }

    private Product fetchShoesProduct(String shoes) {
        return new Product(shoes, new BigDecimal("60.0"), sportsCategory);
    }

    private Product fetchBookProduct(String book) {
        return products.get(book);
    }
    
    @Test
    public void itemsCanBeRemovedFromCart() {
        ShoppingCart cart=new ShoppingCart();
        Product product = new Product("1", new BigDecimal("40.00"), null);
        cart.add(product);
        cart.add(new Product("2", new BigDecimal("60.0"), null));
        //cart.remove(product);
        Product productToBeRemvoed = new Product("1", new BigDecimal("40.00"), null);
        cart.remove(productToBeRemvoed);
        assertEquals(1, cart.size());
    }
    
}
