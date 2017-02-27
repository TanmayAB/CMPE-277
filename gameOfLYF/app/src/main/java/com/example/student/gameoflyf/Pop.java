package com.example.student.gameoflyf;

import android.app.Activity;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Student on 2/26/17.
 */

public class Pop extends Activity {

    private TextView close;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = (int)(dm.widthPixels);
        int height = (int) (dm.heightPixels);
        getWindow().setLayout(width,height);
        close = (TextView) findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
