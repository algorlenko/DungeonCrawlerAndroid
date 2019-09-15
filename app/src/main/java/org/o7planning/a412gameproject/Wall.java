package org.o7planning.a412gameproject;

import java.io.IOException;

public class Wall extends MapObject {

    public Wall(int myX, int myY, int wallImageString, GameEngine passedEngine) throws IOException {
        myEngine = passedEngine;
        myLayer = 3;
        unitImage = wallImageString;
        image = generateImage(unitImage);
        x = myX;
        y = myY;
        loadIntoTile(x, y);
    }

}
