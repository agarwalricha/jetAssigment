package com.example.assignment.view.utils;



import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment.R;

public class ErrorBaseActivity extends AppCompatActivity {

    public Dialog dialog;
    private TextView title, note;
    private Button close_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    public void initialiseDialog(String titleString) {
        if (null == dialog) {
            dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(R.layout.layout_error_dialog);
            dialog.setCancelable(false);
            title = dialog.findViewById(R.id.title);
            note = dialog.findViewById(R.id.note);
            close_btn = dialog.findViewById(R.id.close_btn);

            Rect displayRectangle = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
            close_btn.setWidth((int) (displayRectangle.width() * 0.8f));
        }
        String titleText = titleString.isEmpty() ? getString(R.string.speedy_articles) : titleString;
        title.setText(titleText);
    }

    public void showErrorDialog(final AppCompatActivity activity, String titleString, String msg) {
        initialiseDialog(titleString);
        note.setText(msg);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                activity.finish();
            }
        });
        dialog.show();
    }
}
