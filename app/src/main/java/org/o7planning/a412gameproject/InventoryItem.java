package org.o7planning.a412gameproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.IOException;

public class InventoryItem {

    int imageName;
    Bitmap image;
    String itemName;
    String itemDescription;
    boolean isSellable;
    long goldValue;
GameEngine myEngine;

    public InventoryItem(int myImageName, String myDescription, String myItemName, GameEngine passedEngine) {
        myEngine = passedEngine;
        itemName = myItemName;
        imageName = myImageName;
        image = generateImage(imageName);
        itemDescription = myDescription;
        isSellable = false;
        goldValue = 100;
    }

    public Bitmap generateImage(int myImageName) {
        return myEngine.thisScreen.generateImage(myImageName);
    }

}
