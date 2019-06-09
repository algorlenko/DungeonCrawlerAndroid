package org.o7planning.a412gameproject;

import java.io.IOException;
import java.util.ArrayList;

public class LootBag extends MapObject {

    long goldCoins;
    ArrayList<InventoryItem> droppedItems; //as a large global note, I am finding that basically everything needs to have access to everything, and therefore, I think on future projects, I will give all subclasses a property that is the class that they are part of

    public LootBag(int myX, int myY, Tile myTiles[][], ArrayList<InventoryItem> myItems, long myBounty, GameScreen myScreen) throws IOException {
        thisScreen = myScreen;
        myLayer = 2;
        droppedItems = myItems;
        if (myItems == null) {
            unitImage = R.drawable.satchel;
        } else {
            unitImage = R.drawable.chest_silver;
        }
        image = generateImage(unitImage);
        x = myX;
        y = myY;
        goldCoins = myBounty;
        loadIntoTile(x, y, myTiles);
    }

    public void addToBag(ArrayList<InventoryItem> addedItems, long addedBounty) //this is poorly written and should be done using lists
    {
        goldCoins += addedBounty;
        if (droppedItems == null) {
            droppedItems = addedItems;
            if (addedItems != null) {
                try {
                    unitImage = R.drawable.chest_silver; // this won't work without me loading the chest image into myTiles or me fundamentally changing how drawing works, by having the draw just use the mycontents parameter.
                    image = generateImage(unitImage);
                } catch (Exception e) {

                }
            }
        } else if (addedItems != null) { // Alex fixed this
            droppedItems.addAll(addedItems);
        }
        /*
        for (int i = 1; i < droppedItems.length; i++) {
            for (int j = 0; j < droppedItems.length; j++) {
                if (droppedItems[i] == null) {
                    if (addedItems[j] == null) {
                        return;
                    }
                    droppedItems[i] = addedItems[j];
                    i++;
                }
            }
        }*/

    }

}
