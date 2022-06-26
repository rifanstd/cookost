package com.example.cookost;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class FoodAdapter extends FirestoreRecyclerAdapter<Food,FoodAdapter.FoodHolder> {
    private OnItemClickListener listener;
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
        TextView NamaResep, Bahan, Deskripsi, Langkah;
        ImageView fotoMakanan;


        TextView prioritas;
        public FoodHolder(View itemView){
            super(itemView);
            NamaResep = itemView.findViewById(R.id.tv_item_makanan);
            fotoMakanan = itemView.findViewById(R.id.img_item_makanan);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);
                    }
                }
            });
        }
        private void setFoodIcon(String iconUrl){
            Glide.with(itemView.getContext()).load(iconUrl).apply(new RequestOptions().placeholder(R.drawable.kat_kere_hore)).into(fotoMakanan);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
