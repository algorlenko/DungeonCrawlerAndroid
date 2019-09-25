package org.o7planning.a412gameproject;

import android.graphics.Bitmap;

public class MapObject {

    int unitImage;
    int x;
    int y;
    int myLayer;
    Tile currentTile;
    Bitmap image;
    GameEngine myEngine;
    int numberOfImages;
    int currentImageNumber;
    Bitmap[] images;

    public MapObject(){

    }

    public MapObject(Tile currentTile, int layer){
        this.currentImageNumber = 0;
        numberOfImages = 1;
        this.currentTile = currentTile;
        images = new Bitmap[1];
        this.myEngine = currentTile.myEngine;
        myLayer = layer;
        currentTile.myContents[myLayer]=this;
        x = currentTile.x;
        y = currentTile.y;
    }

    public MapObject(Tile currentTile, int layer, int numberOfImages, String baseImageName){
        this.numberOfImages = numberOfImages;
        this.currentImageNumber = 0;
        this.currentTile = currentTile;
        images = new Bitmap[numberOfImages];
        this.myEngine = currentTile.myEngine;
        for (int i = 0; i < numberOfImages; i++) {
            images[i] = myEngine.myGSM.allImages.get(baseImageName+i);
        }
        myLayer = layer;
        currentTile.myContents[myLayer]=this;
        x = currentTile.x;
        y = currentTile.y;
    }


    public void loadIntoTile(int myX, int myY) {
myEngine.myTiles[myX][myY].imageName[myLayer] = unitImage; // I added this and it may cause problems
        myEngine.myTiles[myX][myY].myContents[myLayer] = this;
        myEngine.myTiles[myX][myY].image[myLayer] = image;
        // used to be that myTiles was passed
    }

    public Boolean loadIntoTile(Tile tileToLoad){
        currentTile = tileToLoad;
        tileToLoad.myContents[myLayer]=this;
        return true;
    }

    public void removeFromTile(){
        if(currentTile != null){
            currentTile.myContents[myLayer] = null;
            currentTile = null;
        }
    }

    public void moveToTile(Tile newTile){
        Tile oldTile = currentTile;
        currentTile = newTile;
        oldTile.myContents[myLayer] = null;
        currentTile.myContents[myLayer] = this;
        x = newTile.x;
        y = newTile.y;
    }

    public Bitmap generateImage(int myImageName){
        return myEngine.thisScreen.generateImage(myImageName);
        //used to be thisScreen
    }

    public Bitmap getCurrentImage(){
        currentImageNumber = (currentImageNumber + 1) % numberOfImages;
        return images[currentImageNumber];

    }

}
