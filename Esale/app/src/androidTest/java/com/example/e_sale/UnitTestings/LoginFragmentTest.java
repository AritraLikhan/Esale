package com.example.e_sale.UnitTestings;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.core.app.ApplicationProvider;

import com.example.e_sale.R;
import com.example.e_sale.ui.login.LoginFragment;
import com.example.e_sale.ui.reg.RegFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LoginFragmentTest {

    @Test
    public void testFragmentInstantiation() {
        FragmentScenario<LoginFragment> scenario = FragmentScenario.launchInContainer(LoginFragment.class);
        scenario.onFragment(fragment -> {
            assertNotNull(fragment);
        });
    }

    @Test
    public void testLoginButton() {
        FragmentScenario<LoginFragment> scenario = FragmentScenario.launchInContainer(LoginFragment.class);
        scenario.onFragment(fragment -> {
            LayoutInflater inflater = (LayoutInflater) ApplicationProvider.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.fragment_login, null, false);
            Button logInButton = view.findViewById(R.id.buttonLogin);
            assertNotNull(logInButton);
        });
    }

    // Additional tests for input validation and sign-up logic can be added here
}
