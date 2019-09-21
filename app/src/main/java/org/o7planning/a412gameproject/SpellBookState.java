package org.o7planning.a412gameproject;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.io.IOException;


import java.lang.Math;

public class SpellBookState extends GameState {

    public GameStateManager myGSM;
    public GameScreen thisScreen;
    public Equipment myTestItem;

    public Bitmap menuImage;
    public Inventory heroInventory;
    public Hero myHero;
    public int rows;
    public int columns;
    public int hoveredSlot;
    public int selectedSlot;
    public final int HELMET = 0;
    public final int TALISMAN = 1;
    public final int ARMOR = 2;
    public final int WEAPON = 3;
    public final int OFFHAND = 4;

    public final int BOOTS = 5;

    public Spell teleportSpell;
    public Spell arcaneBlastSpell;
    public Spell raiseSkeletonSpell;
    public GameEngine myEngine;
    public int hoveredSpell;
    public Rect myRectangle;
    int myHeight;
    int myWidth;


    public SpellBookState(GameScreen myScreen, GameStateManager passedGSM, GameEngine passedEngine) throws IOException {
        thisScreen = myScreen;
        myGSM = passedGSM;
        hoveredSpell = 0;
        myHero = myGSM.myHero;
        heroInventory = myHero.myInventory;
        rows = (int) Math.sqrt(heroInventory.storageSpace);
        columns = rows;
        hoveredSlot = -1;
        menuImage = generateImage(R.drawable.bookcropped);
        //menuImage = generateImage(R.drawable.staff_mummy);
        selectedSlot = -1;
        myEngine = passedEngine;
        myRectangle = new Rect(); //TODO could probably have one recyclable rectangle for all in GSM
        teleportSpell = new Spell(myEngine, "Teleport", R.drawable.controlled_blink, 30);
        arcaneBlastSpell = new Spell(myEngine, "Arcane Blast", R.drawable.force_lance, 20);
        raiseSkeletonSpell = new Spell(myEngine, "Raise Skeleton", R.drawable.animate_dead, 50);
    }

    public void draw(Canvas canvas) {
        //thisScreen.gbi.drawImage(myTestItem.image, 50, 50, null);
        //if (DeleteThisTestVariable == 1) {
        //   thisScreen.gbi.drawString(myTestItem.itemDescription, 50, 50);
        //  thisScreen.gbi.drawString(("This item is Level " + myTestItem.powerLevel), 50, 40);
        //}

        myHeight = canvas.getHeight();
        myWidth = canvas.getWidth() / 2;
        myRectangle.set(0,0, myWidth * 2, myHeight * 1);
        canvas.drawBitmap(menuImage, null, myRectangle, null);
        drawSpells(canvas);
        //drawDescription(myWidth, myHeight);
    }

    public void drawSpells(Canvas canvas) {
        myRectangle.set(100, 100, myWidth/3+100, myHeight/3+100);
       canvas.drawBitmap(teleportSpell.spellImage, null, myRectangle, null);
        myRectangle.set(myWidth, 100, myWidth * 4 /3, myHeight/3+100);
        canvas.drawBitmap(arcaneBlastSpell.spellImage, null, myRectangle, null);
        myRectangle.set(100,  myHeight / 2, myWidth/3+ 100, myHeight/3 + myHeight/2);
        canvas.drawBitmap(raiseSkeletonSpell.spellImage, null, myRectangle, null); //(100, 100) startpoint
    }

    public void drawDescription(int myWidth, int myHeight) {
        Spell actuallyHoveredSpell;
        if (hoveredSpell == 1) {
            actuallyHoveredSpell = teleportSpell;
         //   thisScreen.gbi.drawString(actuallyHoveredSpell.spellName + ", Mana Cost: " + actuallyHoveredSpell.manaCost, 100, 100);
        }
        if (hoveredSpell == 2) {
            actuallyHoveredSpell = arcaneBlastSpell;
          //  thisScreen.gbi.drawString(actuallyHoveredSpell.spellName + ", Mana Cost: " + actuallyHoveredSpell.manaCost, 1000, 100);
        }
        if (hoveredSpell == 3) {
            actuallyHoveredSpell = raiseSkeletonSpell;
          //  thisScreen.gbi.drawString(actuallyHoveredSpell.spellName + ", Mana Cost: " + actuallyHoveredSpell.manaCost, 100, myHeight / 2);
        }
    }

   /* public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {

            myGSM.setState(0); // Back to adventure screen

        }

        if (key == KeyEvent.VK_1) {
            myEngine.selectedSpell = teleportSpell; // or this could be myHero.selectedSpell
            thisScreen.changeCursor(myEngine.selectedSpell.spellImage);
            myGSM.setState(0); // Back to adventure screen

        }
        if (key == KeyEvent.VK_2) {
            myEngine.selectedSpell = arcaneBlastSpell; // or this could be myHero.selectedSpell
            thisScreen.changeCursor(myEngine.selectedSpell.spellImage);
            myGSM.setState(0); // Back to adventure screen

        }
        if (key == KeyEvent.VK_3) {
            myEngine.selectedSpell = raiseSkeletonSpell; // or this could be myHero.selectedSpell
            thisScreen.changeCursor(myEngine.selectedSpell.spellImage);
            myGSM.setState(0); // Back to adventure screen

        }

    }*/



 /*   public void mouseMoved(MouseEvent e) {
        // hoveredSlot = calculateSlot(e.getX(), e.getY());
        hoveredSpell = calculateSpell(e.getX(), e.getY());

    }
*/

    public int calculateSpell(int x, int y) {
        if (x > myWidth) {
            return 2;
        } else if (y > myHeight / 2) {
            return 3;
        } else {
            return 1;
        }
    }

    public void mouseClicked(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            int chosenSpell = calculateSpell((int) e.getX(), (int) e.getY());
            if (chosenSpell == 1 && myHero.mana >= 30) {
                myEngine.selectedSpell = teleportSpell; // or this could be myHero.selectedSpell
                //thisScreen.changeCursor(myEngine.selectedSpell.spellImage);
                myGSM.setState(0); // Back to adventure screen
            } else if (chosenSpell == 2 && myHero.mana >= 20) {
                myEngine.selectedSpell = arcaneBlastSpell; // or this could be myHero.selectedSpell
                // thisScreen.changeCursor(myEngine.selectedSpell.spellImage);
                myGSM.setState(0); // Back to adventure screen
            } else if (chosenSpell == 3 && myHero.mana >= 50) {
                myEngine.selectedSpell = raiseSkeletonSpell; // or this could be myHero.selectedSpell
                // thisScreen.changeCursor(myEngine.selectedSpell.spellImage);
                myGSM.setState(0); // Back to adventure screen

            }
        }
    }


    public Bitmap generateImage(int myImageName) throws IOException {
        return thisScreen.generateImage(myImageName);
    }

}
