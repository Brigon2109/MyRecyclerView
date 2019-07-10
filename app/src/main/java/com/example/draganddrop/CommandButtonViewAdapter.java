package com.example.draganddrop;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull CommandButtonViewHolder holder, int position) {
        ListButton oldLB = mDataset.get(position);
        holder.draggableButton.setBackground(oldLB.getBackground());
        holder.draggableButton.setText(oldLB.getText());
        holder.draggableButton.setCurrentType(oldLB.getCurrentType());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
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
        }
    }
}