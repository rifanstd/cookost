package com.example.cookost;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class UploadFragment extends Fragment {
    Button Upload;
    EditText NamaResep, Deskripsi, Bahan,Langkah;
    ImageView arrowback;
    private SetGetMakanan Makanan = new SetGetMakanan();
    private ArrayList<SetGetMakanan> listAkhirBulan = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listAkhirBulan.addAll(DataMakanan.getListMakanan());
        arrowback = view.findViewById(R.id.backFromUpload);
        Upload = view.findViewById(R.id.btn_upload_resep);
        NamaResep = view.findViewById(R.id.input_nama_resep);
        Deskripsi = view.findViewById(R.id.deskripsi);
        Bahan = view.findViewById(R.id.edt_bahan);
        Langkah = view.findViewById(R.id.edt_langkah);
        String A = NamaResep.getContext().toString();
        String B = Deskripsi.getContext().toString();
        String C = Bahan.getContext().toString();
        String D = Langkah.getContext().toString();

        arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Home.class);
                getActivity().startActivity(intent);
            }
        });
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataMakananAdapter b = new DataMakananAdapter(listAkhirBulan);
                DataMakanan a = new DataMakanan();
                a.AddMakanan(A , B, C, D);
                b.updateReceiptsList(listAkhirBulan);
                Upload.setText("");
                Deskripsi.setText("");
                Bahan.setText("");
                Langkah.setText("");

            }
        });
    }

}