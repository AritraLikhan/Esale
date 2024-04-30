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

    //    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public HomeFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment HomeFragment.
//     */
//    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
//    }


    private RecyclerView recyclerViewProducts;

    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize views

        recyclerViewProducts = view.findViewById(R.id.recyclerViewHomeProducts);

        // Initialize Firebase instances
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Fetch and display user data

        // Set OnClickListener for update button
//        buttonUpdate.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                updateUserData();
//            }
//        });




        // Fetch and display seller's products
        fetchSellerProducts();

//        // Set OnClickListener for add product button
//        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Navigate to AddProductFragment
//            }
//        });



        return view;

    }

    private void fetchSellerProducts() {
        // Fetch seller's products from Firebase and display in RecyclerView

        DatabaseReference usersRef = mDatabase.child("users");
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<com.example.e_sale.ui.home.HomeProduct> products = new ArrayList<>();
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    DataSnapshot productsSnapshot = userSnapshot.child("Products");
                    // Now you can work with the products of each user
                    for (DataSnapshot productSnapshot : productsSnapshot.getChildren()) {
                        com.example.e_sale.ui.home.HomeProduct product = productSnapshot.getValue(com.example.e_sale.ui.home.HomeProduct.class);
                        products.add(product);
                    }
                }

                // Set up RecyclerView
                HomeProductAdapter productAdapter = new HomeProductAdapter(products);
                recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        // Navigate to HomeFragment
        getFragmentManager().beginTransaction()
                .replace(R.id.idFragContainer, new HomeFragment())
                .commit();
    }


}