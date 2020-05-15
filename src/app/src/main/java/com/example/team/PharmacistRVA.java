package com.example.team;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PharmacistRVA extends RecyclerView.Adapter<PharmacistRVA.PharmacistViewHolder> implements Filterable{

    public ArrayList<StoreProduct> products;
    private ArrayList<StoreProduct> productsFull;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onAddClick(int position);
        void onPlusClick(int position);
        void onMinusClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class PharmacistViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView, addImage,plus,minus;
        public TextView name, available, price;
        public EditText quantity;


        public PharmacistViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            addImage = itemView.findViewById(R.id.image_add);
            name = itemView.findViewById(R.id.name);
            available = itemView.findViewById(R.id.available);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantity);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);

            addImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onAddClick(position);
                            addImage.setVisibility(v.GONE);
                        }
                    }
                }
            });

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onPlusClick(position);
                        }
                    }
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onMinusClick(position);
                        }
                    }
                }
            });

        }
    }

    public PharmacistRVA(ArrayList<StoreProduct> products) {
        this.products = products;
        productsFull = new ArrayList<>(products);
    }

    @NonNull
    @Override
    public PharmacistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_list, parent, false);
        PharmacistViewHolder pharmacistViewHolder = new PharmacistViewHolder(view, listener);
        return pharmacistViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacistViewHolder holder, int position) {

        StoreProduct currentItem = products.get(position);
        holder.imageView.setImageResource(R.drawable.ic_medicine);
        holder.name.setText("Name: " + currentItem.getProduct());
        holder.available.setText("Available: " + currentItem.getAvailablequantity());
        holder.price.setText("Price: " + currentItem.getPrice()/100 + "$");
        holder.quantity.setText(""+currentItem.getQuantity());

//        holder.name.setText("Name: " + currentItem.getName());
//        holder.available.setText("Available: " + currentItem.getAvailable());
//        holder.price.setText("Price: " + currentItem.getPrice() + "$");
//        holder.quantity.setText(""+currentItem.getQuantity());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public Filter getFilter() {
        return productsFilter;
    }

    private Filter productsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<StoreProduct> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(productsFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(StoreProduct products: productsFull){
                    if(products.getProduct().toLowerCase().contains(filterPattern)){
                        filteredList.add(products);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            products.clear();
            products.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };
}
