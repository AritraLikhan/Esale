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

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.INTERNET);

    private FirebaseAuth mockFirebaseAuth;
    private FirebaseDatabase mockFirebaseDatabase;

    @Before
    public void setUp() {
        mockFirebaseAuth = Mockito.mock(FirebaseAuth.class);
        mockFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
    }

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

    @Test
    public void testSuccessfulSignUp() {
        FragmentScenario<RegFragment> scenario = FragmentScenario.launchInContainer(RegFragment.class);
        scenario.onFragment(fragment -> {
            fragment.mAuth = mockFirebaseAuth;
            fragment.mDatabase = mockFirebaseDatabase.getReference();

            when(mockFirebaseAuth.createUserWithEmailAndPassword(Mockito.anyString(), Mockito.anyString()))
                    .thenReturn(Mockito.mock(Task.class));
        });

        onView(withId(R.id.editTextUsername))
                .perform(ViewActions.typeText("testUser"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextEmail))
                .perform(ViewActions.typeText("test@example.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextPassword))
                .perform(ViewActions.typeText("password123"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPassword))
                .perform(ViewActions.typeText("password123"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextPhone))
                .perform(ViewActions.typeText("1234567890"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextAddress))
                .perform(ViewActions.typeText("123 Street"), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.buttonSignUp)).perform(ViewActions.click());

        // Check for a successful sign-up message (assuming the toast message)
//        onView(withText("Expected Toast Message"))
//                .inRoot(new ToastMatcher())
 //               .check(matches(withText("Expected Toast Message")));
    }
}
