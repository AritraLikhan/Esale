package com.example.e_sale.UnitTestings;


import androidx.annotation.NonNull;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class LoginFragmentTest {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser testUser;

    @Before
    public void setUp() {
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Account Info");

        // Create or sign in to a test user account
        mAuth.signInWithEmailAndPassword("likhan@gmail.com", "gublikhan")
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        testUser = mAuth.getCurrentUser();
                        assertNotNull("User should be signed in", testUser);
                    } else {
                        fail("Failed to sign in test user");
                    }
                });

        // Wait for the authentication to complete
        try {
            Thread.sleep(3000); // Adjust sleep time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadWriteData() {
        // Write data to Firebase
        databaseReference.setValue("Hello, Firebase!")
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        fail("Failed to write data to Firebase");
                    }
                });

        // Read data from Firebase and verify
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                assertEquals("Hello, Firebase!", value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                fail("Failed to read data from Firebase: " + databaseError.getMessage());
            }
        });

        // Wait for the read operation to complete
        try {
            Thread.sleep(3000); // Adjust sleep time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        // Clean up test data after each test
        databaseReference.removeValue();

        // Sign out the test user
        if (testUser != null) {
            mAuth.signOut();
            testUser = null;
        }
    }
}
