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
GameScreen thisScreen;

    public InventoryItem(int myImageName, String myDescription, String myItemName, GameScreen myScreen) throws IOException {
        thisScreen = myScreen;
        itemName = myItemName;
        imageName = myImageName;
        image = generateImage(imageName);
        itemDescription = myDescription;
        isSellable = false;
        goldValue = 100;
    }

    public Bitmap generateImage(int myImageName) throws IOException {
        return thisScreen.generateImage(myImageName);
    }

}
