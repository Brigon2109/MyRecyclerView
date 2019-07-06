package com.example.draganddrop;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Color;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ViewDragListener extends Fragment implements View.OnDragListener {
    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        int event = dragEvent.getAction();

        switch (event){
            case DragEvent.ACTION_DRAG_STARTED:
                if(BuildConfig.DEBUG){
                    Log.i("DRAG","START");
                }
                view.setBackgroundColor(Color.WHITE);
                view.invalidate();
                return true;
            case DragEvent.ACTION_DRAG_ENTERED:
                if(BuildConfig.DEBUG){
                    Log.i("DRAG","ENTERED");
                }
                view.setBackgroundColor(Color.BLUE);
                view.invalidate();
                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                if(BuildConfig.DEBUG){
                    Log.i("DRAG","LOCATION X:Y " + dragEvent.getX() + ":" + dragEvent.getY());
                }
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                if(BuildConfig.DEBUG){
                    Log.i("DRAG","EXITED");
                }
                view.setBackgroundColor(Color.WHITE);
                view.invalidate();
                return true;
            case DragEvent.ACTION_DROP:
                if(BuildConfig.DEBUG){
                    Log.i("DRAG","DROP");
                }
                ClipData cp = dragEvent.getClipData();
                return true;
            case DragEvent.ACTION_DRAG_ENDED:{
                if(BuildConfig.DEBUG){
                    Log.i("DRAG","ENDED WITH " + dragEvent.getResult());
                }
                if(dragEvent.getResult()){
                    DraggableButton newInstance = new DraggableButton(getActivity());
                    LinearLayout container = (LinearLayout) view;
                    container.addView(newInstance);

                    newInstance.setVisibility(View.VISIBLE);
                    newInstance.setText(R.string.DragButton, TextView.BufferType.NORMAL);
                    newInstance.setTag(R.string.ButtonTag);

                    view.setBackgroundColor(Color.GREEN);
                    view.invalidate();
                }else {
                    view.setBackgroundColor(Color.RED);
                    view.invalidate();
                }
            }
        }
        return true;
    }
}