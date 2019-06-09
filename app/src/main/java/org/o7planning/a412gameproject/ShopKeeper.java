package org.o7planning.a412gameproject;


import java.io.IOException;

public class ShopKeeper extends MapObject implements Useable {

//    boolean isOpen;
GameEngine myEngine;

    public ShopKeeper(int myX, int myY, GameEngine passedEngine) throws IOException {
     //   isOpen = false;
        thisScreen = passedEngine.thisScreen;
        myLayer = 3;
myEngine = passedEngine;

        unitImage = R.drawable.merchant_a_shopkeeper;
        image = generateImage(unitImage);
        x = myX;
        y = myY;
        loadIntoTile(x, y, myEngine.myTiles);
    }

    public int tryUse(GameEngine myEngine) {
return 3;
    }



}
