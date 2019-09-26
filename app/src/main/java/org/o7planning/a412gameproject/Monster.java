package org.o7planning.a412gameproject;

import java.util.ArrayList;

public class Monster extends Unit {

    public ArrayList<InventoryItem> myDrops;

    long myBounty;

    public Monster(Tile heroTile, String monsterName, InventoryItem myLoot, int myMaxHP, int monsterBounty, int monsterAttack) {
        super(heroTile, myMaxHP, monsterName,4);
        myBounty = monsterBounty;
        maxHP = myMaxHP;
        attackPower = monsterAttack;
        myDrops = new ArrayList<InventoryItem>();
        if (myLoot != null) {
            myDrops.add(myLoot);
        } else {
            myDrops = null;
        }
    }

    public Monster(Tile heroTile, String monsterName, InventoryItem myLoot, int myMaxHP) {
        this(heroTile, monsterName, myLoot, myMaxHP, 100, 2);
    }

    public void aiAction() {
        MapObject target = null;
        target = scanInRadius(1); // first we try to see if there is a hero or friendly within attacking range

        if (target != null) {
            Unit unitTarget = (Unit) target;
            attack(unitTarget);
            myEngine.myStatus.pushMessage("The Monster has Attacked dealing " + attackPower + " damage.");
        } else {
            target = scanInRadius(4); // if not, we check to see if they are within aggro distance and if we are, we follow them.
            if (target != null) {
                followTarget(target);
            }
        }
    }

    public void followTarget(MapObject target){
        if(target.x > x){
            if (attemptMoveToTile(myEngine.myTiles[x+1][y])){
                return;
            }
        }
        else{
            if (attemptMoveToTile(myEngine.myTiles[x-1][y])){
                return;
            }
        }
        if(target.y > y){
            if (attemptMoveToTile(myEngine.myTiles[x][y+1])){
                return;
            }
        }
        else{
            if (attemptMoveToTile(myEngine.myTiles[x][y-1])){
                return;
            }
        }
    }


    public void attack(Unit recipient) {
        recipient.takeDamage(attackPower);
    }

    public MapObject scanInRadius(int myRadius) {

        for (int i = -myRadius; i <= myRadius; i++) {
            for (int j = -myRadius; j <= myRadius; j++) {
                if (!(x + i < 0 || x + i > myEngine.myTiles.length - 1 || y + j < 0 || y + j > myEngine.myTiles[0].length - 1 || (i == 0 && j == 0))) {
                    if (myEngine.myTiles[x + i][y + j].myContents[myLayer] instanceof Hero || myEngine.myTiles[x + i][y + j].myContents[myLayer] instanceof FriendlyCreature) {
                        return myEngine.myTiles[x + i][y + j].myContents[myLayer];
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void deathFunction() {
        super.deathFunction();
        // if (myDrops != null) {
        if (myEngine.myTiles[x][y].myContents[2] == null) {
            //new LootBag(x, y, myDrops, myBounty, myEngine);
            new LootBag(myEngine.myTiles[x][y], myDrops, myBounty);
        } else if (myEngine.myTiles[x][y].myContents[2] instanceof LootBag) {
            LootBag currentBag = (LootBag) myEngine.myTiles[x][y].myContents[2];
            currentBag.addToBag(myDrops, myBounty);
        }
        // }
    }

}
