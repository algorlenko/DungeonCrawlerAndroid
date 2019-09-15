package org.o7planning.a412gameproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;



public class StatusScreen {

    String message1;
    String message2;
    String message3;
Rect myRectangle;
    Paint myPaint;
    GameEngine myEngine;
    public StatusScreen(GameEngine passedEngine) {
        message1 = " ";
        message2 = " ";
        message3 = " ";
        myRectangle = new Rect();
       myPaint = new Paint();
       myEngine = passedEngine;
    }

    public void pushMessage(String pushedMessage) {
        message3 = message2;
        message2 = message1;
        message1 = pushedMessage;
    }

    public void drawStatus() {
        //   myGraphic.setFont(StatusFont); this only needs to be done once
        // myGraphic.setPaint(new Color(255,255,255));

        //Canvas myGraphic, Bitmap statusImage, int myWidth, int myHeight, GameScreen thisScreen, Hero myHero These are the old parameters

        Canvas myGraphic = myEngine.myGraphic;
        int myHeight = (myGraphic.getHeight() / 5) * 4;
        int myWidth = myEngine.myGraphic.getWidth();
        Bitmap statusImage = myEngine.statusImage;
        Hero myHero = myEngine.myHero;


        myPaint.setColor(Color.WHITE);
        myRectangle.set(0, myHeight, myWidth, myGraphic.getHeight());
        myGraphic.drawBitmap(statusImage,null, myRectangle, null);
        myGraphic.drawText(message3, 50, myHeight + (myGraphic.getHeight() - myHeight) / 4, myPaint);
        myGraphic.drawText(message2, 50, myHeight + (myGraphic.getHeight() - myHeight) / 2, myPaint);
        myGraphic.drawText(message1, 50, myHeight + (myGraphic.getHeight() - myHeight) * 3 / 4, myPaint);

        myGraphic.drawText("Your Current Gold is: " + myHero.goldCoins, 1000, myHeight + (myGraphic.getHeight() - myHeight) / 2, myPaint);
        /*if (myMonsters.size() != 0) {
            myGraphic.drawString("Your current Hp is: " + myHero.hp + "\nThe Monster's Hp is: " + myMonsters.get(0).hp, 50, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 4);
        } else {
            myGraphic.drawString("Your current Hp is: " + myHero.hp + "\nAll monsters on this floor are dead.", 50, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 4);
        }*/
        // myGraphic.drawString("the Current Frame is: " + heroFrame, 200, 50);
        

        myPaint.setColor(Color.BLUE);
        myRectangle.set((myGraphic.getWidth() / 4) * 3, myHeight + (myGraphic.getHeight() - myHeight) / 2 - 15,(myGraphic.getWidth() / 4) * 3 + myHero.mana, myHeight + (myGraphic.getHeight() - myHeight) / 2 + 5);
        myGraphic.drawRect(myRectangle, myPaint);
        myGraphic.drawText("Mana: " + myHero.mana + " / " + myHero.maxMana, (myGraphic.getWidth() / 4) * 3, myHeight + (myGraphic.getHeight() - myHeight) / 3, myPaint);//for displaying mana
        if (myHero.hp <= (myHero.maxHP / 3) * 2 && myHero.hp > (100 / 3)) { // NO HARDCODED NUMBERS >:( I have dehardcoded some but not all of it
            myPaint.setColor(Color.YELLOW);
        }
        if (myHero.hp < (myHero.maxHP / 3) && myHero.hp >= 0) {
            myPaint.setColor(Color.RED);
        }
        if (myHero.hp > (myHero.maxHP / 3) * 2) {
            myPaint.setColor(Color.GREEN);
            // myGraphic.fillRect((thisScreen.myBufferedDimension.width / 4) * 3+110, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2 -20, myHero.hp, 20);
        }
        myRectangle.set((myGraphic.getWidth() / 4) * 3, myHeight + (myGraphic.getHeight() - myHeight) / 2 - 20,(myGraphic.getWidth() / 4) * 3 + myHero.hp, myHeight + (myGraphic.getHeight() - myHeight) / 2 - 40);
        myGraphic.drawRect(myRectangle, myPaint); // draws the HP bar
        
        
        
        myGraphic.drawText("HP: " + myHero.hp + " / " + myHero.maxHP, (myGraphic.getWidth() / 4) * 3, myHeight + (myGraphic.getHeight() - myHeight) / 4, myPaint);//for displaying hp
        myPaint.setColor(Color.WHITE);
        // used to globally set all text to white.
    }

    public void print() {

    }

}
