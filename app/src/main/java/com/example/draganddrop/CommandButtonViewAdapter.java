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
import java.util.List;

public class CommandButtonViewAdapter extends RecyclerView.Adapter<CommandButtonViewAdapter.CommandButtonViewHolder> {

    public ArrayList<ListButton> mDataset;

    @NonNull
    @Override
    public CommandButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("ResourceType") View ll =  LayoutInflater.from(parent.getContext()).inflate(R.layout.teaml_commandbutton_layout, parent,!true);
        return new CommandButtonViewHolder(ll,  mDataset);
    }

    @Override
    public void onBindViewHolder(@NonNull CommandButtonViewHolder holder, int position) {
        ListButton oldLB = mDataset.get(position);

        holder.draggableButton.setText(oldLB.getText());
        //holder.draggableButton.setState(oldLB.getState(), "onBindViewHolder");

        if(holder.draggableButton.getState()){
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

        public CommandButtonViewHolder(@NonNull View itemView, ArrayList<ListButton> mDataset) {
            super(itemView);
            ListButton db = itemView.findViewById(R.id.listButton);
            db.setText(mDataset.get(mDataset.size()-1).getText());
            mDataset.set(mDataset.size()-1, db);
            draggableButton = db;
            if(draggableButton.getState()){
                draggableButton.setBackgroundColor(Color.BLACK);
            }else{
                draggableButton.setBackgroundColor(Color.WHITE);
            }
        }
    }

    public void printDataSet(){
        for(ListButton listButton: mDataset){
            System.out.println(listButton.getText() + ": " + listButton.getState());
        }
    }
}
