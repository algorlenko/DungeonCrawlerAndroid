package org.o7planning.a412gameproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;


public class MapObject {

    int unitImage;
    int x;
    int y;
    int myLayer;
    Bitmap image;
    GameEngine myEngine;

    public void loadIntoTile(int myX, int myY) {
myEngine.myTiles[myX][myY].imageName[myLayer] = unitImage; // I added this and it may cause problems
        myEngine.myTiles[myX][myY].myContents[myLayer] = this;
        myEngine.myTiles[myX][myY].image[myLayer] = image;
        // used to be that myTiles was passed
    }

    public Bitmap generateImage(int myImageName){
        return myEngine.thisScreen.generateImage(myImageName);
        //used to be thisScreen
    }

}
