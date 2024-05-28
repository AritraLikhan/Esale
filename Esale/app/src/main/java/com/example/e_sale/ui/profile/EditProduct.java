package com.example.e_sale.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_sale.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

/**
 * A fragment that allows users to edit product details and upload a new product photo.
 * This fragment handles displaying product details, updating the product information,
 * and uploading a new product photo to Firebase Storage.
 *
 * <p>Features:
 * <ul>
 *   <li>Display current product name and description</li>
 *   <li>Allow users to update the product name and description</li>
 *   <li>Allow users to upload a new product photo</li>
 * </ul>
 * </p>
 *
 * <p>Usage example:</p>
 * <pre>
 * {@code
 * Product product = // obtain product object
 * EditProduct editProductFragment = EditProduct.newInstance(product);
 * getSupportFragmentManager().beginTransaction()
 *     .replace(R.id.container, editProductFragment)
 *     .commit();
 * }
 * </pre>
 *
 * <p>Required permissions:</p>
 * <ul>
 *   <li>android.permission.INTERNET</li>
 *   <li>android.permission.READ_EXTERNAL_STORAGE</li>
 * </ul>
 *
 * @see Fragment
 * @see FirebaseStorage
 * @see FirebaseAuth
 * @see DatabaseReference
 *
 * @since 1.0
 * @version 1.0
 *
 * AUTHOR: Hanium
 */
public class EditProduct extends Fragment {

    private static final String ARG_PRODUCT = "product";

    private Product product;

    private EditText editTextDescription, editTextName;
    private Button buttonUploadPhoto, buttonEditProduct, buttonGoBack;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;

    /**
     * Required empty public constructor.
     */
    public EditProduct() {
    }

    /**
     * Factory method to create a new instance of this fragment using the provided parameters.
     *
     * @param product The product to be edited.
     * @return A new instance of fragment EditProduct.
     */
    public static EditProduct newInstance(Product product) {
        EditProduct fragment = new EditProduct();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called to do initial creation of a fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = (Product) getArguments().getSerializable(ARG_PRODUCT);
        }
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to. The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_product, container, false);

        editTextName = view.findViewById(R.id.editProductName);
        editTextDescription = view.findViewById(R.id.editDescription);
        buttonUploadPhoto = view.findViewById(R.id.buttonUploadNewPhoto);
        buttonEditProduct = view.findViewById(R.id.buttonUpdateProduct);
        buttonGoBack = view.findViewById(R.id.buttonGoBackEdit);

        editTextDescription.setText(product.getDescription());

        buttonUploadPhoto.setOnClickListener(v -> uploadProductPhoto());
        buttonEditProduct.setOnClickListener(v -> updateProduct());
        buttonGoBack.setOnClickListener(v -> getFragmentManager().beginTransaction()
                .replace(R.id.idFragContainer, new ProfileFragment())
                .commit());

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).child("Products")
                .child(product.getId()).child("name").get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String name = String.valueOf(task.getResult().getValue());
                        editTextName.setText(name);
                    }
                });
        return view;
    }

    /**
     * Starts an activity to pick an image from external storage.
     */
    private void uploadProductPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 100);
    }

    /**
     * Handles the result of the started activity for selecting an image.
     *
     * @param requestCode The integer request code originally supplied to startActivityForResult(),
     *                    allowing you to identify who this result came from.
     * @param resultCode The integer result code returned by the child activity through its setResult().
     * @param data An Intent, which can return result data to the caller (various data can be attached to Intent "extras").
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String url = product.getPhotoUrl();
            StorageReference productPhotoRef = mStorageRef.child("users")
                    .child(product.getOwnerID()).child("Products").child(product.getPhotoUrl());

            productPhotoRef.delete().addOnSuccessListener(aVoid -> {
                Log.d("TAG", "onSuccess: deleted file");
            }).addOnFailureListener(exception -> {
                Log.d("TAG", "onFailure: did not delete file");
            });

            productPhotoRef.putFile(selectedImage)
                    .addOnSuccessListener(taskSnapshot -> productPhotoRef.getDownloadUrl()
                            .addOnSuccessListener(uri -> {
                                DatabaseReference productRef = mDatabase.child("users")
                                        .child(product.getOwnerID()).child("Products")
                                        .child(product.getId());
                                productRef.child("photoUrl").setValue(uri.toString());
                            }))
                    .addOnFailureListener(e -> Log.e("EditProduct", "Failed to upload photo", e));
        }
    }

    /**
     * Updates the product details in Firebase Realtime Database.
     */
    private void updateProduct() {
        String description = editTextDescription.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        if (!description.isEmpty() && !name.isEmpty()) {
            String userId = mAuth.getCurrentUser().getUid();
            DatabaseReference productRef = mDatabase.child("users").child(userId).child("Products").child(product.getId());
            productRef.child("description").setValue(description);
            productRef.child("name").setValue(name);
            getFragmentManager().beginTransaction()
                    .replace(R.id.idFragContainer, new ProfileFragment())
                    .commit();
        } else {
            Toast.makeText(getActivity(), "Please fill in the fields.", Toast.LENGTH_SHORT).show();
        }
    }
}
