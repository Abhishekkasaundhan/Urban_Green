package com.urbangreen.urbangreen;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView PlantName,PlantDescription;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView=itemView.findViewById(R.id.imgPlant);
        PlantName=itemView.findViewById(R.id.txtPlantName);
        PlantDescription=itemView.findViewById(R.id.txtPlantDescription);



    }
}
