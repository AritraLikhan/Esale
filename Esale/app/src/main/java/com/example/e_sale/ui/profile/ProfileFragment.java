package com.example.e_sale.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sale.R;
import com.example.e_sale.ui.home.HomeFragment;
import com.example.e_sale.ui.reg.User;
import com.google.android.material.textfield.TextInputEditText;
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
 * Use the {@link ProfileFragment#} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public ProfileFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ProfileFragment.
//     */
//    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
//        return inflater.inflate(R.layout.fragment_profile, container, false);
//    }


    protected EditText editTextUsername;
    protected EditText editTextEmail;
    private EditText editTextPassword;
    protected EditText editTextAddress;
    protected EditText editTextPhone;
    private Button buttonUpdate, buttonAddProduct;
    private Button buttonLogout;
    private RecyclerView recyclerViewProducts;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize views
        editTextUsername = view.findViewById(R.id.editTextUsername);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextAddress = view.findViewById(R.id.editTextAddress);
        editTextPhone = view.findViewById(R.id.editTextPhone);
        buttonUpdate = view.findViewById(R.id.buttonUpdate);
        buttonAddProduct = view.findViewById(R.id.buttonAddProduct);
        recyclerViewProducts = view.findViewById(R.id.recyclerViewProducts);
        buttonLogout = view.findViewById(R.id.buttonLogOut);

        // Initialize Firebase instances
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Fetch and display user data
        fetchUserData();

        // Set OnClickListener for update button
//        buttonUpdate.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                updateUserData();
//            }
//        });


//        buttonUpdate.setOnClickListener(new android.view.View.OnClickListener() {
//            @Override
//            public void onClick(android.view.View v) {
//                updateUserData();
//            }
//        });


        ButtonActionFactory factory = new ButtonActionFactory();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonAction action = factory.getButtonAction(buttonUpdate);
                if (action != null) {
                    action.onClick(ProfileFragment.this);
                }
            }
        });

        // Fetch and display seller's products
        fetchSellerProducts();

//        // Set OnClickListener for add product button
//        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Navigate to AddProductFragment
//            }
//        });

//        buttonAddProduct.setOnClickListener(new android.view.View.OnClickListener() {
//            @Override
//            public void onClick(android.view.View v) {
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.idFragContainer, new UploadFragment())
//                        .commit();
//            }
//        });


        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonAction action = factory.getButtonAction(buttonAddProduct);
                if (action != null) {
                    action.onClick(ProfileFragment.this);
                }
            }
        });


//        buttonLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                logout();
//            }
//        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonAction action = factory.getButtonAction(buttonLogout);
                if (action != null) {
                    action.onClick(ProfileFragment.this);
                }
            }
        });



        return view;

    }




    protected void fetchUserData() {
        // Fetch user data from Firebase and set EditText fields
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference userRef = mDatabase.child("users").child(userId).child("Account Info");

        userRef.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    editTextUsername.setText(user.getUserName());
                    editTextEmail.setText(user.getEmail());
                    editTextPhone.setText(user.getPhone());
                    editTextAddress.setText(user.getAddress());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    protected void updateUserData() {
        // Get text from EditText fields and update user data in Firebase

        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference userRef = mDatabase.child("users").child(userId).child("Account Info");

        String userName = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        if (!userName.isEmpty()) {
            userRef.child("userName").setValue(userName);
        }
        if (!email.isEmpty()) {
            userRef.child("email").setValue(email);
        }
        if (!phone.isEmpty()) {
            userRef.child("phone").setValue(phone);
        }
        if (!address.isEmpty()) {
            userRef.child("address").setValue(address);
        }


        Toast.makeText(getActivity(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
    }

    public void fetchSellerProducts() {
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference productsRef = mDatabase.child("users").child(userId).child("Products");

        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Product> products = new ArrayList<>();
                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                    Product product = productSnapshot.getValue(Product.class);
                    if (product != null) {
                        product.setId(productSnapshot.getKey()); // Set unique ID
                        product.setOwnerID(userId);
                        products.add(product);
                    }
                }
                FragmentManager fragmentManager = getFragmentManager();
                ProductAdapter productAdapter = new ProductAdapter(products, fragmentManager);
                recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerViewProducts.setAdapter(productAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }



    protected void logout() {
        FirebaseAuth.getInstance().signOut();
        // Navigate to HomeFragment
        getFragmentManager().beginTransaction()
                .replace(R.id.idFragContainer, new HomeFragment())
                .commit();
    }



}

interface ButtonAction {
    void onClick(ProfileFragment fragment);
}

class UpdateAction implements ButtonAction {
    @Override
    public void onClick(ProfileFragment fragment) {
        fragment.updateUserData();
    }
}


class AddProductAction implements ButtonAction {
    @Override
    public void onClick(ProfileFragment fragment) {
        fragment.getFragmentManager().beginTransaction()
                .replace(R.id.idFragContainer, new UploadFragment())
                .commit();
    }
}

class LogoutAction implements ButtonAction {
    @Override
    public void onClick(ProfileFragment fragment) {
        fragment.logout();
    }
}

class ButtonActionFactory {
    public ButtonAction getButtonAction(Button button) {
        if (button.getId() == R.id.buttonUpdate) {
            return new UpdateAction();
        } else if (button.getId() == R.id.buttonAddProduct) {
            return new AddProductAction();
        } else if (button.getId() == R.id.buttonLogOut) {
            return new LogoutAction();
        }
        return null;
    }
}



