package com.example.draganddrop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    static ArrayList<ListButton> blasMirDochEinen = new ArrayList<>();
    static CommandButtonViewAdapter cbva = new CommandButtonViewAdapter(blasMirDochEinen);
    static boolean lastValue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DraggableButton db1 = findViewById(R.id.dragButton);
        DraggableButton db2= findViewById(R.id.ifButton);

        db1.setCurrentType(DraggableButton.CommandType.MOVE_TO);
        db2.setCurrentType(DraggableButton.CommandType.IF_BRANCHE);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView rv = findViewById(R.id.DragSurface);

        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(cbva);

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
                    case DragEvent.ACTION_DRAG_ENTERED:
                        lastValue = true;
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED:
                        view.setBackgroundColor(Color.WHITE);
                        view.invalidate();
                        lastValue = false;
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED:{
                        if(BuildConfig.DEBUG){
                        }
                        if(dragEvent.getResult() && lastValue){

                            DraggableButton dragb = (DraggableButton) dragEvent.getLocalState();
                            switch (dragb.getCurrentType()){
                                case TURN:
                                    break;
                                case MOVE_TO:
                                    ListButton newInstance = new ListButton(getApplicationContext());
                                    newInstance.setText(dragb.getCurrentType().toString());
                                    blasMirDochEinen.add(newInstance);
                                    break;
                                case FOR_LOOP:
                                    break;
                                case IF_BRANCHE:
                                    newInstance = new ListButton(getApplicationContext());
                                    ListButton backOfInstance = new ListButton(getApplicationContext());
                                    newInstance.setText(dragb.getCurrentType().toString());
                                    backOfInstance.setText("END_IF");
                                    blasMirDochEinen.add(newInstance);
                                    blasMirDochEinen.add(backOfInstance);
                                    break;
                                case SCAN_ENVIRONMENT:
                                    break;
                                case SCAN_LINE:
                                    break;
                                case TELEPORT:
                                    break;
                                case WHILE_LOOP:
                                    break;
                                default:
                                    try {
                                        throw new SomeThingWentWrongException();
                                    } catch (SomeThingWentWrongException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                            }
                            cbva.notifyDataSetChanged();
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
