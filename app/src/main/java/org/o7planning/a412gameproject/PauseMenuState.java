package org.o7planning.a412gameproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.io.IOException;


public class PauseMenuState extends GameState {

    public GameStateManager myGSM;
    public GameScreen thisScreen;
    public Bitmap menuImage;
    public Bitmap selector;

    public PauseMenuState(GameScreen myScreen, GameStateManager passedGSM) throws IOException {
        thisScreen = myScreen;
        myGSM = passedGSM;
      //  menuImage = generateImage(R.drawable.pause_menu);
        menuImage = generateImage(R.drawable.staff_mummy);
    }

    public void draw(Canvas canvas) {

        // thisScreen.gbi.drawString(("Presss Enter To return to the Game. Press Z if you would like to exit the game."), 100, 100);
        thisScreen.canvas.drawText("This is the pause menu.", 50, 50, null);
        //thisScreen.gbi.drawImage(menuImage, 0, 0, thisScreen.myBufferedDimension.width, thisScreen.myBufferedDimension.height, null); repopulate this later.
    }

    /*public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER || key == KeyEvent.VK_R || key == KeyEvent.VK_P) {
            myGSM.setState(0); // Go to adventure screen
        }
        if (key == KeyEvent.VK_S) {
            // Spellbook
        }
        if (key == KeyEvent.VK_I) {
            myGSM.setState(1); // Inventory
        }
        if (key == KeyEvent.VK_X) { // lololol it spells out six
            System.exit(0); // Exit
        }
        if (key == KeyEvent.VK_O) {
            myGSM.setState(2); // PLACE. HOLDER. OPTIONS
        }

    }
*/
    public Bitmap generateImage(int myImageName) throws IOException {
        return thisScreen.generateImage(myImageName);
    }



    public void mouseClicked(MotionEvent e) {

    }

}
