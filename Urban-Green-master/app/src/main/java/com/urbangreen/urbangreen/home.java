package com.urbangreen.urbangreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.urbangreen.urbangreen.Adapters.PlantAdapter;
import com.urbangreen.urbangreen.Models.PlantModel;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity implements PlantAdapter.PlantClickEvents{


    RecyclerView recyclerPlants;
    FirebaseAuth auth;
    PlantAdapter plantAdapter;
    private Button logOutBtn,gameBtn,ProfileBtn, addProduct;
    DatabaseReference mDBref;
    FirebaseDatabase Db;

    FirebaseRecyclerOptions<PlantModel>options;
    FirebaseRecyclerAdapter<PlantModel, MyViewHolder>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //db ref
        mDBref= FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_home);

     addProduct=findViewById(R.id.AddProBtn);
     logOutBtn=findViewById(R.id.logOutBtn);
     gameBtn=findViewById(R.id.gameBtn);
     ProfileBtn=findViewById(R.id.profileBtn);


        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {            Intent intent = new Intent(home.this,AddProduct.class);
                startActivity(intent);
                finish();

            }
        });
        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {            Intent intent = new Intent(home.this,game.class);
                startActivity(intent);
                finish();

            }
        });
        ProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {            Intent intent = new Intent(home.this,profile.class);
                startActivity(intent);
                finish();

            }
        });
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                finish();
                startActivity(new Intent(home.this,MainActivity.class));
            }
        });


        recyclerPlants=findViewById(R.id.recyclerPlants);
        recyclerPlants.setHasFixedSize(true);
        recyclerPlants.setLayoutManager(new LinearLayoutManager(this));
        auth=FirebaseAuth.getInstance();
        plantAdapter=new PlantAdapter(this,this);
        recyclerPlants.setAdapter(plantAdapter);

         Db=FirebaseDatabase.getInstance();
        mDBref=Db.getInstance().getReference("products");




        loadData();

    }

    private void loadData(){
        final List<PlantModel>plantModelList=new ArrayList<>();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("products");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                plantModelList.clear();
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        PlantModel plantModel=dataSnapshot.getValue(PlantModel.class);
                        plantModelList.add(plantModel);
                    }
                    plantAdapter.setPlantList(plantModelList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onPlantClick(PlantModel plantModel) {
        Intent intent=new Intent(home.this,PlantDetails.class);
        intent.putExtra("plantname",plantModel.getPlantname());
        intent.putExtra("plantdesc",plantModel.getDescription());
        intent.putExtra("plantimg",plantModel.getmImageUri());
        intent.putExtra("plantPrice",plantModel.getPrice());
        intent.putExtra("PlantID",plantModel.getPlant_id());
        intent.putExtra("PlantLink",plantModel.getPlantLink());
        intent.putExtra("SellerPhone",plantModel.getSellerPhone());
        startActivity(intent);
    }
}