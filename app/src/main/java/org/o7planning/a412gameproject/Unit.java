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

    public Unit(int myX, int myY, int myImage, int myMaxHP, GameEngine passedEngine){
        myEngine = passedEngine;
        myLayer = 3; //if you change this unitLayer you need to change it in the GameEngine/AdventureState class as well.
        unitImage = myImage;

        image = generateImage(unitImage);

        x = myX;
        y = myY;
        loadIntoTile(x, y);
        maxHP = myMaxHP;
        hp = maxHP;
        //armor = 0; 
        isAlive = true;

    }

    public Unit(Tile currentTile, int myMaxHP, String baseImageName, int numberOfImages){
        super(currentTile, 3, numberOfImages, baseImageName);
        myEngine = currentTile.myEngine;
        myLayer = 3;
        maxHP = myMaxHP;
        hp = maxHP;
        isAlive = true;
    }

    public void takeDamage(int damageAmount) {
        
        hp -= damageAmount;
        
               
        if (hp <= 0) {
            deathFunction();
        }
    }

    public void deathFunction(){
        //myEngine.myTiles[x][y].myContents[myLayer] = null;
        currentTile.myContents[myLayer] = null;
        //myEngine.myTiles[x][y].imageName[myLayer] = R.drawable.empty;
        isAlive = false;
    }

    public void move(int dx, int dy) {
        int futureX = x + dx;
        int futureY = y + dy;
        int pastX = x;
        int pastY = y;

        if (!(futureX < 0 || futureX > myEngine.dungeonColumns - 1 || futureY < 0 || futureY > myEngine.dungeonRows - 1)) {
            if ((myEngine.myTiles[futureX][futureY].myContents[myLayer] == null)) {
                x = futureX;
                y = futureY;
                myEngine.myTiles[pastX][pastY].myContents[myLayer] = null;
                //myEngine.myTiles[pastX][pastY].imageName[myLayer] = R.drawable.empty;

                loadIntoTile(x, y);
            }
        }
    }
    
    
        public boolean moveTo(int futureX, int futureY) {
        int pastX = x;
        int pastY = y;

        if (!(futureX < 0 || futureX > myEngine.dungeonColumns - 1 || futureY < 0 || futureY > myEngine.dungeonRows - 1)) {
            if ((myEngine.myTiles[futureX][futureY].myContents[myLayer] == null)) {
                x = futureX;
                y = futureY;
                myEngine.myTiles[pastX][pastY].myContents[myLayer] = null;
                myEngine.myTiles[pastX][pastY].imageName[myLayer] = R.drawable.empty;

                loadIntoTile(x, y);
                return true;
            }
        }
        return false;
    }
}
