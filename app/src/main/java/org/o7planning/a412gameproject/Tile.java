package org.o7planning.a412gameproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sasha
 */
public class Tile {

    String myError;
    int x;
    int y;
    int[] imageName;
    Bitmap[] image;
    MapObject[] myContents;
    boolean hasChanged;
GameScreen thisScreen;

    public Tile(int myX, int myY, int floorImageString, GameScreen myScreen) throws IOException {

        thisScreen = myScreen;
        imageName = new int[4];
        image = new Bitmap[4];
        myContents = new MapObject[4];

        imageName[0] = floorImageString;
        //imageName[0] = R.drawable.carpet_1; for testing
        imageName[1] = R.drawable.empty;
imageName[2] = R.drawable.empty;
        imageName[3] = R.drawable.empty;


        image[0] = generateImage(imageName[0]);
        image[1] = generateImage(imageName[0]);
image[2] = null;
image[3] = null;
        x = myX;
        y = myY;
    }

    public void clearAtLayer(int deletionLayer) {
        myContents[deletionLayer] = null;
        imageName[deletionLayer] = R.drawable.empty;
    }

    public Bitmap generateImage(int myImageName) throws IOException {
        return thisScreen.generateImage(myImageName);
    }
}
