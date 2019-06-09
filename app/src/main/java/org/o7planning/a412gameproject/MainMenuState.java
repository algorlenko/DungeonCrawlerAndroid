package org.o7planning.a412gameproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;


import java.io.IOException;


public class MainMenuState extends GameState {

    public GameStateManager myGSM;
    public GameScreen thisScreen;
    public Bitmap menuImage;
    public Bitmap selector;
    public int selectorX;
    public int selectorY;
    public int menuGap;
    public int selectedItem;
    public int itemCount;

    public MainMenuState(GameScreen myScreen, GameStateManager passedGSM) throws IOException {
        thisScreen = myScreen;
        myGSM = passedGSM;
      //  menuImage = generateImage(R.drawable.main_menu_placeholder3);
        menuImage = generateImage(R.drawable.staff_mummy);
     //   selector = generateImage(R.drawable.selector);
        selector = generateImage(R.drawable.empty);
        selectorX = 990;
        selectorY = 382;
        menuGap = thisScreen.myBufferedDimension.getHeight() / 8;
        selectedItem = 0;
        itemCount = 3;
    }

    public void draw(Canvas canvas) {

        thisScreen.canvas.drawText("This is the Main Menu", 50, 50, null);
        // thisScreen.gbi.drawString(("Presss Enter To return to the Game. Press Z if you would like to exit the game."), 100, 100);
     //   thisScreen.gbi.drawImage(menuImage, 0, 0, thisScreen.myBufferedDimension.width, thisScreen.myBufferedDimension.height, null); repopulate this soon
        drawArrow();
    }

    public void drawArrow() {
        // selector below repopulate this
      //  thisScreen.gbi.drawImage(selector, selectorX, selectorY + (menuGap * selectedItem), thisScreen.myBufferedDimension.width / 12, thisScreen.myBufferedDimension.height / 12, null);
    }


   /* @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            if (selectedItem == 0) {
                myGSM.setState(0); // Go to adventure screen (New Game)
            }
            if (selectedItem == 1) {
                myGSM.setState(1); // Inventory (will be Load Game)
            }
            if (selectedItem == 2) {
                System.exit(0); // Exit
            }
            // myGSM.setState(0);
        }

        //Shortcuts Below
        if (key == KeyEvent.VK_X) {
            System.exit(0); // Exit
        }

        if (key == KeyEvent.VK_L) {
            myGSM.setState(1); // Inventory
        }

        if (key == KeyEvent.VK_N || key == KeyEvent.VK_ESCAPE) {
            myGSM.setState(0); // Go to adventure screen (New Game)
        }

        if (key == KeyEvent.VK_DOWN) {
            selectedItem++;
            selectedItem %= itemCount;
// selectorY += thisScreen.myBufferedDimension.height / 8;
        }
        if (key == KeyEvent.VK_UP) {
            selectedItem = (selectedItem + itemCount - 1) % 3;
        }

    }
*/
   public Bitmap generateImage(int myImageName) throws IOException {
       return thisScreen.generateImage(myImageName);
   }



    public void mouseClicked(MotionEvent e) {

    }


}
