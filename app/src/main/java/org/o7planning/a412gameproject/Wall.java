package org.o7planning.a412gameproject;

public class Wall extends MapObject {

    public Wall(int myX, int myY, int wallImageString, GameEngine passedEngine) {
        myEngine = passedEngine;
        myLayer = 3;
        unitImage = wallImageString;
        image = generateImage(unitImage);
        x = myX;
        y = myY;
        loadIntoTile(x, y);
    }

    public Wall(Tile currentTile) {
        super(currentTile, 3, "Wall");
        x = currentTile.x;
        y = currentTile.y;
    }

}
