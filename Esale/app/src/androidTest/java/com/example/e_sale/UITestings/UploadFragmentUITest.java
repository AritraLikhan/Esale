package com.example.e_sale.UITestings;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.e_sale.MainActivity;
import com.example.e_sale.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class UploadFragmentUITest {

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
    public void testUIComponentsPresence() {

        onView(ViewMatchers.withId(R.id.my_drawer)).perform(DrawerActions.open());

        // Navigate to the RegFragment
        onView(withId(R.id.pro)).perform(click());

        onView(withId(R.id.buttonAddProduct)).perform(click());

        onView(withId(R.id.editTextDescription)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextProductName)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonUploadPhoto)).check(matches(isDisplayed()));

    }

}
