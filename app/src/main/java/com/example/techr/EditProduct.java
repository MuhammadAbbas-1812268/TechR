package com.example.techr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EditProduct extends AppCompatActivity {

    private EditText productNameEdt,productCategoryEdt,productPriceEdt,productImageEdt,productDescriptionEdt;
    private Button updateProductBtn,deleteProductBtn;
    private ProgressBar loadingPB;
    private FirebaseFirestore db;
    private String productID,productName,productPrice,productCategory,productImageLink,productDescription;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        productNameEdt = findViewById(R.id.ProductName);
        productCategoryEdt = findViewById(R.id.ProductCategory);
        productPriceEdt = findViewById(R.id.ProductPrice);
        productImageEdt = findViewById(R.id.ProductImageLink);
        productDescriptionEdt = findViewById(R.id.ProductDescription);
        updateProductBtn = findViewById(R.id.BtnUpdateProduct);
        deleteProductBtn = findViewById(R.id.BtnDeleteProduct);
        loadingPB = findViewById(R.id.PBloading);

        db = FirebaseFirestore.getInstance();


        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            productName = bundle.getString("productName");
            productPrice = bundle.getString("productPrice");
            productCategory = bundle.getString("productCategory");
            productImageLink = bundle.getString("productImage");
            productDescription = bundle.getString("productDescription");
            productID = bundle.getString("productID");
            productNameEdt.setText(productName);
            productCategoryEdt.setText(productCategory);
            productPriceEdt.setText(productPrice);
            productDescriptionEdt.setText(productDescription);
            productImageEdt.setText(productImageLink);
            position = bundle.getInt("position",-1);

        }


        updateProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                productName = productNameEdt.getText().toString();
                productCategory = productCategoryEdt.getText().toString();
                productPrice = productPriceEdt.getText().toString();
                productImageLink = productImageEdt.getText().toString();
                productDescription = productDescriptionEdt.getText().toString();

                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 != null){
                    String id = productID;
                    Toast.makeText(EditProduct.this, productID, Toast.LENGTH_SHORT).show();
                    updateToFireStore(id, productName, productCategory,productPrice,productImageLink,productDescription);
                }

            }
        });
        deleteProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //productRVAdapter.deleteData(position);
                ProductRVModal item = BrowseProducts.productRVModalArrayList.get(position);
                db.collection("Products").document(item.getProductID()).delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    startActivity(new Intent(EditProduct.this,BrowseProducts.class));
                                    Toast.makeText(EditProduct.this, "Product Deleted", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(EditProduct.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
    private void updateToFireStore(String id,String productName,String productCategory,String productPrice,String productImageLink,String productDescription){

        db.collection("Products").document(id).update("productName", productName,"productPrice", productPrice,"productCategory", productCategory,"productImage", productImageLink,"productDescription", productDescription)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            loadingPB.setVisibility(View.GONE);
                            Toast.makeText(EditProduct.this, "Product Updated..", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditProduct.this,BrowseProducts.class));
                        }else{
                            Toast.makeText(EditProduct.this, "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProduct.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}