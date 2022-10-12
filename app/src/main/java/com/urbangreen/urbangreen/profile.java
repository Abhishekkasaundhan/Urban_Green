package com.urbangreen.urbangreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;

public class profile extends AppCompatActivity {
    private ImageView userimage,emaillogo,userlogo,phonelogo;
    private TextView userprofiletext;
    private  Button logoutBtn;

    private FirebaseAuth mAuth;
   private  FirebaseUser user;
    private DatabaseReference reference;
    private String USERID;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


      mAuth=FirebaseAuth.getInstance();



        emaillogo=findViewById(R.id.useremaillogo);
        userlogo=findViewById(R.id.usernamelogo);
        phonelogo=findViewById(R.id.userphonelogo);

        userimage=findViewById(R.id.Userimage);

       user=FirebaseAuth.getInstance().getCurrentUser();
       reference=FirebaseDatabase.getInstance().getReference("users");
       USERID=user.getUid();


       final  TextView DisplayName=(TextView)findViewById(R.id.username);
        final  TextView DisplayEmail=(TextView)findViewById(R.id.useremail);
        final  TextView DisplayPhone=(TextView)findViewById(R.id.userphone);
        reference.child(USERID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelperClass userProfile=snapshot.getValue(UserHelperClass.class);
                if(userProfile!=null){
                    String fullname=userProfile.name;
                    String UserEMAIL=userProfile.email;
                    String UserPHONE =userProfile.phone;

                    DisplayName.setText(fullname);
                    DisplayEmail.setText(UserEMAIL);
                    DisplayPhone.setText(UserPHONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(profile.this,"something went wrong",Toast.LENGTH_LONG).show();
            }
        });



        logoutBtn=findViewById(R.id.logoutbutton);
       logoutBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mAuth.signOut();
               finish();
               startActivity(new Intent(profile.this,MainActivity.class));
           }
       });



    }




}