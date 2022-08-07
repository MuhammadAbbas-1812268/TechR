package com.example.techr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AdminDashboardActivity extends AppCompatActivity {

    private FirebaseAuth fAuth;
    private FirebaseFirestore db;
    private DocumentReference reference;
    private ArrayList<UserHelperClass> userHelperClassArrayList;
    private TextView UserName;

    public void AccSetting(View view) {
        startActivity(new Intent(getApplicationContext(),UserProfile.class));
    }


    public void Browse(View view) {
        startActivity(new Intent(getApplicationContext(),BrowseProductsAdmin.class));
    }
    public void BrowseCategory(View view) {
        startActivity(new Intent(getApplicationContext(),CardViewDesign.class));
    }

    public void SignOut(View view) {
        startActivity(new Intent(getApplicationContext(),Welcome.class));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        UserName= findViewById(R.id.textUsername);
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();



    }

    public void onStart(){
        super.onStart();

        FirebaseUser user = fAuth.getInstance().getCurrentUser();
        String UserID = user.getUid();

        db=FirebaseFirestore.getInstance();
        reference=db.collection("Users").document(UserID);

        reference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(task.getResult().exists()){

                            String nameResult = task.getResult().getString("fName").trim();

                            UserName.setText(nameResult);


                        }else{
                            Intent i = new Intent(getApplicationContext(),AdminDashboardActivity.class);
                            startActivity(i);
                        }
                    }
                });

    }






}