package com.example.e_sale.UITestings;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.e_sale.MainActivity;
import com.example.e_sale.R;
import com.example.e_sale.ui.home.HomeProduct;
import com.example.e_sale.ui.home.HomeProductAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class HomeProductAdapterUITest {

    private List<HomeProduct> mockProducts;

    @Before
    public void setUp() {
        // Prepare mock data for testing
        mockProducts = new ArrayList<>();
        mockProducts.add(new HomeProduct("Test Product 1", "Description 1", "http://example.com/photo1.jpg"));
        mockProducts.add(new HomeProduct("Test Product 2", "Description 2", "http://example.com/photo2.jpg"));
    }

    @Test
    public void testRecyclerViewDisplay() {
        // Start MainActivity
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);

        // Synchronize with the activity's lifecycle
        activityScenario.onActivity(activity -> {
            // Create and set the adapter
            HomeProductAdapter adapter = new HomeProductAdapter(mockProducts, activity.getSupportFragmentManager());
            RecyclerView recyclerView = activity.findViewById(R.id.recyclerViewHomeProducts);
            recyclerView.setAdapter(adapter);
        });

        // Verify that the RecyclerView is displayed
        Espresso.onView(withId(R.id.recyclerViewHomeProducts)).check(matches(isDisplayed()));

        // Close the activity
        activityScenario.close();
    }

    @Test
    public void testItemClick() {
        // Start MainActivity
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);

        // Synchronize with the activity's lifecycle
        activityScenario.onActivity(activity -> {
            // Create and set the adapter
            HomeProductAdapter adapter = new HomeProductAdapter(mockProducts, activity.getSupportFragmentManager());
            RecyclerView recyclerView = activity.findViewById(R.id.recyclerViewHomeProducts);
            recyclerView.setAdapter(adapter);
        });

        // Perform click on RecyclerView item
        Espresso.onView(withId(R.id.recyclerViewHomeProducts))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        // Optionally, add assertions to verify the behavior after the click

        // Close the activity
        activityScenario.close();
    }

}

