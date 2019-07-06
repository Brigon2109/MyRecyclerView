package com.example.draganddrop;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommandButtonViewAdapter extends RecyclerView.Adapter<CommandButtonViewAdapter.CommandButtonViewHolder> {

    public ArrayList<ListButton> mDataset;

    @NonNull
    @Override
    public CommandButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("ResourceType") View ll =  LayoutInflater.from(parent.getContext()).inflate(R.layout.teaml_commandbutton_layout, parent,!true);
        return new CommandButtonViewHolder(ll);
    }

    @Override
    public void onBindViewHolder(@NonNull CommandButtonViewHolder holder, int position) {
        ListButton oldLB = mDataset.get(position);

        holder.draggableButton.setText(oldLB.getText());
        holder.draggableButton.state = oldLB.state;

        if(holder.draggableButton.state){
            holder.draggableButton.setBackgroundColor(Color.BLACK);
        }else{
            holder.draggableButton.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public CommandButtonViewAdapter(ArrayList<ListButton> mDataset){
        this.mDataset = mDataset;
    }

    public static class CommandButtonViewHolder extends RecyclerView.ViewHolder{

        public ListButton draggableButton;

        public CommandButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            ListButton db = itemView.findViewById(R.id.listButton);
            draggableButton = db;
            if(draggableButton.state){
                draggableButton.setBackgroundColor(Color.BLACK);
            }else{
                draggableButton.setBackgroundColor(Color.WHITE);
            }
        }
    }

    public void printDataSet(){
        for(ListButton listButton: mDataset){
            System.out.println(listButton.getText() + ": " + listButton.state);
        }
    }
}
