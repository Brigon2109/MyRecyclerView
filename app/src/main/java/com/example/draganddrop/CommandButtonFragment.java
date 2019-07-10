package com.example.draganddrop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CommandButtonFragment extends Fragment {

    static ArrayList<ListButton> listButtons = new ArrayList<>();
    static CommandButtonViewAdapter cbva = new CommandButtonViewAdapter(listButtons);

    private OnFragmentInteractionListener mListener;

    public CommandButtonFragment() {
        // Required empty public constructor
    }

    public static CommandButtonFragment newInstance(String param1, String param2) {
        CommandButtonFragment fragment = new CommandButtonFragment();
        Bundle args = new Bundle();


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.teaml_fragment_command_button, container, false);

        DraggableButton db1 = view.findViewById(R.id.movetoButton);
        DraggableButton db2 = view.findViewById(R.id.ifButton);
        DraggableButton db3 = view.findViewById(R.id.whileButton);
        DraggableButton db4 = view.findViewById(R.id.scanlineButton);
        DraggableButton db5 = view.findViewById(R.id.scanenvButton);
        DraggableButton db6 = view.findViewById(R.id.teleportButton);

        db1.setCurrentType(DraggableButton.CommandType.MOVE_TO);
        db2.setCurrentType(DraggableButton.CommandType.IF_BRANCHE);
        db3.setCurrentType(DraggableButton.CommandType.WHILE_LOOP);
        db4.setCurrentType(DraggableButton.CommandType.SCAN_LINE);
        db5.setCurrentType(DraggableButton.CommandType.SCAN_ENVIRONMENT);
        db6.setCurrentType(DraggableButton.CommandType.TELEPORT);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView rv = view.findViewById(R.id.DragSurface);

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
            boolean lastValue = false;

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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
                        if(dragEvent.getResult() && lastValue){
                            DraggableButton dragb = (DraggableButton) dragEvent.getLocalState();
                            switch (dragb.getCurrentType()){
                                case TURN:
                                    break;
                                case MOVE_TO:
                                    ListButton newInstance = new ListButton(view.getContext(), DraggableButton.CommandType.MOVE_TO);
                                    newInstance.setBackground(dragb.getBackground());
                                    newInstance.setText("MOVE TO");
                                    listButtons.add(newInstance);
                                    break;
                                case FOR_LOOP:
                                    newInstance = new ListButton(view.getContext(), DraggableButton.CommandType.FOR_LOOP);
                                    ListButton backOfInstance = new ListButton(view.getContext(), DraggableButton.CommandType.END_FOR);
                                    newInstance.setBackground(dragb.getBackground());
                                    backOfInstance.setBackground(dragb.getBackground());
                                    newInstance.setText("FOR LOOP");
                                    backOfInstance.setText("END FOR");
                                    listButtons.add(newInstance);
                                    listButtons.add(backOfInstance);
                                    break;
                                case IF_BRANCHE:
                                    newInstance = new ListButton(view.getContext(), DraggableButton.CommandType.IF_BRANCHE);
                                    backOfInstance = new ListButton(view.getContext(), DraggableButton.CommandType.END_IF);
                                    newInstance.setBackground(dragb.getBackground());
                                    backOfInstance.setBackground(dragb.getBackground());
                                    newInstance.setText("IF BRANCH");
                                    backOfInstance.setText("END IF");
                                    listButtons.add(newInstance);
                                    listButtons.add(backOfInstance);
                                    break;
                                case SCAN_ENVIRONMENT:
                                    newInstance = new ListButton(view.getContext(), DraggableButton.CommandType.SCAN_ENVIRONMENT);
                                    newInstance.setText("SCAN ENVIRONMENT");
                                    newInstance.setBackground(dragb.getBackground());
                                    listButtons.add(newInstance);
                                    break;
                                case SCAN_LINE:
                                    newInstance = new ListButton(view.getContext(), DraggableButton.CommandType.SCAN_LINE);
                                    newInstance.setText("SCAN LINE");
                                    newInstance.setBackground(dragb.getBackground());
                                    listButtons.add(newInstance);
                                    break;
                                case TELEPORT:
                                    newInstance = new ListButton(view.getContext(), DraggableButton.CommandType.TELEPORT);
                                    newInstance.setText("TELEPORT");
                                    newInstance.setBackground(dragb.getBackground());
                                    listButtons.add(newInstance);
                                    break;
                                case WHILE_LOOP:
                                    newInstance = new ListButton(view.getContext(), DraggableButton.CommandType.WHILE_LOOP);
                                    backOfInstance = new ListButton(view.getContext(), DraggableButton.CommandType.END_WHILE);
                                    newInstance.setBackground(dragb.getBackground());
                                    backOfInstance.setBackground(dragb.getBackground());
                                    newInstance.setText("WHILE");
                                    backOfInstance.setText("END WHILE");
                                    listButtons.add(newInstance);
                                    listButtons.add(backOfInstance);
                                    break;
                                case END_IF:
                                    try {
                                        throw new SomeThingWentWrongException();
                                    } catch (SomeThingWentWrongException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case END_FOR:
                                    try {
                                        throw new SomeThingWentWrongException();
                                    } catch (SomeThingWentWrongException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case END_WHILE:
                                    try {
                                        throw new SomeThingWentWrongException();
                                    } catch (SomeThingWentWrongException e) {
                                        e.printStackTrace();
                                    }
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
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


    private void moveItem(int oldPos, int newPos) {
        ListButton dba = listButtons.get(oldPos);
        listButtons.remove(oldPos);

        listButtons.add(newPos, dba);
        cbva.notifyItemMoved(oldPos, newPos);

    }

    private void deleteItem(int oldPos) {
        listButtons.remove(oldPos);
        cbva.notifyItemRemoved(oldPos);
    }

}
