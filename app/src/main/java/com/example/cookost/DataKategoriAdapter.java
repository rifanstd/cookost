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

public class DataKategoriAdapter extends RecyclerView.Adapter<DataKategoriAdapter.ListViewHolder>{
    private ArrayList<SetGetKategori> listKategori;
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kategori, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        SetGetKategori iu = listKategori.get(position);
        Glide.with(holder.itemView.getContext())
                .load(iu.getFotoKategori())
                .apply(new RequestOptions().override(500, 500))
                .into(holder.imgKategori);
        holder.tvNamaKategori.setText(iu.getNamaKategori());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listKategori.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKategori.size();
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgKategori;
        TextView tvNamaKategori;

        ListViewHolder(View itemView) {
            super(itemView);
            imgKategori = itemView.findViewById(R.id.img_item_kategori);
            tvNamaKategori = itemView.findViewById(R.id.tv_item_nama_kategori);
        }
    }

    public DataKategoriAdapter(ArrayList<SetGetKategori> list) {
        this.listKategori = list;
    }
    public interface OnItemClickCallback {
        void onItemClicked(SetGetKategori data);
    }
}
