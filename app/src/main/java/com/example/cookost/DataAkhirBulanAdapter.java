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

public class DataAkhirBulanAdapter extends RecyclerView.Adapter<DataAkhirBulanAdapter.ListViewHolder>{
    private ArrayList<SetGetAkhirBulan> listAkhirBulan;
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_akhir_bulan, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        SetGetAkhirBulan iu = listAkhirBulan.get(position);
        Glide.with(holder.itemView.getContext())
                .load(iu.getFotoAkhirBulan())
                .apply(new RequestOptions().override(500, 500))
                .into(holder.imgAkhirBulan);
        holder.tvNamaAkhirBulan.setText(iu.getNamaAkhirBulan());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listAkhirBulan.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listAkhirBulan.size();
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAkhirBulan;
        TextView tvNamaAkhirBulan;

        ListViewHolder(View itemView) {
            super(itemView);
            imgAkhirBulan = itemView.findViewById(R.id.img_item_akhir_bulan);
            tvNamaAkhirBulan = itemView.findViewById(R.id.tv_item_nama_akhir_bulan);
        }
    }

    public DataAkhirBulanAdapter(ArrayList<SetGetAkhirBulan> list) {
        this.listAkhirBulan = list;
    }
    public interface OnItemClickCallback {
        void onItemClicked(SetGetAkhirBulan data);
    }
}
