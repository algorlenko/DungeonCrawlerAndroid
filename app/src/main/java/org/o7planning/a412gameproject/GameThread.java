package org.o7planning.a412gameproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private boolean running;
    private GameScreen gameScreen;
    private SurfaceHolder surfaceHolder;

    public GameThread(GameScreen gameScreen, SurfaceHolder surfaceHolder)  {
        this.gameScreen= gameScreen;
        this.surfaceHolder= surfaceHolder;
    }

    @Override
    public void run()  {
        long startTime = System.nanoTime();

        while(running)  {
            Canvas canvas= null;
            try {
                // Get Canvas from Holder and lock it. OHHHHH this provides the canvas that you need to draw on.
                canvas = this.surfaceHolder.lockCanvas();

                // Synchronized
                synchronized (canvas)  {
     //               this.gameScreen.myGSM.draw(Canvas canvas);

                    this.gameScreen.draw(canvas);
                    gameScreen.myGSM.draw(canvas);
                }
            }catch(Exception e)  {
                // Do nothing.
            } finally {
                if(canvas!= null)  {
                    // Unlock Canvas. OHHHHHH this is what actually displays stuff to the screen.
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            long now = System.nanoTime() ;
            // Interval to redraw game
            // (Change nanoseconds to milliseconds)
            long waitTime = (now - startTime)/1000000;
            if(waitTime < 50)  {
                waitTime= 50; // Millisecond.
            }
//            System.out.print(" Wait Time="+ waitTime);

            try {
                // Sleep.
                this.sleep(waitTime);
            } catch(InterruptedException e)  {

            }
            startTime = System.nanoTime();
            System.out.print(".");
        }
    }

    public void setRunning(boolean running)  {
        this.running= running;
    }
}
