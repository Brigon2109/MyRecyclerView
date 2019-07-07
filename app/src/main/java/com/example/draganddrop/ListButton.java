package com.example.draganddrop;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

@SuppressLint("AppCompatCustomView")
public class ListButton extends Button {

    public boolean state = false;

    public void setListener(){
        this.setOnDragListener(new View.OnDragListener(){
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
                            state = true;
                            Log.i("BUTTON","HELP1");
                        }else {

                        }
                    }
                }
                return true;
            }
        });
    }

    public ListButton(Context context) {
        super(context);
        setListener();
    }

    public ListButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setListener();
    }

    public ListButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setListener();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ListButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setListener();
    }
}
