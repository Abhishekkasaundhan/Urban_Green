package com.urbangreen.urbangreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PlantDetails extends AppCompatActivity {

    ImageView imgPlant;
    TextView txtPlantName,txtPlantDescription,txtPlantPrice,PlantID,PlantPRICE,PlantLink,SellerPhone;
    Button btnOrderNow;
    ImageButton imgBtnBack;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_details);
        imgPlant=findViewById(R.id.imgPlant);
        txtPlantName=findViewById(R.id.txtPlantName);

        txtPlantDescription=findViewById(R.id.txtPlantDescription);
        txtPlantPrice=findViewById(R.id.txtPlantPrice);
        btnOrderNow=findViewById(R.id.btnOrderNow);
        imgBtnBack=findViewById(R.id.imgBtnBack);


       //new objects
         PlantID=findViewById(R.id.plant_IDOP);
         PlantPRICE=findViewById(R.id.plant_price);
        PlantLink=findViewById(R.id.PlantLink);
        SellerPhone=findViewById(R.id.Phone);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        String plantName=getIntent().getStringExtra("plantname");
        String plantDescription=getIntent().getStringExtra("plantdesc");
        String plantImage=getIntent().getStringExtra("plantimg");
        String plantprice=getIntent().getStringExtra("plantPrice");
        String PLANTid=getIntent().getStringExtra("PlantID");
        String plantLink=getIntent().getStringExtra("PlantLink");
        String sellerPhone=getIntent().getStringExtra("SellerPhone");
        txtPlantName.setText(plantName);
        txtPlantDescription.setText(plantDescription);


       PlantPRICE.setText(plantprice);
       PlantID.setText(PLANTid);
       PlantLink.setText(plantLink);
       SellerPhone.setText(sellerPhone);

        Glide.with(this).load(plantImage).into(imgPlant);

        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Intent intent = new Intent(PlantDetails.this,orderDetail.class);
                        startActivity(intent);
                        finish();
                    }
                },2000);
            }
        });

    }
}