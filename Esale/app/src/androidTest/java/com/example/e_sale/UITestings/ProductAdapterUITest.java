package com.example.e_sale.UITestings;


import androidx.fragment.app.testing.FragmentScenario;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.e_sale.MainActivity;
import com.example.e_sale.R;
import com.example.e_sale.ui.Show.ShowFragment;
import com.example.e_sale.ui.profile.Product;
import com.example.e_sale.ui.profile.ProductAdapter;
import com.example.e_sale.ui.profile.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import android.os.Bundle;
import android.view.View;

@RunWith(AndroidJUnit4.class)
public class ProductAdapterUITest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    FirebaseAuth mAuth;
    FirebaseDatabase databaseReference;
    FirebaseUser testUser;


    @Before
    public void setUp() {
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Account Info").getDatabase();

        // Create or sign in to a test user account
        mAuth.signInWithEmailAndPassword("test@gmail.com", "testpass123")
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
    public void testRecyclerViewIsDisplayed() {

       FragmentScenario<ProfileFragment> scenario = FragmentScenario.launchInContainer(ProfileFragment.class, new Bundle());

        scenario.onFragment(fragment -> {
            // Check if RecyclerView is displayed
            View view = fragment.getView();
            RecyclerView recyclerView = view.findViewById(R.id.recyclerViewProducts);
            assertEquals(View.VISIBLE, recyclerView.getVisibility());
        });
    }

}

