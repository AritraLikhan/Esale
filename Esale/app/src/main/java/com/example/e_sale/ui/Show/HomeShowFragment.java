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
import com.example.e_sale.ui.home.HomeFragment;
import com.example.e_sale.ui.home.HomeProduct;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeShowFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class HomeShowFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeShowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeShowFragment newInstance(String param1, String param2) {
        HomeShowFragment fragment = new HomeShowFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private HomeProduct product;

    public HomeShowFragment() {
        // Required empty public constructor
    }

    public HomeShowFragment(HomeProduct product) {
        this.product = product;
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
        View view= inflater.inflate(R.layout.fragment_home_show, container, false);

        ImageView imageViewProduct = view.findViewById(R.id.imageViewHP);
        TextView tvProductName = view.findViewById(R.id.textViewHName);
        TextView tvProductDesc = view.findViewById(R.id.textViewHDesc);
        TextView tvSellerName = view.findViewById(R.id.textViewName);
        TextView tvSellerPhone = view.findViewById(R.id.textViewPhone);
        TextView tvSellerEmail = view.findViewById(R.id.textViewEmail);
        TextView tvSellerAddress = view.findViewById(R.id.textViewAddress);
        Button buttonGoBack = view.findViewById(R.id.buttonGoBack);

        Picasso.get().load(product.getPhotoUrl()).into(imageViewProduct);
        tvProductName.setText(product.getName());
        tvProductDesc.setText(product.getDescription());

        FirebaseDatabase.getInstance().getReference("users").child(product.getOwnerID()).child("Account Info").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                tvSellerName.setText(task.getResult().child("username").getValue()==null?"":task.getResult().child("username").getValue().toString());
                tvSellerPhone.setText(task.getResult().child("phone").getValue()==null?"":task.getResult().child("phone").getValue().toString());
                tvSellerEmail.setText(task.getResult().child("email").getValue()==null?"":task.getResult().child("email").getValue().toString());
                tvSellerAddress.setText(task.getResult().child("address").getValue()==null?"":task.getResult().child("address").getValue().toString());
            }
        });

        buttonGoBack.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.idFragContainer, homeFragment).commit();
        });

        return view;
    }
}