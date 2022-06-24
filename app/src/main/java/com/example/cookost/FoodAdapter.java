package com.example.cookost;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class FoodAdapter extends FirestoreRecyclerAdapter<Food,FoodAdapter.FoodHolder> {

    public FoodAdapter(@NonNull FirestoreRecyclerOptions<Food> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FoodHolder holder, int position, @NonNull Food model) {
        holder.NamaResep.setText(model.getNamaResep());
        String icon = model.getFoto();
        holder.setFoodIcon(icon);

    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_makanan,parent,false);
        return new FoodHolder(view);
    }


    class FoodHolder extends RecyclerView.ViewHolder{
        TextView NamaResep;
        ImageView fotoMakanan;
        TextView prioritas;
        public FoodHolder(View itemView){
            super(itemView);
            NamaResep = itemView.findViewById(R.id.tv_item_makanan);
            fotoMakanan = itemView.findViewById(R.id.img_item_makanan);
        }
        private void setFoodIcon(String iconUrl){
            Glide.with(itemView.getContext()).load(iconUrl).apply(new RequestOptions().placeholder(R.drawable.kat_kere_hore)).into(fotoMakanan);
        }
    }
}
