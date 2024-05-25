package com.example.e_sale.IntegrationTesting;


import androidx.test.espresso.IdlingResource;

import com.bumptech.glide.request.ResourceCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FirebaseIdlingResource implements IdlingResource {

    private final String resourceName;
    private final DatabaseReference query;
    private ResourceCallback resourceCallback;

    private boolean isIdle = false;

    public FirebaseIdlingResource(String resourceName, DatabaseReference query) {
        this.resourceName = resourceName;
        this.query = query;

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                isIdle = true;
                if (resourceCallback != null) {
                    resourceCallback.onTransitionToIdle();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                isIdle = true;
                if (resourceCallback != null) {
                    resourceCallback.onTransitionToIdle();
                }
            }
        });
    }

    @Override
    public String getName() {
        return resourceName;
    }

    @Override
    public boolean isIdleNow() {
        return isIdle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }
}