package com.example.e_sale.ui.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sale.R;
import com.example.e_sale.ui.Show.ShowFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> products;
    private FragmentManager fragmentManager;

    public ProductAdapter(List<Product> products, FragmentManager fragmentManager) {
        this.products = products;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.textViewDescription.setText(product.getDescription());
        Picasso.get().load(product.getPhotoUrl()).into(holder.imageViewProduct);

        holder.imageViewProduct.setOnClickListener(v -> {
            ShowFragment showFragment = ShowFragment.newInstance(product);
            fragmentManager.beginTransaction().replace(R.id.idFragContainer, showFragment).commit();
        });

        holder.buttonDelete.setOnClickListener(v -> {
            // Remove product from Firebase
            DatabaseReference productRef = FirebaseDatabase.getInstance()
                    .getReference("users")
                    .child(product.getOwnerID())
                    .child("Products")
                    .child(product.getId()); // Use unique ID to identify the product

            productRef.removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Remove product from local list and notify adapter
                    products.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, products.size());
                } else {
                    // Handle the error, show a message to the user
                }
            });
        });

        holder.buttonEdit.setOnClickListener(v -> {
            EditProduct editProduct = EditProduct.newInstance(product);
            fragmentManager.beginTransaction().replace(R.id.idFragContainer, editProduct).commit();
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewDescription;
        public ImageView imageViewProduct;
        public Button buttonDelete, buttonEdit;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
        }
    }
}
