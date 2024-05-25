package com.example.e_sale.IntegrationTesting;


import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.e_sale.MainActivity;
import com.example.e_sale.R;
import com.example.e_sale.ui.login.LoginFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class LoginFragmentIntegrationTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Before
    public void setUp() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Test
    public void testLoginAndNavigateToProfile() {


        onView(ViewMatchers.withId(R.id.my_drawer)).perform(DrawerActions.open());

        // Navigate to the RegFragment
        onView(withId(R.id.login)).perform(click());

        // Perform login
        onView(withId(R.id.editTextEmail)).perform(typeText("test@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("testpass123"), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());

        // Register Idling Resource
     //   IdlingRegistry.getInstance().register(new FirebaseAuthIdlingResource(mAuth));

        // Check if ProfileFragment is displayed
        onView(withId(R.id.idFragContainer))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));



        // Unregister Idling Resource
       // IdlingRegistry.getInstance().unregister(new FirebaseAuthIdlingResource(mAuth));
    }
}
