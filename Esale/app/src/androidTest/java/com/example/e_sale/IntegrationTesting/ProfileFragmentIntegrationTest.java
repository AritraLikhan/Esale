package com.example.e_sale.IntegrationTesting;



import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.e_sale.MainActivity;
import com.example.e_sale.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class ProfileFragmentIntegrationTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser testUser;

    @Before
    public void setUp() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Account Info");

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
    public void testFetchUserData() {

        onView(ViewMatchers.withId(R.id.my_drawer)).perform(DrawerActions.open());

        // Navigate to the RegFragment
        onView(withId(R.id.pro)).perform(click());

        // Check if the user data is correctly fetched and displayed
        onView(withId(R.id.editTextUsername)).check(matches(withText("testUser")));
        onView(withId(R.id.editTextEmail)).check(matches(withText("test@gmail.com")));
        onView(withId(R.id.editTextPhone)).check(matches(withText("998877")));
        onView(withId(R.id.editTextAddress)).check(matches(withText("testAddress")));
    }

    @Test
    public void testUpdateUserData() {
        // Open the drawer and navigate to the RegFragment
        onView(ViewMatchers.withId(R.id.my_drawer)).perform(DrawerActions.open());
        onView(withId(R.id.pro)).perform(click());

        // Simulate user input and button click
        onView(withId(R.id.editTextUsername)).perform(ViewActions.replaceText("newUser"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.buttonUpdate)).perform(ViewActions.click());

        // Wait for Firebase to update (use IdlingResource for better synchronization)
        IdlingResource idlingResource = new FirebaseIdlingResource("Firebase", mDatabase);
        IdlingRegistry.getInstance().register(idlingResource);

        onView(withId(R.id.editTextUsername)).check(matches(withText("newUser")));

        // Revert changes for future tests
        onView(withId(R.id.editTextUsername)).perform(ViewActions.replaceText("testUser"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.buttonUpdate)).perform(ViewActions.click());

        // Unregister the idling resource
        IdlingRegistry.getInstance().unregister(idlingResource);
    }


    @Test
    public void testLogout() {

        onView(ViewMatchers.withId(R.id.my_drawer)).perform(DrawerActions.open());
        onView(withId(R.id.pro)).perform(click());
        // Simulate logout button click
        onView(withId(R.id.buttonLogOut)).perform(ViewActions.click());

        // Verify if the user is logged out
        FirebaseUser currentUser = mAuth.getCurrentUser();
        assert currentUser == null;
    }
}
