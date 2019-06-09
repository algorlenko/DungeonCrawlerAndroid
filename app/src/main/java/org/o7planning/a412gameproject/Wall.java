package org.o7planning.a412gameproject;

import java.io.IOException;

public class Wall extends MapObject {

    public Wall(int myX, int myY, Tile myTiles[][], int wallImageString, GameScreen myScreen) throws IOException {
        thisScreen = myScreen;
        myLayer = 3;
        unitImage = wallImageString;
        image = generateImage(unitImage);
        x = myX;
        y = myY;
        loadIntoTile(x, y, myTiles);
    }

}
