package com.example.e_sale.ui.reg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.e_sale.R;
import com.example.e_sale.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public RegFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment RegFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static RegFragment newInstance(String param1, String param2) {
//        RegFragment fragment = new RegFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
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
//        return inflater.inflate(R.layout.fragment_reg, container, false);
//    }

    private TextInputEditText editTextUsername, editTextEmail, editTextPassword, editTextConfirmPassword, editTextAddress, editTextPhone;
    private Button buttonSignUp;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    public static RegFragment newInstance(String s)
    {
        RegFragment fragment = new RegFragment();
        Bundle args = new Bundle();
        args.putString("s", s);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reg, container, false);

        editTextUsername = view.findViewById(R.id.editTextUsername);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextConfirmPassword = view.findViewById(R.id.editTextConfirmPassword);
        editTextAddress = view.findViewById(R.id.editTextAddress);
        editTextPhone = view.findViewById(R.id.editTextPhone);
        buttonSignUp = view.findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
//                Navigation.findNavController(v).navigate(R.id.action_signup_to_login);
            }
        });

        return view;
    }

    private void signUp() {

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();


        // Perform validation
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getContext(), "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Here you can perform the sign-up process
        // For demonstration, let's just display a toast message


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Signup successful, add user data to Firebase Realtime Database
                            String userId = mAuth.getCurrentUser().getUid();

                         //   Toast.makeText(getContext(), userId, Toast.LENGTH_SHORT).show();


                            // User user = new User(firstName, lastName, email, password); // Create a User class to hold user data
                            //  mDatabase.child("users").child(userId).setValue(user);

                            User user1 = new User();
                            // User user2 = new User();
                            user1.AccInfo(username, email, phone, address, password);
                            // user2.TextFiles("");
                            mDatabase.child("users").child(userId).child("Account Info").setValue(user1);
                         //   mDatabase.child("users").child(userId).child("Text Files");
                         //   Toast.makeText(SignUpp.this, "Signed up as a SeQR user! Log in to continue", Toast.LENGTH_SHORT).show();
                            // Redirect to your main activity or perform any necessary actions
                            // Example: startActivity(new Intent(SignupActivity.this, MainActivity.class));
                            Toast.makeText(getActivity(), "Registration successful!", Toast.LENGTH_SHORT).show();
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.idFragContainer, new HomeFragment())
                                    .commit();



                        } else {
                            // Signup failed
                            Toast.makeText(getActivity(), "Signup failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

}