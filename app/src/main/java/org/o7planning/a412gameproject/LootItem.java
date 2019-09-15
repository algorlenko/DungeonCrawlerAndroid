package org.o7planning.a412gameproject;

public class LootItem extends MapObject {

    public InventoryItem thisItem;

    public LootItem(int myX, int myY, InventoryItem myItem, GameEngine passedEngine) {
        myEngine = passedEngine;
        myLayer = 2;
        thisItem = myItem;
        unitImage = myItem.imageName;
        image = myItem.image;
        x = myX;
        y = myY;
        loadIntoTile(x, y);

    }

}
