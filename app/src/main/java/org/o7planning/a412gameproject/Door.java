package org.o7planning.a412gameproject;

import android.graphics.Bitmap;

import java.io.IOException;

public class Door extends MapObject implements Useable {

    boolean isOpen;
    Bitmap openImage;
    int openImageString;
    Bitmap closedImage;
    int closedImageString;

    public Door(int myX, int myY, GameEngine passedEngine) throws IOException {
        myEngine = passedEngine;
        isOpen = false;
        myLayer = 3;
        closedImageString = R.drawable.runed_door;
        closedImage = generateImage(closedImageString);

        openImageString = R.drawable.open_door;
        openImage = generateImage(openImageString);

        unitImage = closedImageString;
        image = closedImage;
        x = myX;
        y = myY;
        loadIntoTile(x, y);
    }

    public int tryUse() {
        if (isOpen == false) {
            return tryOpen();
        } else {
            return 2;
        }
    }

    public int tryOpen() {
        if (isOpen == false) {
            if (myEngine.myHero.myInventory.searchFor("L1Key") != -1) {
                isOpen = true;
                unitImage = openImageString;
                image = openImage;
                loadIntoTile(x, y);
                int removedItem;
                removedItem = myEngine.myHero.myInventory.searchFor("L1Key");
                myEngine.myHero.myInventory.clearSlot(removedItem);
                myEngine.myStatus.pushMessage("You have unlocked the door using your key.");
                return 1;
            }
            myEngine.myStatus.pushMessage("You do not have the key.");
            return -1;
        }
        myEngine.myStatus.pushMessage("The Door is already open.");
        return -1;
    }
}
