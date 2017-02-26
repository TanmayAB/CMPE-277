package com.example.student.gameoflyf;

        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.util.AttributeSet;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.Button;


public class GridGameofLYF extends View {
    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private int totalWidth, totalHeight;
    private Paint myPaint = new Paint();
    private boolean[][]cellChecked;
    private Button mnext_generation;
    private View view;

    public GridGameofLYF(Context context) {
        this(context, null);
        init();
    }

    public void init(){
                this.mnext_generation = (Button) findViewById(R.id.next_gen);
    }

    public GridGameofLYF(Context context, AttributeSet attrs) {
        super(context, attrs);
        myPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }


    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        calculateDimensions();
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
        calculateDimensions();
    }

    public int getNumRows() {
        return numRows;
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        calculateDimensions();
    }
    private void calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return;
        }

        totalWidth = getWidth();
        totalHeight = getWidth();
        cellWidth = totalWidth / numColumns;
        cellHeight = totalHeight / numRows;

        cellChecked = new boolean[numColumns][numRows];

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        if (numColumns < 1 || numRows < 1 ) {
            return;
        }

        int width = totalWidth;
        int height = totalWidth;

        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                if (cellChecked[i][j]) {
                    int circle_x = (i+1) * (totalWidth/numRows) -(totalWidth/numRows)/2 ;
                    int circle_y = (j+1) * (totalHeight/numColumns) - (totalHeight/numColumns)/2;
                    myPaint.setColor(Color.RED);
                    canvas.drawCircle(circle_x,circle_y,(totalHeight/numColumns)/2,myPaint);
                    myPaint.setColor(Color.BLACK);

                }
            }
        }

        for (int i = 1; i < numColumns; i++) {
            canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, myPaint);
        }

        for (int i = 1; i < numRows; i++) {
            canvas.drawLine(0, i * cellHeight, width, i * cellHeight, myPaint);
        }

        canvas.drawLine(0,0,totalWidth,0,myPaint);
        canvas.drawLine(1,0,1,totalWidth,myPaint);
        canvas.drawLine(totalWidth,0,totalWidth,totalWidth,myPaint);
        canvas.drawLine(0,totalWidth,totalWidth,totalWidth,myPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int column = (int) (event.getX() / cellWidth);
            int row = (int) (event.getY() / cellHeight);
            if (row < numRows && column < numColumns) {
                cellChecked[column][row] = !cellChecked[column][row];
                invalidate();
            }
        }

        return true;
    }

    public void startNextGeneration(){
        boolean[][] temp = new boolean[numRows][numColumns];

        int neighbours = 0;
        for(int i=0 ; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                temp[i][j] = cellChecked[i][j];
                neighbours = calculateNeighbours(i, j,numRows);
                if (neighbours < 2 || neighbours > 3) {
                    if (cellChecked[i][j]) {
                        temp[i][j] = false;
                    }
                }
                else if(neighbours == 3) {
                    if (!cellChecked[i][j]) {
                        temp[i][j] = true;
                    }
                }
            }
        }
        cellChecked = temp;
        invalidate();
    }

    public int calculateNeighbours(int i, int j,int grid_size)
    {
        int neighbours =0;
        int temp_m = (i-1) % grid_size;
        if(temp_m < 0)
            temp_m +=grid_size;
        int temp_n = (j-1) % grid_size;
        if(temp_n < 0)
            temp_n +=grid_size;

        for (int m = temp_m; m !=(i + 2) % grid_size; m = (m +1)%grid_size) {
            for (int n = temp_n; n != (j + 2) % grid_size; n = (n+1)%grid_size) {
                    if (!(m == i && n == j)) {
                    if (cellChecked[m][n]) {
                        neighbours++;
                    }
                }
            }
        }
        return neighbours;
    }

    public void resetGrid(){
        cellChecked = new boolean[numRows][numColumns];
        invalidate();
    }
}

