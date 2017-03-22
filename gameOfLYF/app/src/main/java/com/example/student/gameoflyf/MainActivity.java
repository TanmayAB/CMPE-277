package com.example.student.gameoflyf;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mStart_game;
    private Spinner mspinner_grid;
    private TextView mRule;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assigning view items to variable
        mspinner_grid = (Spinner) findViewById(R.id.spinner_grid);
        mStart_game = (Button) findViewById(R.id.game_start);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Grids_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mspinner_grid.setAdapter(adapter);
        int position = adapter.getPosition("12");
        mspinner_grid.setSelection(position);

        mRule = (TextView) findViewById(R.id.rules);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Pop.class));
            }
        };

        mRule.setOnClickListener(listener);
    }

    public void startGame(View view){

        int selected_item = Integer.parseInt((String)mspinner_grid.getSelectedItem());
        Intent intent = new Intent(this, GridActivity.class);
        intent.putExtra("no_of_Grids",selected_item);
        startActivity(intent);
    }
}
