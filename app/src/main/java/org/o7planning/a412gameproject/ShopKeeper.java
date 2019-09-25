package org.o7planning.a412gameproject;


import java.io.IOException;

public class ShopKeeper extends MapObject implements Useable {

//    boolean isOpen;

    public ShopKeeper(int myX, int myY, GameEngine passedEngine) throws IOException {
     //   isOpen = false;
        myLayer = 3;
myEngine = passedEngine;

        unitImage = R.drawable.merchant_a_shopkeeper;
        image = generateImage(unitImage);
        x = myX;
        y = myY;
        loadIntoTile(x, y);
    }

    public ShopKeeper(Tile currentTile) {
        super(currentTile, 3, 1, "Shopkeeper");
        x = currentTile.x;
        y = currentTile.y;
    }

    public int tryUse() {
return 3;
    }



}
