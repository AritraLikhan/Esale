package com.example.e_sale.UITestings;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.e_sale.R;
import com.example.e_sale.MainActivity;
import com.example.e_sale.ui.Show.HomeShowFragment;
import com.example.e_sale.ui.home.HomeProduct;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

@RunWith(AndroidJUnit4.class)
public class HomeShowFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    private HomeProduct product;
    private MockWebServer mockWebServer;

    @Before
    public void setUp() throws IOException {
        product = new HomeProduct();
        product.setName("Test Product");
        product.setDescription("This is a test product");
        product.setPhotoUrl("https://example.com/photo.jpg");
        product.setOwnerID("testUser");

        FirebaseDatabase.getInstance().getReference("users").child("testUser").child("Account Info").child("userName").setValue("Test User");
        FirebaseDatabase.getInstance().getReference("users").child("testUser").child("Account Info").child("phone").setValue("123456789");
        FirebaseDatabase.getInstance().getReference("users").child("testUser").child("Account Info").child("email").setValue("testuser@example.com");
        FirebaseDatabase.getInstance().getReference("users").child("testUser").child("Account Info").child("address").setValue("123 Test Street");

    }

    @Test
    public void testHomeShowFragmentUI() {
        IdlingRegistry.getInstance().register(HomeShowFragment.idlingResource);
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);

        FragmentScenario.launchInContainer(HomeShowFragment.class, bundle);

        onView(withId(R.id.imageViewHP)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewHName)).check(matches(withText("Test Product")));
        onView(withId(R.id.textViewHDesc)).check(matches(withText("This is a test product")));


//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        onView(withId(R.id.textViewName)).check(matches(withText("Test User")));
        onView(withId(R.id.textViewPhone)).check(matches(withText("123456789")));
        onView(withId(R.id.textViewEmail)).check(matches(withText("testuser@example.com")));
        onView(withId(R.id.textViewAddress)).check(matches(withText("123 Test Street")));
        IdlingRegistry.getInstance().unregister(HomeShowFragment.idlingResource);
    }
}
