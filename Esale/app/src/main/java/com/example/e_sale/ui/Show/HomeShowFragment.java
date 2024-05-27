package com.example.e_sale.ui.Show;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.test.espresso.idling.CountingIdlingResource;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_sale.R;
import com.example.e_sale.ui.home.HomeFragment;
import com.example.e_sale.ui.home.HomeProduct;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Objects;

/**
 * A {@link Fragment} subclass that displays detailed information about a product.
 * It shows the product image, name, description, and the seller's information.
 * The seller's information is fetched from Firebase.
 *
 * <p>This fragment can be instantiated with a {@link HomeProduct} object to display the product details.</p>
 *
 * <p>Usage:</p>
 * <pre>
 * {@code
 * HomeProduct product = new HomeProduct();
 * // Set product details
 * HomeShowFragment fragment = HomeShowFragment.newInstance(product);
 * // Add fragment to your activity
 * }
 * </pre>
 *
 * <p>Author: Hanium</p>
 */
public class HomeShowFragment extends Fragment {

    private static final String ARG_PRODUCT = "product";
    private HomeProduct product;
    public static CountingIdlingResource idlingResource = new CountingIdlingResource("HomeShowFragment");

    /**
     * Required empty public constructor.
     */
    public HomeShowFragment() {
        // Required empty public constructor
    }

    /**
     * Creates a new instance of {@link HomeShowFragment} with the specified product.
     *
     * @param product The {@link HomeProduct} object containing product details.
     * @return A new instance of {@link HomeShowFragment}.
     */
    public static HomeShowFragment newInstance(HomeProduct product) {
        HomeShowFragment fragment = new HomeShowFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = (HomeProduct) getArguments().getSerializable(ARG_PRODUCT);
        }
    }

    /**
     * Inflates the layout for this fragment and initializes the UI components with the product details.
     *
     * @param inflater The {@link LayoutInflater} object that can be used to inflate any views in the fragment.
     * @param container The parent view that the fragment's UI should be attached to, if present.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return The View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_show, container, false);

        ImageView imageViewProduct = view.findViewById(R.id.imageViewHP);
        TextView tvProductName = view.findViewById(R.id.textViewHName);
        TextView tvProductDesc = view.findViewById(R.id.textViewHDesc);
        TextView tvSellerName = view.findViewById(R.id.textViewName);
        TextView tvSellerPhone = view.findViewById(R.id.textViewPhone);
        TextView tvSellerEmail = view.findViewById(R.id.textViewEmail);
        TextView tvSellerAddress = view.findViewById(R.id.textViewAddress);
        Button buttonGoBack = view.findViewById(R.id.buttonGoBack);

        if (product != null) {
            Picasso.get().load(product.getPhotoUrl()).into(imageViewProduct);
            tvProductName.setText(product.getName());
            tvProductDesc.setText(product.getDescription());
            idlingResource.increment();
            FirebaseDatabase.getInstance().getReference("users").child(product.getOwnerID()).child("Account Info").get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d("Data", Objects.requireNonNull(task.getResult().getValue()).toString());
                    tvSellerName.setText(task.getResult().child("userName").getValue() == null ? "" : task.getResult().child("userName").getValue().toString());
                    tvSellerPhone.setText(task.getResult().child("phone").getValue() == null ? "" : task.getResult().child("phone").getValue().toString());
                    tvSellerEmail.setText(task.getResult().child("email").getValue() == null ? "" : task.getResult().child("email").getValue().toString());
                    tvSellerAddress.setText(task.getResult().child("address").getValue() == null ? "" : task.getResult().child("address").getValue().toString());
                    idlingResource.decrement();
                }
            });
        }

        buttonGoBack.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.idFragContainer, new HomeFragment()).commit();
        });

        return view;
    }
}
