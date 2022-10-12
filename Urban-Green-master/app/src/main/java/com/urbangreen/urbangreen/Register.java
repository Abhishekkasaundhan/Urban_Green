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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText registername,registerphone,registeremail,registerpassword;
    private Button registernow;
    private FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registername=(EditText)findViewById(R.id.registerName);
        registeremail=(EditText)findViewById(R.id.registerEmail);
        registerphone=(EditText)findViewById(R.id.registerPhone);
        registerpassword=(EditText)findViewById(R.id.registerPassword);
        registernow =(Button)findViewById(R.id.RegisterNow);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth=FirebaseAuth.getInstance();
       FirebaseDatabase database=FirebaseDatabase.getInstance();






        registernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = registeremail.getText().toString().trim();
                String password = registerpassword.getText().toString().trim();
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("users");

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (registerphone.length() < 10) {
                    Toast.makeText(getApplicationContext(), "invalid phone", Toast.LENGTH_SHORT).show();
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                        if (!task.isSuccessful()) {
                            Toast.makeText(Register.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                            String Userid=mAuth.getUid();
                            String name=registername.getEditableText().toString();
                            String email=registeremail.getEditableText().toString();
                            String phone=registerphone.getEditableText().toString();
                            String password=registerpassword.getEditableText().toString();
                            UserHelperClass helperClass=new UserHelperClass(name,phone,email,password);
                            reference.child(Userid).setValue(helperClass);

                            if(user.isEmailVerified()){


                            startActivity(new Intent(Register.this, Login.class));
                            finish();
                        }else{ user.sendEmailVerification();

                                Toast.makeText(getApplicationContext(), "check your email to verify account", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Register.this,Login.class);
                                startActivity(intent);
                                finish();
                            }

                        }

                    }
                });

            }
        });

        }
    }
