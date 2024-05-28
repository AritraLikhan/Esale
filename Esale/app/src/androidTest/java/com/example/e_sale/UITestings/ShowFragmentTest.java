package com.example.e_sale.UITestings;

import android.os.Bundle;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.e_sale.MainActivity;
import com.example.e_sale.R;
import com.example.e_sale.ui.Show.ShowFragment;
import com.example.e_sale.ui.profile.Product;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

/**
 * A test class for the {@link ShowFragment} UI using Espresso framework.
 * This class tests the UI components of the ShowFragment to ensure they are displayed correctly.
 *
 * <p>It sets up a {@link Product} instance and verifies that the product description
 * and image are displayed as expected.</p>
 *
 * <p>It uses the {@link ActivityTestRule} to launch the {@link MainActivity}.</p>
 *
 * <p>Author: Hanium</p>
 */
@RunWith(AndroidJUnit4.class)
public class ShowFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    private Product product;

    /**
     * Sets up the test environment before each test method.
     * It initializes a {@link Product} instance with test data.
     */
    @Before
    public void setUp() {
        product = new Product();
        product.setDescription("This is a test product");
        product.setPhotoUrl("https://example.com/photo.jpg");
    }

    /**
     * Tests the UI components of the {@link ShowFragment}.
     * It verifies that the product description and image are displayed correctly.
     */
    @Test
    public void testShowFragmentUI() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);

        FragmentScenario.launchInContainer(ShowFragment.class, bundle);

        onView(withId(R.id.textViewDesc)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewDesc)).check(matches(withText("This is a test product")));
        onView(withId(R.id.imageViewP)).check(matches(isDisplayed()));
    }
}
