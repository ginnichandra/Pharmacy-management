package com.example.team;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> implements Filterable {

    private ArrayList<User> users;
    private ArrayList<User> usersFull;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public  ImageView imageView,deleteImage;
        public  TextView id,name,designation;


        public RecyclerViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            imageView= itemView.findViewById(R.id.imageView);
            deleteImage = itemView.findViewById(R.id.image_delete);
            id = itemView.findViewById(R.id.textView1);
            name = itemView.findViewById(R.id.textView2);
            designation = itemView.findViewById(R.id.textView3);
//            users = new ArrayList<>();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public RecyclerViewAdapter(ArrayList<User> users) {
        this.users = users;
        usersFull = new ArrayList<>(users);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list, parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,listener);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        User currentItem = users.get(position);

        holder.imageView.setImageResource(R.drawable.ic_person);
        holder.id.setText("Id: "+currentItem.getId());
        holder.name.setText("Name: "+currentItem.getName());
        holder.designation.setText("Designation: "+currentItem.getRole());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public Filter getFilter() {
        return usersFilter;
    }

    private Filter usersFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<User> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length()== 0){
                filteredList.addAll(usersFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(User user: usersFull){
                    if(user.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(user);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            users.clear();
            users.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}
