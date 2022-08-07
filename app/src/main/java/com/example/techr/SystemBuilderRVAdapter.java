package com.example.techr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SystemBuilderRVAdapter extends RecyclerView.Adapter<SystemBuilderRVAdapter.ViewHolder> {
    private ArrayList<PreBuildModal> preBuildModalArrayList;
    private Context context;
    int lastPos = -1;
    private ProductClickInterface productClickInterface;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public SystemBuilderRVAdapter(ArrayList<PreBuildModal> preBuildModalArrayList, Context context, ProductClickInterface productClickInterface) {
        this.preBuildModalArrayList = preBuildModalArrayList;
        this.context = context;
        this.productClickInterface = productClickInterface;
    }


    @NonNull
    @Override
    public SystemBuilderRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.systembuilder_rv_item,parent,false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull SystemBuilderRVAdapter.ViewHolder holder, int pos){
        final int position = holder.getAdapterPosition();
        holder.CPUTV.setText("CPU: "+preBuildModalArrayList.get(position).getCPU());
        holder.CPUCoolerTV.setText("CPU Cooler: "+preBuildModalArrayList.get(position).getCPUCooler());
        holder.MotherboardTV.setText("Motherboard: "+preBuildModalArrayList.get(position).getMotherboard());
        holder.PowerSupplyTV.setText("Power Supply: "+preBuildModalArrayList.get(position).getPowerSupply());
        holder.MonitorTV.setText("Monitor: "+preBuildModalArrayList.get(position).getMonitor());
        holder.StorageTV.setText("Storage: "+preBuildModalArrayList.get(position).getStorage());
        holder.MemoryTV.setText("Memory: "+preBuildModalArrayList.get(position).getMemory());
        holder.VideoCardTV.setText("Video Card: "+preBuildModalArrayList.get(position).getVideoCard());
        holder.PriceTag.setText("Rs. "+preBuildModalArrayList.get(position).getTotal()+"/-");

        setAnimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productClickInterface.onProductClick(position);
            }
        });

    }

    private void setAnimation(View itemView, int position){
        if(position>lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return preBuildModalArrayList.size();
    }

    public interface ProductClickInterface{
        void onProductClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView CPUTV,CPUCoolerTV,MotherboardTV, PowerSupplyTV, MonitorTV, StorageTV, MemoryTV, VideoCardTV, PriceTag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CPUTV = itemView.findViewById(R.id.idTVCPUName);
            CPUCoolerTV = itemView.findViewById(R.id.idCPUCooler);
            MotherboardTV = itemView.findViewById(R.id.idMotherboard);
            PowerSupplyTV = itemView.findViewById(R.id.idPowerSupply);
            MonitorTV = itemView.findViewById(R.id.idMonitor);
            StorageTV = itemView.findViewById(R.id.idStorage);
            MemoryTV = itemView.findViewById(R.id.idMemory);
            VideoCardTV = itemView.findViewById(R.id.idVideoCard);
            PriceTag = itemView.findViewById(R.id.idPriceTag);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }

}
