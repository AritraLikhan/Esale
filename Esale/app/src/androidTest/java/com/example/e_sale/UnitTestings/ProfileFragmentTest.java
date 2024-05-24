package com.example.e_sale.UnitTestings;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.core.app.ApplicationProvider;

import com.example.e_sale.R;
import com.example.e_sale.ui.profile.ProfileFragment;
import com.example.e_sale.ui.reg.RegFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProfileFragmentTest {


    FirebaseAuth mAuth;
    FirebaseDatabase databaseReference;
    FirebaseUser testUser;


    @Before
    public void setUp() {
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Account Info").getDatabase();

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
    public void testFragmentInstantiation() {
        FragmentScenario<ProfileFragment> scenario = FragmentScenario.launchInContainer(ProfileFragment.class);
        scenario.onFragment(fragment -> {
            assertNotNull(fragment);
        });
    }

    @Test
    public void testUpdateButton() {
        FragmentScenario<ProfileFragment> scenario = FragmentScenario.launchInContainer(ProfileFragment.class);
        scenario.onFragment(fragment -> {
            LayoutInflater inflater = (LayoutInflater) ApplicationProvider.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.fragment_profile, null, false);
            Button updateButton = view.findViewById(R.id.buttonUpdate);
            assertNotNull(updateButton);
        });
    }

    @Test
    public void testAddProductButton() {
        FragmentScenario<ProfileFragment> scenario = FragmentScenario.launchInContainer(ProfileFragment.class);
        scenario.onFragment(fragment -> {
            LayoutInflater inflater = (LayoutInflater) ApplicationProvider.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.fragment_profile, null, false);
            Button addProductButton = view.findViewById(R.id.buttonAddProduct);
            assertNotNull(addProductButton);
        });
    }

    @Test
    public void testLogoutButton() {
        FragmentScenario<ProfileFragment> scenario = FragmentScenario.launchInContainer(ProfileFragment.class);
        scenario.onFragment(fragment -> {
            LayoutInflater inflater = (LayoutInflater) ApplicationProvider.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.fragment_profile, null, false);
            Button logoutButton = view.findViewById(R.id.buttonLogOut);
            assertNotNull(logoutButton);
        });
    }


}
