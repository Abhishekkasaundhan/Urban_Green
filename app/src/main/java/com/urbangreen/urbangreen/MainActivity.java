package com.urbangreen.urbangreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button gotoLogin,gotoRegister,gotoResetPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotoLogin = (Button)findViewById(R.id.gotoLogin);
       gotoResetPassword = (Button)findViewById(R.id.resetPassword);
        gotoRegister = (Button)findViewById(R.id.gotoRegister);
        mAuth=FirebaseAuth.getInstance();


            if (mAuth.getCurrentUser()!=null){
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                if(user.isEmailVerified()){
                    Intent intent = new Intent(MainActivity.this, home.class);
                    startActivity(intent);}
                else{
                    Intent intent = new Intent(MainActivity.this,Login.class);
                    startActivity(intent);

                }


            }

        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);

            }
        });
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);

            }
        });
         gotoResetPassword.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this,forgetpassword.class);
                 startActivity(intent);
             }

         });

    }
}