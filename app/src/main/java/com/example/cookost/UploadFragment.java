package com.example.cookost;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UploadFragment extends Fragment {
    Button Upload, PilihFoto;
    EditText NamaResep, Deskripsi, Bahan,Langkah;
    ImageView arrowback,upFoto;
    FirebaseFirestore db;
    private static final int CAMERA_REQUEST_CODE = 200;
    private static final int STORAGE_REQUEST_CODE = 300;
    private static final int IMAGE_PICK_GALLERY_CODE = 400;
    private static final int IMAGE_PICK_CAMERA_CODE = 500;
    private String [] cameraPermission;
    private String [] storagePermission;
    private Uri image_uri;
    private SetGetMakanan Makanan = new SetGetMakanan();
    private ArrayList<SetGetMakanan> listAkhirBulan = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
        upFoto = view.findViewById(R.id.img_up);
        Upload = view.findViewById(R.id.btn_upload_resep);
        NamaResep = view.findViewById(R.id.input_nama_resep);
        Deskripsi = view.findViewById(R.id.deskripsi);
        Bahan = view.findViewById(R.id.edt_bahan);
        Langkah = view.findViewById(R.id.edt_langkah);
        PilihFoto = view.findViewById(R.id.btn_pilih);
        db = FirebaseFirestore.getInstance();
//        String A = NamaResep.getContext().toString();
//        String B = Deskripsi.getContext().toString();
//        String C = Bahan.getContext().toString();
//        String D = Langkah.getContext().toString();
        cameraPermission = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};


        upFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        PilihFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
//                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
//                        requestPermissions(getActivity(),String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                                1001);
                    }
                    else{
                        pickFromGallery();
                    }
                }
                else {

                }
            }
        });
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
//                DataMakananAdapter b = new DataMakananAdapter(listAkhirBulan);
//                DataMakanan a = new DataMakanan();
//                a.AddMakanan(A , B, C, D);
//                b.updateReceiptsList(listAkhirBulan);
//                Upload.setText("");
//                Deskripsi.setText("");
//                Bahan.setText("");
//                Langkah.setText("");
                String namaResep = NamaResep.getText().toString();
                String deskripsi = Deskripsi.getText().toString();
                String bahan = Bahan.getText().toString();
                String langkah = Langkah.getText().toString();
                Map<String,Object> user = new HashMap<>();
                user.put("NamaResep",namaResep);
                user.put("Deskripsi",deskripsi);
                user.put("Bahan",bahan);
                user.put("Langkah",langkah);
                db.collection("Makanan").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity(), "Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Tidak Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void pickFromGallery() {
        Intent intent =  new Intent (Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_GALLERY_CODE);

    }
//    private void showImagePick() {
//        String [] options = {"Camera","Gallery"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Pick Image").setItems(options, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int i) {
//                if(i==0){
//                    if(checkCameraPermission()){
//                        pickFromCamera();
//                    }
//                    else{
//                        requestCameraPermission();
//                    }
//                }
//                else{
//                    if(checkStoragePermission()){
//                        pickFromGallery();
//                    }
//                    else{
//                        requestStoragePermission();
//                    }
//                }
//            }
//        });
//    }
    private void pickFromCamera(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE,"image_title");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION,"image_desc");
        image_uri = getActivity().getApplicationContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(intent, IMAGE_PICK_CAMERA_CODE);
    }
    private boolean checkStoragePermission(){
        boolean result = ContextCompat.checkSelfPermission( getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result;
    }
//    private boolean checkCameraPermission(){
//        boolean result = ContextCompat.checkSelfPermission( getActivity(), Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
//
//        boolean result1 = ContextCompat.checkSelfPermission( getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
//        return result && result1;
//    }
//    private void requestStoragePermission(){
//        ActivityCompat.requestPermissions(getActivity(),storagePermission,STORAGE_REQUEST_CODE);
//    }
//    private void requestCameraPermission(){
//        ActivityCompat.requestPermissions(getActivity(),cameraPermission,CAMERA_REQUEST_CODE);
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode){
//            case CAMERA_REQUEST_CODE:{
//                if(grantResults.length>0){
//                    boolean cameraAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;
//                    boolean storageAccepted = grantResults[1]==PackageManager.PERMISSION_GRANTED;
//                    if(cameraAccepted && storageAccepted){
//                        pickFromCamera();
//                    }
//                    else{
//                        Toast.makeText(getActivity(),"Membutuhkan Izin Camera dan Penyimpanan",Toast.LENGTH_LONG ).show();
//                    }
//                }
//            }
//            case STORAGE_REQUEST_CODE:{
//                if(grantResults.length>0){
//                    boolean storageAccepted = grantResults[1]==PackageManager.PERMISSION_GRANTED;
//                    if(storageAccepted){
//                        pickFromGallery();
//                    }
//                    else{
//                        Toast.makeText(getActivity(),"Membutuhkan Izin Penyimpanan",Toast.LENGTH_LONG ).show();
//                    }
//                }
//            }
//
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == IMAGE_PICK_GALLERY_CODE){
                image_uri = data.getData();
                upFoto.setImageURI(image_uri);
            }
            else if(requestCode == IMAGE_PICK_CAMERA_CODE){

                upFoto.setImageURI(image_uri);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}