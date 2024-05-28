package com.example.e_sale.ui.Show;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_sale.R;
import com.example.e_sale.ui.profile.Product;
import com.example.e_sale.ui.profile.ProfileFragment;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass that displays detailed information about a product.
 * It shows the product image and description, and provides a button to go back to the profile.
 *
 * <p>This fragment can be instantiated with a {@link Product} object to display the product details.</p>
 *
 * <p>Usage:</p>
 * <pre>
 * {@code
 * Product product = new Product();
 * // Set product details
 * ShowFragment fragment = ShowFragment.newInstance(product);
 * // Add fragment to your activity
 * }
 * </pre>
 *
 * <p>Author: Hanium</p>
 */
public class ShowFragment extends Fragment {
    private static final String ARG_PRODUCT = "product";

    private Product product;

    /**
     * Required empty public constructor.
     */
    public ShowFragment() {
        // Required empty public constructor
    }

    /**
     * Creates a new instance of {@link ShowFragment} with the specified product.
     *
     * @param product The {@link Product} object containing product details.
     * @return A new instance of {@link ShowFragment}.
     */
    public static ShowFragment newInstance(Product product) {
        ShowFragment fragment = new ShowFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = (Product) getArguments().getSerializable(ARG_PRODUCT);
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show, container, false);

        TextView textViewDescription = view.findViewById(R.id.textViewDesc);
        ImageView imageViewProduct = view.findViewById(R.id.imageViewP);
        Button goBack = view.findViewById(R.id.buttonBackProfile);

        // Set product description
        textViewDescription.setText(product.getDescription());
        // Use Picasso to load the product photo into the ImageView
        Picasso.get().load(product.getPhotoUrl()).into(imageViewProduct);

        // Set onClick listener for the Go Back button to replace this fragment with ProfileFragment
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFragment profileFragment = new ProfileFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.idFragContainer, profileFragment).commit();
            }
        });

        return view;
    }
}
