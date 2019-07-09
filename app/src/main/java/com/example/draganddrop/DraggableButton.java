package com.example.draganddrop;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

@SuppressLint("AppCompatCustomView")
public class DraggableButton extends Button {

    public enum CommandType{
        IF_BRANCHE, SCAN_LINE, SCAN_ENVIRONMENT,
        MOVE_TO, FOR_LOOP, WHILE_LOOP, TELEPORT,
        TURN
    }

    private CommandType currentType;

    public DraggableButton(Context context) {
        super(context);
        setListeners();
    }

    public void setListeners() {
        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);

                data.addItem(item);

                DragShadowBuilder shadowBuilder = new DragShadowBuilder(view);

                view.startDrag(data,shadowBuilder,view, 0);

                view.setVisibility(View.VISIBLE);
                return true;
            }
        });
    }

    public DraggableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setListeners();
    }

    public DraggableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setListeners();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DraggableButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setListeners();
    }

    public CommandType getCurrentType(){
        return currentType;
    }

    public void setCurrentType(CommandType commandType){
        this.currentType = commandType;
    }
}
