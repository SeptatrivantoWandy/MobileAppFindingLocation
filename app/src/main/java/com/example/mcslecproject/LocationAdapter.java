package com.example.mcslecproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder> {

    public static final String SEND_ID = "com.example.mcslecproject.SEND_ID";

    int userId;

    private Context ctx;
    private Vector<LocationData> locations;

    public LocationAdapter(int userId, Context ctx) {
        this.userId = userId;
        this.ctx = ctx;
    }

    public void setLocations(Vector<LocationData> locations) {
        this.locations = locations;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.home_form_component, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.attractionName.setText(locations.get(position).getAttractionName());
        holder.attractionImage.setImageResource(locations.get(position).getAttractionImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, LocationDetailForm.class);

                intent.putExtra("id", locations.get(position).getAttractionId());
                intent.putExtra("foto", locations.get(position).getAttractionImage());
                intent.putExtra("nama", locations.get(position).getAttractionName());
                intent.putExtra("deskripsi", locations.get(position).getAttractionDescription());
                intent.putExtra("alamat", locations.get(position).getAttractionLocationStreet());
                intent.putExtra("jadwal", locations.get(position).getAttractionSchedule());
                intent.putExtra("telp", locations.get(position).getAttractionPhone());
                intent.putExtra("website", locations.get(position).getAttractionWebsite());
                intent.putExtra("longitude", locations.get(position).getAttractionLongitude());
                intent.putExtra("latitude", locations.get(position).getAttractionLatitude());

                intent.putExtra("userid", userId);

                ctx.startActivity(intent);
                ((HomeForm)ctx).finish();

            }
        });

    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView attractionImage;
        TextView attractionName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            attractionImage = itemView.findViewById(R.id.hfAttractionImage);
            attractionName = itemView.findViewById(R.id.hfAttractionNameTV);
        }
    }

}
