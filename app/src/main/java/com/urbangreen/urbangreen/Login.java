package com.urbangreen.urbangreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private EditText loginemail,loginpassword;
       private TextView gotoMAIN;
private Button login;
   private FirebaseAuth mAuth;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginemail=(EditText)findViewById(R.id.loginEmail);
        loginpassword=(EditText)findViewById(R.id.loginPassword);
      gotoMAIN=findViewById(R.id.goTOmain);

        login = (Button)findViewById(R.id.LoginBtn);
          mAuth=FirebaseAuth.getInstance();
          gotoMAIN.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(Login.this,Register.class);
                  startActivity(intent);
                  finish();
              }
          });

      if (mAuth.getCurrentUser()!=null){
           FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
          if(user.isEmailVerified()){
              Intent intent = new Intent(Login.this, home.class);
              startActivity(intent);}


      }
      login.setOnClickListener(new  View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String email=loginemail.getText().toString();
              final String password=loginpassword.getText().toString();
              //for empty field
              if (TextUtils.isEmpty(email)) {
                  Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                  return;
              }

              if (TextUtils.isEmpty(password)) {
                  Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                  return;
              }


              mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {

                      if (task.isSuccessful()) {
                          FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();



                          if(user.isEmailVerified()){
                          Intent intent = new Intent(Login.this, home.class);
                          startActivity(intent);




                          }else {


                              Toast.makeText(getApplicationContext(), "check your email to verify account", Toast.LENGTH_LONG).show();
                          }
                      }
                       else {
                          Toast.makeText(Login.this, "login failed", Toast.LENGTH_LONG).show();
                      }
                  }
              });
          }
      });

}



}