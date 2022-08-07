package com.example.techr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class SystemBuilder extends AppCompatActivity implements SystemBuilderRVAdapter.ProductClickInterface{

    private RecyclerView SystemBuilderRV;
    public static ArrayList<PreBuildModal> preBuildModalArrayList;
    private FirebaseFirestore db;
    private FirebaseAuth fAuth;
    private SystemBuilderRVAdapter systemBuilderRVAdapter;
    private TextView pricetag;
    String idnum,idnum1,idnum2,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_builder);

        pricetag = findViewById(R.id.idPriceTag);

        SystemBuilderRV = findViewById(R.id.idRVSystemBuilder);
        SystemBuilderRV.setHasFixedSize(true);
        SystemBuilderRV.setLayoutManager(new LinearLayoutManager(this));



        db = FirebaseFirestore.getInstance();
        preBuildModalArrayList = new ArrayList<>();
        fAuth= FirebaseAuth.getInstance();

        systemBuilderRVAdapter = new SystemBuilderRVAdapter(preBuildModalArrayList,this,this);
        SystemBuilderRV.setAdapter(systemBuilderRVAdapter);


        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            idnum=bundle.getString("CPU");
            idnum1=bundle.getString("Motherboard");
            idnum2=bundle.getString("GPU");

        }

        String a = idnum;
        String b = idnum1;
        String c = idnum2;
        if(a == null){
            getAllProducts1();
        }else {
            getAllProducts(a, b, c);
        }

    }
    public void BackArrow(View view) {
        startActivity(new Intent(getApplicationContext(),Dashboard.class));
    }

    @Override
    public void onProductClick(int position) {
    }

    public void getAllProducts(String C,String D,String E){
        String a =C;
        String b= D;
        String c = E;


        db.collection("PreBuilds")
                .whereEqualTo("CPU", a)
                .whereEqualTo("Motherboard",b)
                .whereEqualTo("Video Card",c)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        preBuildModalArrayList.clear();
                        for(DocumentSnapshot snapshot : task.getResult()){
                            PreBuildModal preBuildModal = new PreBuildModal(snapshot.getString("CPU"),snapshot.getString("CPU Cooler"),snapshot.getString("Memory"),snapshot.getString("Monitor"),snapshot.getString("Motherboard"),snapshot.getString("Power Supply"),snapshot.getString("Storage"),snapshot.getString("Video Card"),snapshot.getString("Total Amount"));
                            preBuildModalArrayList.add(preBuildModal);
                        }
                        systemBuilderRVAdapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SystemBuilder.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getAllProducts1(){

        db.collection("PreBuilds")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        preBuildModalArrayList.clear();
                        for(DocumentSnapshot snapshot : task.getResult()){
                            PreBuildModal preBuildModal = new PreBuildModal(snapshot.getString("CPU"),snapshot.getString("CPU Cooler"),snapshot.getString("Memory"),snapshot.getString("Monitor"),snapshot.getString("Motherboard"),snapshot.getString("Power Supply"),snapshot.getString("Storage"),snapshot.getString("Video Card"),snapshot.getString("Total Amount"));
                            preBuildModalArrayList.add(preBuildModal);
                        }
                        systemBuilderRVAdapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SystemBuilder.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void SearchFilter(View view){
        try {
            Intent i = new Intent(getApplicationContext(), PopActivity.class);
            startActivity(i);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}