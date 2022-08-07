package com.example.techr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Processors extends AppCompatActivity {

    private FirebaseFirestore db;
    private ArrayList<ProductRVModal> productRVModalArrayList;
    private ProductRVAdapter productRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processors);

    }

    public void showAll(){
        String productCategory = "CPU";
        db.collection("Products").whereEqualTo("productCategory",productCategory).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        productRVModalArrayList.clear();
                        for(DocumentSnapshot snapshot : task.getResult()){
                            ProductRVModal productRVModal = new ProductRVModal(snapshot.getString("productName"),snapshot.getString("productPrice"),snapshot.getString("productCategory"),snapshot.getString("productImage"),snapshot.getString("productDescription"),snapshot.getString("productID"));
                            productRVModalArrayList.add(productRVModal);
                        }
                        productRVAdapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Processors.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}