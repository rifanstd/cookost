package com.example.cookost;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
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
                final CharSequence[] items = {"Take Photo", "Choose From Gallery","Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle((getString(R.string.app_name)));
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setItems(items,(dialog,item)->{
                    if(items[item].equals("Take Photo")){
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,10);
                    }
                    else if (items[item].equals("Choose From Gallery")){
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"Select Image"),20);
                    }
                    else if(items[item].equals("Cancel")){
                        dialog.dismiss();
                    }
                });
                builder.show();
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

                String namaResep = NamaResep.getText().toString();
                String deskripsi = Deskripsi.getText().toString();
                String bahan = Bahan.getText().toString();
                String langkah = Langkah.getText().toString();

                upload(namaResep,deskripsi,bahan,langkah);
//                user.put("fotoMenu",upFoto);
//                user.put("NamaResep",namaResep);
//                user.put("Deskripsi",deskripsi);
//                user.put("Bahan",bahan);
//                user.put("Langkah",langkah);
//                db.collection("Makanan").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(getActivity(), "Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getActivity(), "Tidak Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });
    }
    private void saveData(String nama_resep,String deskripsi,String bahan, String langkah,String Foto){
        Map<String,Object> user = new HashMap<>();
        user.put("foto",Foto);
        user.put("NamaResep",nama_resep);
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
private void upload(String nama_resep,String deskripsi,String bahan, String langkah){
        upFoto.setDrawingCacheEnabled(true);
        upFoto.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) upFoto.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[]data = byteArrayOutputStream.toByteArray();
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference reference = firebaseStorage.getReference("images").child(new Date().getTime()+".jpeg");
        UploadTask uploadTask = reference.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
        }
    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            if(taskSnapshot.getMetadata()!=null){
                if(taskSnapshot.getMetadata().getReference()!=null){
                    taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if(task.getResult()!=null){
                                saveData(nama_resep,deskripsi,bahan,langkah,task.getResult().toString());
                            }
                        }
                    });
                }
            }
        }
    });
}
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==20 && resultCode==Activity.RESULT_OK && data !=null){
            final Uri path = data.getData();
            Thread thread = new Thread(()->{
                try{
                    Context applicationContext = Home.getContextOfApplication();
                    InputStream inputStream = getActivity().getApplicationContext().getContentResolver().openInputStream(path);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    upFoto.post(()->{
                        upFoto.setImageBitmap(bitmap);
                    });
                }catch (IOException e){
                    e.printStackTrace();
                }
            });
            thread.start();

        }
        if(requestCode==10&&resultCode==Activity.RESULT_OK){
            final Bundle extras = data.getExtras();
            Thread thread = new Thread(()->{
               Bitmap bitmap = (Bitmap) extras.get("data");
                upFoto.post(()->{
                    upFoto.setImageBitmap(bitmap);
                });
            });
            thread.start();
        }

    }
}