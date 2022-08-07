package com.example.techr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EditUserProfile extends AppCompatActivity {

    private EditText username,userphone;
    private Button updateuser;
    private ArrayList<UserHelperClass> userHelperClassArrayList;
    private FirebaseFirestore db;
    private FirebaseAuth fAuth;
    private String UserName,UserPhone,UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        username = findViewById(R.id.UseNameET);
        userphone = findViewById(R.id.UserPhoneET);
        updateuser = findViewById(R.id.BtnUpdateProfile);


        db= FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            UserName = bundle.getString("fName");
            UserPhone = bundle.getString("phone");
            username.setText(UserName);
            userphone.setText(UserPhone);
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserID = user.getUid();

        updateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserName = username.getText().toString();
                UserPhone = userphone.getText().toString();

                updateToFireStore(UserID,UserName,UserPhone);

            }
        });

    }

    private void updateToFireStore(String id,String userName,String userPhone){

        db.collection("Users").document(id).update("fName", userName,"phone", userPhone)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(EditUserProfile.this, "User Updated..", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditUserProfile.this,UserProfile.class));
                        }else{
                            Toast.makeText(EditUserProfile.this, "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditUserProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}