package com.example.draganddrop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.RequiresApi;

@SuppressLint("AppCompatCustomView")
public class ListButton extends Button {

    private DraggableButton.CommandType currentType;

    public ListButton(Context context, DraggableButton.CommandType currentType) {
        super(context);
        this.currentType = currentType;
    }

    public ListButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ListButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
