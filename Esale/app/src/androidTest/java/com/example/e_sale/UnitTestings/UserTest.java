package com.example.e_sale.UnitTestings;

import static org.junit.Assert.assertEquals;

import com.example.e_sale.ui.reg.User;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void testAccInfo() {
        user.AccInfo("testUser", "test@example.com", "1234567890", "123 Street", "password123");

        assertEquals("testUser", user.getUserName());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("1234567890", user.getPhone());
        assertEquals("123 Street", user.getAddress());
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testSetGetUserName() {
        user.setUserName("testUser");
        assertEquals("testUser", user.getUserName());
    }

    @Test
    public void testSetGetEmail() {
        user.setEmail("test@example.com");
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    public void testSetGetPhone() {
        user.setPhone("1234567890");
        assertEquals("1234567890", user.getPhone());
    }

    @Test
    public void testSetGetAddress() {
        user.setAddress("123 Street");
        assertEquals("123 Street", user.getAddress());
    }

    @Test
    public void testSetGetPassword() {
        user.setPassword("password123");
        assertEquals("password123", user.getPassword());
    }
}
