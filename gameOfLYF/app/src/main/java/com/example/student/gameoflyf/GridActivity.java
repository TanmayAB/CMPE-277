package com.example.student.gameoflyf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Student on 2/23/17.
 */

public class GridActivity extends AppCompatActivity {

    private GridGameofLYF mgridGameofLYF;
    private Button mNext_gen;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        int no_of_Grids = getIntent().getIntExtra("no_of_Grids",12);
        mNext_gen = (Button) this.findViewById(R.id.next_gen);
        mgridGameofLYF =  (GridGameofLYF) this.findViewById(R.id.game_grid);
        mgridGameofLYF.setNumColumns(no_of_Grids);
        mgridGameofLYF.setNumRows(no_of_Grids);
        View.OnClickListener listener= new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("Clicked button","!!");
                mgridGameofLYF.startNextGeneration();
            }
        };

        mNext_gen.setOnClickListener(listener);
    }
}
