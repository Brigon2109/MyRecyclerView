package com.example.draganddrop;

import android.content.ClipData;
import android.graphics.Color;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ButtonDragListener implements View.OnDragListener {

    private boolean lastValue = false;

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        int event = dragEvent.getAction();

        switch (event){
            case DragEvent.ACTION_DRAG_STARTED:
                lastValue = false;
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                lastValue = true;
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                lastValue = false;
                return true;
            case DragEvent.ACTION_DROP:
                ClipData cp = dragEvent.getClipData();
                return true;
            case DragEvent.ACTION_DRAG_ENDED:{
                if(dragEvent.getResult()&& lastValue){
                    Log.i("BUTTON","HELP");
                }else {

                }
            }
        }
        return true;
    }
}
