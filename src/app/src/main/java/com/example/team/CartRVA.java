package com.example.team;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartRVA extends RecyclerView.Adapter<CartRVA.CartViewHolder> {

    private ArrayList<StoreProduct> products;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder{

        public  ImageView imageView,deleteImage;
        public  TextView name,price,quantity;

        public CartViewHolder(@NonNull View itemView, final OnItemClickListener cartListener) {
            super(itemView);
            imageView= itemView.findViewById(R.id.imageView);
            deleteImage = itemView.findViewById(R.id.image_delete);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantity);

            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cartListener != null){
                        int position = getAdapterPosition();
                        if(position !=RecyclerView.NO_POSITION){
                            cartListener.onDeleteClick(position);
                        }
                    }
                }
            });


            
        }
    }

    public CartRVA(ArrayList<StoreProduct> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list, parent,false);
        CartViewHolder cartViewHolder = new CartViewHolder(view,listener);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        StoreProduct currentItem = products.get(position);

        holder.imageView.setImageResource(R.drawable.ic_password);
        holder.name.setText("Name: "+currentItem.getProduct());
        holder.price.setText("Price: "+currentItem.getPrice()+"$");
        holder.quantity.setText("Quantity: "+currentItem.getQuantity());


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

}
