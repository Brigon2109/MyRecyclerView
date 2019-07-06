package com.example.draganddrop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    static ArrayList<ListButton> blasMirDochEinen = new ArrayList<>();
    static CommandButtonViewAdapter cbva = new CommandButtonViewAdapter(blasMirDochEinen);
    static boolean lastValue = false;

    public static Drawable draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView rv = findViewById(R.id.DragSurface);

        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        rv.getRecycledViewPool().setMaxRecycledViews(1000, 0);
        rv.setAdapter(cbva);

        ListButton newInstance = new ListButton(getApplicationContext());
        newInstance.setText("Kein Glas Wein");
        blasMirDochEinen.add(newInstance);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                moveItem(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                deleteItem(viewHolder.getAdapterPosition());
            }

        });

        itemTouchHelper.attachToRecyclerView(rv);

        rv.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                int event = dragEvent.getAction();
                switch (event){
                    case DragEvent.ACTION_DRAG_STARTED:
                        view.setBackgroundColor(Color.WHITE);
                        view.invalidate();
                        return true;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        view.setBackgroundColor(Color.BLUE);
                        view.invalidate();
                        lastValue = true;
                        return true;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED:
                        view.setBackgroundColor(Color.WHITE);
                        view.invalidate();
                        lastValue = false;
                        return true;
                    case DragEvent.ACTION_DROP:
                        ClipData cp = dragEvent.getClipData();
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED:{
                        if(BuildConfig.DEBUG){
                        }
                        if(dragEvent.getResult() && lastValue){
                            ListButton newInstance = new ListButton(getApplicationContext());
                            newInstance.setText("TEST" + cbva.mDataset.size());
                            blasMirDochEinen.add(newInstance);
                            cbva.notifyDataSetChanged();

                            view.setBackgroundColor(Color.GREEN);
                            view.invalidate();
                            cbva.printDataSet();
                        }else {
                            view.setBackgroundColor(Color.RED);
                            view.invalidate();
                        }
                    }
                }
                return true;
            }
        });
    }

    private void moveItem(int oldPos, int newPos) {
        ListButton dba = blasMirDochEinen.get(oldPos);
        blasMirDochEinen.remove(oldPos);

        blasMirDochEinen.add(newPos, dba);
        cbva.notifyItemMoved(oldPos, newPos);

    }

    private void deleteItem(int oldPos) {
        blasMirDochEinen.remove(oldPos);
        cbva.notifyItemRemoved(oldPos);
    }
    
}
