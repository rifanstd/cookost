package com.example.cookost;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    private RecyclerView categoryRecylerView,rvAkhirBulan;
    private ArrayList<SetGetMakanan> listAkhirBulan = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    ImageView profilePhoto;
    private FirebaseFirestore firebaseFirestore;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Makanan");
    private FoodAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecylerView = view.findViewById(R.id.rv_data_kategori);
        categoryRecylerView.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false));
        rvAkhirBulan = view.findViewById(R.id.rv_data_akhir_bulan);
        rvAkhirBulan.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false));
        profilePhoto = view.findViewById(R.id.profile_photo);
        List<CategoryModel> categoryModelList =  new ArrayList<CategoryModel>();
        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecylerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("KATEGORI").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(),documentSnapshot.get("category").toString()));
                    }
                    categoryAdapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        showRecyclerListMakanan();

    }


    private void showRecyclerListMakanan(){
        Query query = notebookRef.orderBy("NamaResep",Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Food>options = new FirestoreRecyclerOptions.Builder<Food>()
                .setQuery(query,Food.class)
                .build();
        adapter = new FoodAdapter(options);
        rvAkhirBulan.setHasFixedSize(true);
        rvAkhirBulan.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void showSelectedMakanan(SetGetMakanan data) {
        Intent i = new Intent(getContext(), DeskripsiMakanan.class);
        i.putExtra("foto_makanan", data.getFotoMakanan());
        i.putExtra("judul",data.getNamaMakanan());
        i.putExtra("foto_pengupload",data.getFotoProfil());
        i.putExtra("nama_pengupload",data.getPenguploadMakanan());
        i.putExtra("lokasi_pengupload",data.getLokasiPenguploadMakanan());
        i.putExtra("deskripsi",data.getDeskripsiMakanan());
        i.putExtra("bahan",data.getBahanMakanan());
        i.putExtra("langkah", data.getLangkahMakanan());
        startActivity(i);
    }

}