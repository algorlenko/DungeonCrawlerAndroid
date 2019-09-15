package org.o7planning.a412gameproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FriendlyCreature extends Unit {



    public FriendlyCreature(int myX, int myY, int imageName, int myMaxHP, int myBaseAttackPower, GameEngine passedEngine) {



        super(myX, myY, imageName, myMaxHP, passedEngine);
        attackPower = myBaseAttackPower;

        // myDrops[0] = myLoot;

    }

    public void move(int dx, int dy) {
        int futureX = x + dx;
        int futureY = y + dy;
        int pastX = x;
        int pastY = y;

        if (!(futureX < 0 || futureX > myEngine.dungeonColumns - 1 || futureY < 0 || futureY > myEngine.dungeonRows - 1)) {
            if (myEngine.myTiles[futureX][futureY].myContents[myLayer] == null) {
                x = futureX;
                y = futureY;
                myEngine.myTiles[pastX][pastY].myContents[myLayer] = null;
                myEngine.myTiles[pastX][pastY].imageName[myLayer] = R.drawable.empty;

                loadIntoTile(x, y);
            }
        }
    }

    public void aiAction() {
        MapObject target = null;
        target = scanInRadius(1); // first we try to scan for enemy monsters to attack
        // Random rand = new Random();

        if (target != null) {
            Unit unitTarget = (Unit) target;
            attack(unitTarget);
            myEngine.myStatus.pushMessage("Your Ally has attacked the Enemy for " + attackPower + " damage.");
            return;
        } else {
            target = scanForHero(4); // if the hero is close follow him
            if (target != null) {
                followTarget(target);
                return;
            }

            target = scanInRadius(4); // scan for close by monsters to engage
            if (target != null) {
                followTarget(target);
                return;
            } else // if the ally can't find any monsters to engange he will follow the hero.
            {
                target = myEngine.myHero;
                followTarget(target);
            }
        }
        // move(xMove, yMove, myTiles, myTiles.length, myTiles[0].length);

    }

    public void followTarget(MapObject target) // I could potentially add in diagonal movement and just generally more intelligent calculation
    {
        int xMove = 0;
        int yMove = 0;
        xMove = target.x - x;
        yMove = target.y - y;
        if (Math.abs(xMove) > Math.abs(yMove)) {
            xMove = (int) Math.signum(xMove);;
            yMove = 0;
        } else {
            yMove = (int) Math.signum(yMove);
            xMove = 0;
        }
        move(xMove, yMove);
    }

    public void attack(Unit recipient){
        recipient.takeDamage(attackPower);
    }

    public MapObject scanInRadius(int myRadius) { // this scans for a monster within the designated radius

        for (int i = -myRadius; i <= myRadius; i++) {
            for (int j = -myRadius; j <= myRadius; j++) {
                if (!(x + i < 0 || x + i > myEngine.myTiles.length - 1 || y + j < 0 || y + j > myEngine.myTiles[0].length - 1 || (i == 0 && j == 0))) {
                    if (myEngine.myTiles[x + i][y + j].myContents[myLayer] instanceof Monster) {
                        return myEngine.myTiles[x + i][y + j].myContents[myLayer];
                    }
                }
            }
        }
        return null;
    }

    public MapObject scanForHero(int myRadius) {

        for (int i = -myRadius; i <= myRadius; i++) {
            for (int j = -myRadius; j <= myRadius; j++) {
                if (!(x + i < 0 || x + i > myEngine.myTiles.length - 1 || y + j < 0 || y + j > myEngine.myTiles[0].length - 1 || (i == 0 && j == 0))) {
                    if (myEngine.myTiles[x + i][y + j].myContents[myLayer] instanceof Hero) {
                        return myEngine.myTiles[x + i][y + j].myContents[myLayer];
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void deathFunction(){
        super.deathFunction();
        // if (myDrops != null) {

        // }
    }

}
