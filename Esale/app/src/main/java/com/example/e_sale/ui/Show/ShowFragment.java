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
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Product product;

    public ShowFragment() {
        // Required empty public constructor
    }

    public ShowFragment(Product product) {
        this.product = product;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowFragment newInstance(String param1, String param2) {
        ShowFragment fragment = new ShowFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_show, container, false);

        TextView textViewDescription=view.findViewById(R.id.textViewDesc);
        ImageView imageViewProduct=view.findViewById(R.id.imageViewP);
        Button goBack = view.findViewById(R.id.buttonBackProfile);

        textViewDescription.setText(product.getDescription());
        // Use a library like Picasso or Glide to load the product photo into the ImageView
        Picasso.get().load(product.getPhotoUrl()).into(imageViewProduct);

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