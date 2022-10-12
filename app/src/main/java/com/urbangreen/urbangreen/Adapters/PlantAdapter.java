package com.urbangreen.urbangreen.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.urbangreen.urbangreen.Models.PlantModel;
import com.urbangreen.urbangreen.R;

import java.util.ArrayList;
import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {

    Context mCxt;
    List<PlantModel>plantModelList=new ArrayList<>();
    PlantClickEvents plantClickEvents;

    public PlantAdapter(Context mCxt, PlantClickEvents plantClickEvents) {
        this.mCxt = mCxt;
        this.plantClickEvents = plantClickEvents;
    }

    public void setPlantList(List<PlantModel>plantList){
        plantModelList=plantList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mCxt).inflate(R.layout.items_plants,parent,false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        final PlantModel plantModel=plantModelList.get(position);
        holder.txtPlantName.setText(plantModel.getPlantname());
        holder.txtPlantDescription.setText(plantModel.getDescription());
       

        Glide.with(mCxt).load(plantModel.getmImageUri()).into(holder.imgPlant);

        holder.relMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plantClickEvents.onPlantClick(plantModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plantModelList.size();
    }

    public class PlantViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout relMain;
        ImageView imgPlant;
        TextView txtPlantName,txtPlantDescription, PlantID,  PlantPRICE,plantLink,sellerphone;

        public PlantViewHolder(@NonNull View itemView) {
            super(itemView);
            relMain=itemView.findViewById(R.id.relMain);
            imgPlant=itemView.findViewById(R.id.imgPlant);
            txtPlantName=itemView.findViewById(R.id.txtPlantName);
            txtPlantDescription=itemView.findViewById(R.id.txtPlantDescription);

            PlantID=itemView.findViewById(R.id.plant_IDOP);
            PlantPRICE=itemView.findViewById(R.id.plant_price);
            plantLink=itemView.findViewById(R.id.PlantLink);
            sellerphone=itemView.findViewById(R.id.Phone);

        }
    }

    public interface PlantClickEvents{
        void onPlantClick(PlantModel plantModel);
    }
}
