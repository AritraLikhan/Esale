package com.example.e_sale.ui.home;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sale.R;
import com.example.e_sale.ui.Show.HomeShowFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.ProductViewHolder> {

    private List<HomeProduct> products;
    private FragmentManager fragmentManager;

    public HomeProductAdapter(List<com.example.e_sale.ui.home.HomeProduct> products) {
        this.products = products;
    }

    public HomeProductAdapter(List<com.example.e_sale.ui.home.HomeProduct> products, FragmentManager fragmentManager) {
        this.products = products;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        com.example.e_sale.ui.home.HomeProduct product = products.get(position);
        holder.textViewDescription.setText(product.getName());
        // Use a library like Picasso or Glide to load the product photo into the ImageView
        Picasso.get().load(product.getPhotoUrl()).into(holder.imageViewProduct);
        holder.imageViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeShowFragment homeShowFragment = new HomeShowFragment(product);
                fragmentManager.beginTransaction().replace(R.id.idFragContainer, homeShowFragment).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewDescription;
        public ImageView imageViewProduct;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
        }
    }
}
