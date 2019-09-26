package org.o7planning.a412gameproject;

public class Floor extends MapObject {

    public Floor(Tile currentTile) {
        super(currentTile, 0, "Floor");
        x = currentTile.x;
        y = currentTile.y;
    }

}
