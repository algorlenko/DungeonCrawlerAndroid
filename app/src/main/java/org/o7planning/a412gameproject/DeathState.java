package org.o7planning.a412gameproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;


import java.io.IOException;


public class DeathState extends GameState {

    public GameStateManager myGSM;
    public GameScreen thisScreen;
    public Bitmap menuImage;
    public Bitmap selector;
    public int selectorX;
    public int selectorY;
    public int menuGap;
    public int selectedItem;
    public int itemCount;

    public DeathState(GameScreen myScreen, GameStateManager passedGSM) throws IOException { // I have simplified the death state immensely for the port.
        thisScreen = myScreen;
        myGSM = passedGSM;
        menuImage = generateImage(R.drawable.empty); //the image is pretty ugly im sorry armeen
       // selector = generateImage(R.drawable.selector);
        selector = generateImage(R.drawable.empty);
        selectorX = 990;
        selectorY = 382;
        menuGap = thisScreen.myBufferedDimension.getHeight() / 8;
        selectedItem = 0;
        itemCount = 2;
    }

    public void draw(Canvas canvas) {
        thisScreen.canvas.drawText("You have Died.", 50, 50, null);
        // thisScreen.gbi.drawString(("Presss Enter To return to the Game. Press Z if you would like to exit the game."), 100, 100);
       // thisScreen.gbi.drawImage(menuImage, 0, 0, thisScreen.myBufferedDimension.width, thisScreen.myBufferedDimension.height, null);
        drawArrow();
    }

    public void drawArrow() {
        // selector below
        if (selectedItem == 1) {
        //    thisScreen.gbi.drawImage(selector, selectorX, selectorY + (menuGap * (selectedItem + 1)), thisScreen.myBufferedDimension.width / 12, thisScreen.myBufferedDimension.height / 12, null);
        }
        if (selectedItem == 0) { //had to fix by brute force but oh well
        //    thisScreen.gbi.drawImage(selector, selectorX, selectorY + (menuGap * selectedItem), thisScreen.myBufferedDimension.width / 12, thisScreen.myBufferedDimension.height / 12, null);
        }
    }

  /*  @Override

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            if (selectedItem == 0) {
                myGSM.resetGame();
                // myGSM.setState(0); // Go to adventure screen (New Game) TODO, make it work 
            }
            if (selectedItem == 1) {
                System.exit(0); // Exit
            }
            // myGSM.setState(0);
        }

        //Shortcuts Below
        if (key == KeyEvent.VK_X) {
            System.exit(0); // Exit
        }

        if (key == KeyEvent.VK_N || key == KeyEvent.VK_ESCAPE) {
            myGSM.resetGame();
            //myGSM.setState(0); // Go to adventure screen (New Game)
        }

        if (key == KeyEvent.VK_DOWN) {
            selectedItem++;
            selectedItem %= itemCount;
            // selectorY += thisScreen.myBufferedDimension.height / 8;
        }

        if (key == KeyEvent.VK_UP) {
            selectedItem = (selectedItem + itemCount - 1) % 2;
        }

    }*/

    public Bitmap generateImage(int myImageName) throws IOException {
        return thisScreen.generateImage(myImageName);
    }

    public void mouseClicked(MotionEvent e) {

    }



}
