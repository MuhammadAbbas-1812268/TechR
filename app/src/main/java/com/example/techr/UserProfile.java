package com.example.techr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity {

    private ArrayList<UserHelperClass> userHelperClassArrayList;
    private FirebaseFirestore db;
    private FirebaseAuth fAuth;
    private DocumentReference reference;
    private TextView Name,Title,Email,Phone;
    private TextView EditProf,ChangePW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Name= findViewById(R.id.UserFnameTV);
        Title=findViewById(R.id.UserProfTV);
        Email=findViewById(R.id.UserEmailTV);
        Phone=findViewById(R.id.UserPhoneTV);
        EditProf=findViewById(R.id.editProfBtn);


    }
    public void BackArrow(View view) {
        startActivity(new Intent(getApplicationContext(),Dashboard.class));
    }

    public void editprofile(View view){
        startActivity(new Intent(getApplicationContext(),EditUserProfile.class));
    }

    public void SignOut(View view) {
        startActivity(new Intent(getApplicationContext(),Welcome.class));
    }

    public void updateUserData(int position){
        UserHelperClass item = userHelperClassArrayList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("fName", item.getUserName());
        bundle.putString("email", item.getUserEmail());
        bundle.putString("fName", item.getUserName());
        bundle.putString("phone", item.getPhoneNumber());
        bundle.putInt("position",position);
        Intent i = new Intent(getApplicationContext(), EditUserProfile.class);
        i.putExtras(bundle);
        startActivity(i);
    }



    public void onStart(){
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String UserID = user.getUid();

        db=FirebaseFirestore.getInstance();
        reference=db.collection("Users").document(UserID);

        reference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(task.getResult().exists()){

                            String nameResult = task.getResult().getString("fName").trim();
                            String emailResult = task.getResult().getString("email");
                            String titleResult = task.getResult().getString("fName");
                            String phoneResult = task.getResult().getString("phone");

                            Name.setText(nameResult);
                            Title.setText(titleResult);
                            Email.setText(emailResult);
                            Phone.setText(phoneResult);


                        }else{
                            Intent i = new Intent(getApplicationContext(),Dashboard.class);
                            startActivity(i);
                        }
                    }
                });

    }
}