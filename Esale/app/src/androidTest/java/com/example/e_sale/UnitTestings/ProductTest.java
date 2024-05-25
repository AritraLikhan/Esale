package com.example.e_sale.UnitTestings;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.example.e_sale.ui.profile.Product;

public class ProductTest {

    private Product product;

    @Before
    public void setUp() {
        product = new Product("1", "Test description", "http://example.com/photo.jpg");
    }

    @Test
    public void testDefaultConstructor() {
        Product defaultProduct = new Product();
        defaultProduct.setId("2");
        defaultProduct.setDescription("Another description");
        defaultProduct.setPhotoUrl("http://example.com/photo2.jpg");
        defaultProduct.setOwnerID("owner123");

        assertEquals("2", defaultProduct.getId());
        assertEquals("Another description", defaultProduct.getDescription());
        assertEquals("http://example.com/photo2.jpg", defaultProduct.getPhotoUrl());
        assertEquals("owner123", defaultProduct.getOwnerID());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("1", product.getId());
        assertEquals("Test description", product.getDescription());
        assertEquals("http://example.com/photo.jpg", product.getPhotoUrl());
        assertEquals("", product.getOwnerID()); // ownerID should be empty string by default
    }

    @Test
    public void testSetId() {
        product.setId("123");
        assertEquals("123", product.getId());
    }

    @Test
    public void testSetDescription() {
        product.setDescription("New description");
        assertEquals("New description", product.getDescription());
    }

    @Test
    public void testSetPhotoUrl() {
        product.setPhotoUrl("http://example.com/newphoto.jpg");
        assertEquals("http://example.com/newphoto.jpg", product.getPhotoUrl());
    }

    @Test
    public void testSetOwnerID() {
        product.setOwnerID("owner123");
        assertEquals("owner123", product.getOwnerID());
    }
}