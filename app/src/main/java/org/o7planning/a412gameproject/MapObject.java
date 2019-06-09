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
    GameScreen thisScreen;

    public void loadIntoTile(int myX, int myY, Tile[][] myTiles) {

        myTiles[myX][myY].imageName[myLayer] = unitImage;
        myTiles[myX][myY].myContents[myLayer] = this;
        myTiles[myX][myY].image[myLayer] = image;
        //    myTiles[myX][myY].syncTileWithScreen();
    }

    public Bitmap generateImage(int myImageName) throws IOException {
        return thisScreen.generateImage(myImageName);
    }

}
