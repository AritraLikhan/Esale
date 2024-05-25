package com.example.e_sale.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.e_sale.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UploadFragment#} factory method to
 * create an instance of this fragment.
 */
public class UploadFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public UploadFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment UploadFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static UploadFragment newInstance(String param1, String param2) {
//        UploadFragment fragment = new UploadFragment();
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
//        return inflater.inflate(R.layout.fragment_upload, container, false);
//    }


    private EditText editTextDescription, editTextProductName;
    private Button buttonUploadPhoto;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload, container, false);

        editTextDescription = view.findViewById(R.id.editTextDescription);
        editTextProductName = view.findViewById(R.id.editTextProductName);
        buttonUploadPhoto = view.findViewById(R.id.buttonUploadPhoto);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        buttonUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProductPhoto();
            }
        });

        return view;
    }

    private void uploadProductPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String name = editTextProductName.getText().toString().trim();
            String description = editTextDescription.getText().toString().trim();


            if (!description.isEmpty()&&!name.isEmpty()) {
                String userId = mAuth.getCurrentUser().getUid();
                StorageReference productPhotoRef = mStorageRef.child("users").child(userId).child("Products").child(UUID.randomUUID().toString());

                productPhotoRef.putFile(selectedImage)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                productPhotoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        DatabaseReference productRef = mDatabase.child("users").child(userId).child("Products").push();
                                        productRef.child("name").setValue(name);
                                        productRef.child("description").setValue(description);
                                        productRef.child("photoUrl").setValue(uri.toString());

                                      //  Toast.makeText(getActivity(), "Product added successfully", Toast.LENGTH_SHORT).show();

                                        getFragmentManager().beginTransaction()
                                                .replace(R.id.idFragContainer, new ProfileFragment())
                                                .commit();
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle failed upload
                                Log.e("Upload error", e.getMessage());
                            }
                        });
            } else {
                Toast.makeText(getActivity(), "Please fill in the name and description fields", Toast.LENGTH_SHORT).show();
            }
        }
    }
}