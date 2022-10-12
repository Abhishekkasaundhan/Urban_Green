package com.urbangreen.urbangreen;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class orderDetail extends AppCompatActivity {
    private EditText OrderAddress,OrderPhone,OrderPIN,Plantid,orderName;
    private Button confirmOrder;
    FirebaseDatabase Db;
    DatabaseReference DbRef;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        mAuth=FirebaseAuth.getInstance();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        OrderAddress=findViewById(R.id.orderAddress);
        OrderPhone=findViewById(R.id.orderPhone);
        OrderPIN=findViewById(R.id.orderPincode);
        confirmOrder=findViewById(R.id.orderconfirm);
        orderName=findViewById(R.id.orderName);
        Plantid=findViewById(R.id.plant_id);
        confirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Address=OrderAddress.getText().toString().trim();
                String PHONE=OrderPhone.getText().toString().trim();
                String pin=OrderPIN.getText().toString().trim();
                String plantID=Plantid.getText().toString().trim();
                String Name=orderName.getText().toString().trim();
                Db=FirebaseDatabase.getInstance();
                DbRef=Db.getReference("order detail");
                if (TextUtils.isEmpty(Address)) {
                    Toast.makeText(getApplicationContext(), "Enter  address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Name)) {
                    Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(PHONE)) {
                    Toast.makeText(getApplicationContext(), "enter phone", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(plantID)) {
                    Toast.makeText(getApplicationContext(), "enter plant id  which was given in description", Toast.LENGTH_SHORT).show();
                    return;}
                if (TextUtils.isEmpty(pin)) {
                    Toast.makeText(getApplicationContext(), "enter pin", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pin.length() < 6) {
                    Toast.makeText(getApplicationContext(), "pin invalid,enter 6 digit", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (PHONE.length() < 10) {
                    Toast.makeText(getApplicationContext(), "invalid phone number , enter 10 digit phone number", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(getApplicationContext(), "order placed ", Toast.LENGTH_LONG).show();

                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    String Userid=mAuth.getUid();
                    String Orderid=DbRef.push().getKey();

                    order orderdetail=new order(Address,PHONE,pin,plantID,Name);
                    DbRef.child(Orderid).setValue(orderdetail);
                    Intent intent = new Intent(orderDetail.this,home.class);
                    startActivity(intent);
                    finish();
                }


            }
        });
    }
}