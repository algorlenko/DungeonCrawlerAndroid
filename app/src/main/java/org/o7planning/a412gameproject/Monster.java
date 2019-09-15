package org.o7planning.a412gameproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Monster extends Unit {

    public ArrayList<InventoryItem> myDrops;

    long myBounty;

    public Monster(int myX, int myY, int myImage, InventoryItem myLoot, int myMaxHP, GameEngine passedEngine) throws IOException { // this is the simpler constructor
        super(myX, myY, myImage, myMaxHP, passedEngine);
        myBounty = 100;
        myDrops = new ArrayList<InventoryItem>();
        if (myLoot != null) {
            myDrops.add(myLoot);
        } else {
            myDrops = null;
        }
        // myDrops[0] = myLoot;
        attackPower = 2;

    }

    public Monster(int myX, int myY, int myImage, InventoryItem myLoot, int myMaxHP, long itsBounty, GameEngine passedEngine) throws IOException { // this will be the more in-depth constructor
        super(myX, myY, myImage, myMaxHP, passedEngine);
        myBounty = itsBounty;
        myDrops = new ArrayList<InventoryItem>();
        if (myLoot != null) {
            myDrops.add(myLoot);
        } else {
            myDrops = null;
        }
        // myDrops[0] = myLoot;
        attackPower = 2;

    }
    
    public Monster(int myX, int myY, int myImage, InventoryItem myLoot, int myMaxHP, long itsBounty, int myAttack, GameEngine passedEngine) throws IOException { // this will be the more in-depth constructor
        super(myX, myY, myImage, myMaxHP, passedEngine);
        myBounty = itsBounty;
        myDrops = new ArrayList<InventoryItem>();
        if (myLoot != null) {
            myDrops.add(myLoot);
        } else {
            myDrops = null;
        }
        // myDrops[0] = myLoot;
        attackPower = myAttack;

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

    
    //oldAIcode
   /*  public void aiAction(Tile myTiles[][], StatusScreen myStatus) throws IOException {
        MapObject target = null;
        target = scanInRadius(1, myTiles);
        Random rand = new Random();
        int xMove = 0;
        int yMove = 0;
        if (target != null) {
            Unit unitTarget = (Unit) target;
            attack(unitTarget, myTiles);
            myStatus.pushMessage("The Hero has been attacked!");
        } else {
            int n = rand.nextInt(4);
            switch (n) {
                case 0:
                    xMove = -1;
                    yMove = 0;
                    break;
                case 1:
                    xMove = 0;
                    yMove = 1;
                    break;
                case 2:
                    xMove = 1;
                    yMove = 0;
                    break;
                case 3:
                    xMove = 0;
                    yMove = -1;
                    break;

            }
            move(xMove, yMove, myTiles, myTiles.length, myTiles[0].length);
        }
    }
*/
    
     public void aiAction() {
        MapObject target = null;
        target = scanInRadius(1); // first we try to see if there is a hero or friendly within attacking range
        // Random rand = new Random();

        if (target != null) {
            Unit unitTarget = (Unit) target;
            attack(unitTarget);
            myEngine.myStatus.pushMessage("The Monster has Attacked dealing " + attackPower + " damage.");
            return;
        } else {
            target = scanInRadius(4); // if not, we check to see if they are within aggro distance and if we are, we follow them.
            if (target != null) {
followTarget(target);
            }
 
        }
        // move(xMove, yMove, myTiles, myTiles.length, myTiles[0].length);

    }
    
        public void followTarget(MapObject target) // I could potentially add in diagonal movement and just generally more intelligent calculation this in particualr also uses sloppier code than the allied creature, the allied Creature class is the gold standard for future work.
    {
                int xMove = 0;
        int yMove = 0;
        xMove = target.x - x;
yMove = target.y - y; 
if(Math.abs(xMove) > Math.abs(yMove))
{
    xMove = (int) Math.signum(xMove);;
    yMove = 0;
}
else
{
    yMove = (int) Math.signum(yMove);
    xMove = 0;
}
            move(xMove, yMove);
    }
    
    
    public void attack(Unit recipient){
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
    public void deathFunction(){
        super.deathFunction();
        // if (myDrops != null) {
        if (myEngine.myTiles[x][y].myContents[2] == null) {
            new LootBag(x, y, myDrops, myBounty, myEngine);
        } else if (myEngine.myTiles[x][y].myContents[2] instanceof LootBag) {
            LootBag currentBag = (LootBag) myEngine.myTiles[x][y].myContents[2];
            currentBag.addToBag(myDrops, myBounty);
        }

        // }
    }

}
