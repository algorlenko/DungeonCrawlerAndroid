package org.o7planning.a412gameproject;

import android.content.Context;
import android.support.annotation.Dimension;
import android.util.Size;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.Timer;


import android.graphics.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import java.io.IOException;
import java.util.*;
import java.util.List;


public class GameScreen extends SurfaceView implements SurfaceHolder.Callback{

    Tile[][] currentTiles;
    int dungeonRows;
    int dungeonColumns;
    public Timer timer;
    public final int DELAY = 1000 / 10;
    GameEngine engine;
    StatusScreen myStatus;
    Size myBufferedDimension;
    GameStateManager myGSM;


    GameThread gameThread;

    // Image Stuff
Canvas canvas;

    public GameScreen(Context context) throws IOException {


        super(context);

        // Make Game Surface focusable so it can handle events. .
        this.setFocusable(true);

        // Sét callback.
        this.getHolder().addCallback(this);
        initScreen();
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }







    private void initScreen() throws IOException {
        myBufferedDimension = new Size(1920, 1080);
        myStatus = new StatusScreen();
        //engine = new GameEngine(this);
        //currentTiles = engine.myTiles;
        this.setFocusable(true);

        canvas = new Canvas();
     //   buffImage = new BufferedImage(myBufferedDimension.width, myBufferedDimension.height, BufferedImage.TYPE_INT_RGB);
       // gbi = buffImage.createGraphics();

        myGSM = new GameStateManager(this);
        this.gameThread = new GameThread(this,this.getHolder());
        this.gameThread.setRunning(true);
        this.gameThread.start();
        //this.setSize(1,1);
      //  draw(canvas);
  //      timer = new Timer();
       // timer.setRepeats(true);
        //timer.start();
    }




//draw instead of paintComponent
    @Override
    public void draw(Canvas canvas)  {

 //      canvas.drawColor(Color.BLUE);
        super.draw(canvas);
     //   canvas.drawColor(Color.BLUE);

    }





    /*
    @Override
    public void actionPerformed(ActionEvent e) {
        //myGSM.draw(Canvas canvas);
        repaint();
    }
*/

    /*private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            myGSM.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            myGSM.keyPressed(e);
        }

        //   @Override
        //   public void mousePressed(MouseEvent e)
        // {
        // engine.mousePressed(e); 
        //}
    }*/


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        myGSM.mouseClicked(event);
        return true;
    }





    public Bitmap generateImage(int myID)
    {
        return BitmapFactory.decodeResource(this.getResources(), myID);
    }


}
