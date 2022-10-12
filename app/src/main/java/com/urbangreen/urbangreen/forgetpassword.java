package com.urbangreen.urbangreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class forgetpassword extends AppCompatActivity {
    private EditText regemail;
    private Button resetpassword,backBtn;

    private FirebaseAuth mAuth;
   ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        regemail=(EditText) findViewById(R.id.email2);
        resetpassword=findViewById(R.id.resetBtn);
        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.INVISIBLE);
         backBtn=findViewById(R.id.back_buttion);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgetpassword.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
              RESETPASSWORD();

                }

        });



    }

    private void RESETPASSWORD() {
        String email =regemail.getText().toString();
        if(email.isEmpty()){
            regemail.setError("Email required");
            regemail.requestFocus();
            return;
        }
      mAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
          @Override
          public void onSuccess(Void aVoid) {
              Toast.makeText(getApplicationContext(), " email sent . click on link to reset password", Toast.LENGTH_LONG).show();
              Intent intent = new Intent(forgetpassword.this,Login.class);
              startActivity(intent);
              progressBar.setVisibility(View.INVISIBLE);

          }
      }).addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
              Toast.makeText(getApplicationContext(), " try again. check entered  email.", Toast.LENGTH_LONG).show();
              progressBar.setVisibility(View.INVISIBLE);
          }
      });
    }
}