package com.example.cookost;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
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
    private static int total=0;
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
        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivitiesToHome();
            }
        });
    }
    private void switchActivitiesToHome() {
        Intent i = new Intent(getContext(), Profile.class);
        startActivity(i);
    }

    private void showRecyclerListMakanan(){
        Query query = notebookRef.orderBy("NamaResep",Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Food>options = new FirestoreRecyclerOptions.Builder<Food>()
                .setQuery(query,Food.class)
                .build();

        adapter = new FoodAdapter(options);
        rvAkhirBulan.setHasFixedSize(true);
        rvAkhirBulan.setAdapter(adapter);
        db.collection("Makanan").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int count = 0;
                    for (DocumentSnapshot document : task.getResult()) {
                        count++;
                    }
                    Log.d("TAG", count + "");
                    count = count + 1;
                    total = count;
                } else {
                }
            }
        });
        adapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                total = total * 100;
                adapter.notifyItemRangeRemoved(0,total);
                Food food = documentSnapshot.toObject(Food.class);
                String id = documentSnapshot.getId();
                String namaResep = documentSnapshot.get("NamaResep").toString();
//                Toast.makeText(getContext(), "Posisi " + position + " ID "+ id +" Resep " + namaResep, Toast.LENGTH_SHORT).show();
                showSelectedMakanan(food);
                System.out.println("hasilnya = "+total);
            }
        });
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

    private void showSelectedMakanan(Food data) {
        Intent i = new Intent(getContext(), DeskripsiMakanan.class);
        i.putExtra("foto", data.getFoto());
        i.putExtra("NamaResep",data.getNamaResep());
        i.putExtra("Langkah",data.getLangkah());
        i.putExtra("Deskripsi",data.getDeskripsi());
        i.putExtra("Bahan",data.getBahan());
        startActivity(i);
    }

}