package com.example.e_sale.ui.home;

import android.text.Html;
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



    public static FragmentManager fragmentManager;

    private static HomeProductAdapter instance;

    // Private constructor to prevent direct instantiation
    private HomeProductAdapter(List<HomeProduct> products) {
        this.products = products;

    }


    // Static method to get the singleton instance
    public static HomeProductAdapter getInstance(List<HomeProduct> products) {
        if (instance == null) {
            instance = new HomeProductAdapter(products);
        } else {
            instance.updateProducts(products);
        }
        return instance;
    }

    public static HomeProductAdapter getInstance(List<HomeProduct> products, FragmentManager fragmentManager) {
        if (instance == null) {
            instance = new HomeProductAdapter(products, fragmentManager);
        } else {
            instance.updateProducts(products);
        }
        return instance;
    }

    // Method to update the products list
    private void updateProducts(List<HomeProduct> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public HomeProductAdapter(List<com.example.e_sale.ui.home.HomeProduct> products, FragmentManager fragmentManager) {
        this.products = products;
        HomeProductAdapter.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homeitem_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        HomeProduct product = products.get(position);
        String boldText = "<b>" + product.getName() + "</b>";
        holder.textViewDescription.setText(Html.fromHtml(boldText, Html.FROM_HTML_MODE_LEGACY));
        Picasso.get().load(product.getPhotoUrl()).into(holder.imageViewProduct);
        holder.imageViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeShowFragment homeShowFragment = HomeShowFragment.newInstance(product);
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