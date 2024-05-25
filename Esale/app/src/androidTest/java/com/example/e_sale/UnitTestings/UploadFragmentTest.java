package com.example.e_sale.UnitTestings;

import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.core.app.ApplicationProvider;

import com.example.e_sale.R;
import com.example.e_sale.ui.login.LoginFragment;
import com.example.e_sale.ui.profile.UploadFragment;

import org.junit.Test;

public class UploadFragmentTest {

    @Test
    public void testFragmentInstantiation() {
        FragmentScenario<UploadFragment> scenario = FragmentScenario.launchInContainer(UploadFragment.class);
        scenario.onFragment(fragment -> {
            assertNotNull(fragment);
        });
    }

    @Test
    public void testUploadButton() {
        FragmentScenario<UploadFragment> scenario = FragmentScenario.launchInContainer(UploadFragment.class);
        scenario.onFragment(fragment -> {
            LayoutInflater inflater = (LayoutInflater) ApplicationProvider.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.fragment_upload, null, false);
            Button UploadButton = view.findViewById(R.id.buttonUploadPhoto);
            assertNotNull(UploadButton);
        });
    }

    // Additional tests for input validation and sign-up logic can be added here

}
