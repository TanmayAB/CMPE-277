package com.example.student.gameoflyf;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example..";
    private Button mStart_game;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assigning view items to variable
        mStart_game = (Button) findViewById(R.id.game_start);
    }

    public void startGame(View view){
        Log.i("Created an intent","aaa");

        Intent intent = new Intent(this, GridActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
       // String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, "my foot");
        startActivity(intent);
        Log.i("Going out","bye");

    }
}
