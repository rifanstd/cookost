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

import java.util.ArrayList;
import java.util.List;

public class DataMakananAdapter extends RecyclerView.Adapter<DataMakananAdapter.ListViewHolder>{
    private ArrayList<SetGetMakanan> listMakanan;

    public DataMakananAdapter() {

    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_makanan, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        SetGetMakanan iu = listMakanan.get(position);
        Glide.with(holder.itemView.getContext())
                .load(iu.getFotoMakanan())
                .apply(new RequestOptions().override(500, 500))
                .into(holder.imgMakanan);
        holder.tvNamaMakanan.setText(iu.getNamaMakanan());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listMakanan.get(holder.getAdapterPosition()));
            }
        });
    }
    @Override
    public int getItemCount() {
        return listMakanan.size();
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMakanan;
        TextView tvNamaMakanan;

        ListViewHolder(View itemView) {
            super(itemView);
            imgMakanan = itemView.findViewById(R.id.img_item_makanan);
            tvNamaMakanan = itemView.findViewById(R.id.tv_item_makanan);
        }
    }

    public DataMakananAdapter(ArrayList<SetGetMakanan> list) {
        this.listMakanan = list;
    }

    public interface OnItemClickCallback {
        void onItemClicked(SetGetMakanan data);
    }
}
