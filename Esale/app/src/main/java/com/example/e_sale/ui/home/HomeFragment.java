package com.example.e_sale.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sale.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewProducts;
    private DatabaseReference mDatabase;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewProducts = view.findViewById(R.id.recyclerViewHomeProducts);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getActivity()));

        mDatabase = FirebaseDatabase.getInstance().getReference();

        fetchSellerProducts();

        return view;
    }

    private void fetchSellerProducts() {
        DatabaseReference usersRef = mDatabase.child("users");
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<HomeProduct> products = new ArrayList<>();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    DataSnapshot productsSnapshot = userSnapshot.child("Products");
                    for (DataSnapshot productSnapshot : productsSnapshot.getChildren()) {

                        com.example.e_sale.ui.home.HomeProduct product = productSnapshot.getValue(com.example.e_sale.ui.home.HomeProduct.class);
                        product.setOwnerID(userSnapshot.getKey());

                        HomeProduct product = productSnapshot.getValue(HomeProduct.class);

                        products.add(product);
                    }
                }


                // Set up RecyclerView
                HomeProductAdapter productAdapter = new HomeProductAdapter(products, getFragmentManager());
                recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getActivity()));

                HomeProductAdapter productAdapter = HomeProductAdapter.getInstance(products);

                recyclerViewProducts.setAdapter(productAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        getFragmentManager().beginTransaction()
                .replace(R.id.idFragContainer, new HomeFragment())
                .commit();
    }
}