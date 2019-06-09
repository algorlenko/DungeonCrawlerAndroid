package org.o7planning.a412gameproject;

import java.io.IOException;

public class Unit extends MapObject {

    int maxHP;
    int hp;
    boolean isAlive;
    int attackPower;    
    int mana; 
    int maxMana; 
    int baseMaxMana; 

    public Unit(int myX, int myY, Tile myTiles[][], int myImage, int myMaxHP, GameScreen myScreen) throws IOException {
        thisScreen = myScreen;
        myLayer = 3; //if you change this unitLayer you need to change it in the GameEngine/AdventureState class as well.
        unitImage = myImage;
        try
        {
        image = generateImage(unitImage);
        }
        catch(Exception exc)
        {
            
        }
        x = myX;
        y = myY;
        loadIntoTile(x, y, myTiles);
        maxHP = myMaxHP;
        hp = maxHP;
        //armor = 0; 
        isAlive = true;

    }

    public void takeDamage(int damageAmount, Tile myTiles[][]) throws IOException {
        
        hp -= damageAmount;
        
               
        if (hp <= 0) {
            deathFunction(myTiles);
        }
    }

    public void deathFunction(Tile myTiles[][]) throws IOException {
        myTiles[x][y].myContents[myLayer] = null;
        myTiles[x][y].imageName[myLayer] = R.drawable.empty;
        isAlive = false;
    }

    public void move(int dx, int dy, Tile myTiles[][], int dungeonColumns, int dungeonRows) {
        int futureX = x + dx;
        int futureY = y + dy;
        int pastX = x;
        int pastY = y;

        if (!(futureX < 0 || futureX > dungeonColumns - 1 || futureY < 0 || futureY > dungeonRows - 1)) {
            if ((myTiles[futureX][futureY].myContents[myLayer] == null)) {
                x = futureX;
                y = futureY;
                myTiles[pastX][pastY].myContents[myLayer] = null;
                myTiles[pastX][pastY].imageName[myLayer] = R.drawable.empty;

                loadIntoTile(x, y, myTiles);
            }
        }
    }
    
    
        public boolean moveTo(int futureX, int futureY, Tile myTiles[][], int dungeonColumns, int dungeonRows) {
        int pastX = x;
        int pastY = y;

        if (!(futureX < 0 || futureX > dungeonColumns - 1 || futureY < 0 || futureY > dungeonRows - 1)) {
            if ((myTiles[futureX][futureY].myContents[myLayer] == null)) {
                x = futureX;
                y = futureY;
                myTiles[pastX][pastY].myContents[myLayer] = null;
                myTiles[pastX][pastY].imageName[myLayer] = R.drawable.empty;

                loadIntoTile(x, y, myTiles);
                return true;
            }
        }
        return false;
    }
}
