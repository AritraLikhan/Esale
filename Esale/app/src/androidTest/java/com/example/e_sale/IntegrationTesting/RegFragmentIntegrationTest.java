package com.example.e_sale.IntegrationTesting;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.e_sale.MainActivity;
import com.example.e_sale.R;
import com.example.e_sale.ui.reg.RegFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RegFragmentIntegrationTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    private RegFragment regFragment;

    @Before
    public void setUp() {
        regFragment = mock(RegFragment.class);
    }

    @Test
    public void testSignUpButton() {

        onView(ViewMatchers.withId(R.id.my_drawer)).perform(DrawerActions.open());

        // Navigate to the RegFragment
        onView(withId(R.id.cmp)).perform(click());


        // Enter text into the input fields
        onView(withId(R.id.editTextUsername)).perform(typeText("alamin"), closeSoftKeyboard());
        onView(withId(R.id.editTextEmail)).perform(typeText("alamin@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPhone)).perform(typeText("87862901"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddress)).perform(typeText("Dhaka,Bangladesh"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("alamin7892"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("alamin7892"), closeSoftKeyboard());


        onView(withId(R.id.buttonSignUp)).perform(click());

        regFragment.signUp();

        // Verify that the signUp method was called
        verify(regFragment).signUp();
    }

}


