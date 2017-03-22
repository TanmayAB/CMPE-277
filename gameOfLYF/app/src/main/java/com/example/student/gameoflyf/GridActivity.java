package com.example.student.gameoflyf;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;

/**
 * Created by Tanmay on 2/23/17.
 */

public class GridActivity extends AppCompatActivity {

    private GridGameofLYF mgridGameofLYF;
    private Button mNext_gen;
    private Button mreset;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        int no_of_Grids = getIntent().getIntExtra("no_of_Grids",12);
        mNext_gen = (Button) this.findViewById(R.id.next_gen);
        mreset = (Button) this.findViewById(R.id.reset);
        mgridGameofLYF =  (GridGameofLYF) this.findViewById(R.id.game_grid);
        mgridGameofLYF.setNumColumns(no_of_Grids);
        mgridGameofLYF.setNumRows(no_of_Grids);
        View.OnClickListener listener= new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mgridGameofLYF.startNextGeneration();
            }
        };

        mNext_gen.setOnClickListener(listener);
        View.OnClickListener listener1 = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:
                                mgridGameofLYF.resetGrid();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                            default: break;
                        }

                    }
                };
                @SuppressWarnings("deprecation")
                AlertDialog.Builder builder = new AlertDialog.Builder(GridActivity.this);
                builder.setMessage("Do you want to Reset the game?").setPositiveButton("Yes",dialogListener).setNegativeButton("No",dialogListener).show();
            }
        };
        mreset.setOnClickListener(listener1);
    }
}
