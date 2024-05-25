package com.example.e_sale.UITestings;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.when;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.GrantPermissionRule;

import com.example.e_sale.R;
import com.example.e_sale.ui.reg.RegFragment;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(AndroidJUnit4.class)
public class RegFragmentUITest {


    @Test
    public void testUIComponentsPresence() {
        FragmentScenario<RegFragment> scenario = FragmentScenario.launchInContainer(RegFragment.class);
        onView(withId(R.id.editTextUsername)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextEmail)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextConfirmPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextPhone)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextAddress)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonSignUp)).check(matches(isDisplayed()));
    }

}
