package com.example.e_sale.ui.home;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HomeProductTest {

    private HomeProduct homeProduct;

    @Before
    public void setUp() {
        // Initialize a HomeProduct instance with sample data
        homeProduct = new HomeProduct("Product Name", "Product Description", "https://example.com/image.jpg");
    }

    @Test
    public void testGetName() {
        // Test the getName() method
        assertEquals("Product Name", homeProduct.getName());
    }

    @Test
    public void testSetName() {
        // Test the setName() method
        homeProduct.setName("New Product Name");
        assertEquals("New Product Name", homeProduct.getName());
    }

    @Test
    public void testGetDescription() {
        // Test the getDescription() method
        assertEquals("Product Description", homeProduct.getDescription());
    }

    @Test
    public void testSetDescription() {
        // Test the setDescription() method
        homeProduct.setDescription("New Product Description");
        assertEquals("New Product Description", homeProduct.getDescription());
    }

    @Test
    public void testGetPhotoUrl() {
        // Test the getPhotoUrl() method
        assertEquals("https://example.com/image.jpg", homeProduct.getPhotoUrl());
    }

    @Test
    public void testSetPhotoUrl() {
        // Test the setPhotoUrl() method
        homeProduct.setPhotoUrl("https://example.com/new_image.jpg");
        assertEquals("https://example.com/new_image.jpg", homeProduct.getPhotoUrl());
    }

    @Test
    public void testGetOwnerID() {
        // Test the getOwnerID() method
        assertEquals("", homeProduct.getOwnerID());
    }

    @Test
    public void testSetOwnerID() {
        // Test the setOwnerID() method
        homeProduct.setOwnerID("123456");
        assertEquals("123456", homeProduct.getOwnerID());
    }
}
