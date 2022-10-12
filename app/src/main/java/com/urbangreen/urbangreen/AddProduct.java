package com.urbangreen.urbangreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class AddProduct extends AppCompatActivity {

    private EditText plantNameIp, plantDescIp, plantPriceIp, sellerPhone, plantIDip, PlantLink;
    private Button SubmitBtn;

    private FirebaseAuth mAuth;
    private FirebaseDatabase Db;
    private DatabaseReference DbRef;
    private ProgressBar progressBar;
    private StorageReference storageReference;

    // picker
    private static final int PICK_IMG_REQUEST = 1;
    private ImageView uploadIMg;
    private Uri mImageuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        plantDescIp = findViewById(R.id.plantDesInput);
        sellerPhone = findViewById(R.id.sellerphone);
        plantIDip = findViewById(R.id.Plantid);
        PlantLink = findViewById(R.id.PlantLink);
        plantNameIp = findViewById(R.id.plantNameInput);

        plantPriceIp = findViewById(R.id.plantPriceInput);

        SubmitBtn = findViewById(R.id.SubmitBtn);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);


        //new code





        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String price = plantPriceIp.getText().toString().trim();
                final String description = plantDescIp.getText().toString().trim();
                final String plantname = plantNameIp.getText().toString().trim();
                final String sellerphone = sellerPhone.getText().toString().trim();
                final String plant_ID = plantIDip.getText().toString().trim();
                final String Plant_link = PlantLink.getText().toString().trim();

               progressBar.setVisibility(View.VISIBLE);
                Db = FirebaseDatabase.getInstance();
                DbRef = Db.getReference("products");
                mAuth = FirebaseAuth.getInstance();
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                if (TextUtils.isEmpty(description)) {
                    Toast.makeText(getApplicationContext(), "Enter  description!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    return;

                }
                if (TextUtils.isEmpty(Plant_link)) {
                    Toast.makeText(getApplicationContext(), "Enter Image link!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    return;

                }
                if (TextUtils.isEmpty(plantname)) {
                    Toast.makeText(getApplicationContext(), "enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(price)) {
                    Toast.makeText(getApplicationContext(), "enter price", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                if (TextUtils.isEmpty(sellerphone)) {
                    Toast.makeText(getApplicationContext(), "enter phone no.", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                if (sellerphone.length() < 10) {
                    Toast.makeText(getApplicationContext(), "enter  valid phone no.", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    return;

                } else {
                                       addItem products = new addItem(description, plantname, price, sellerphone, plant_ID, Plant_link);
                    String UploadID = DbRef.push().getKey();
                    DbRef.child(UploadID).setValue(products);
                    Toast.makeText(getApplicationContext(), "Uploaded successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddProduct.this, home.class);
                    startActivity(intent);
                    finish();
                    progressBar.setVisibility(View.INVISIBLE);



                }


            }
        });

    }
}



