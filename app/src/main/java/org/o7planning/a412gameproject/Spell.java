package org.o7planning.a412gameproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.IOException;


public class Spell {

    final static int UNITLAYER = 3;
    public GameEngine myEngine;
    public String spellName;
    public Bitmap spellImage;
    public int manaCost;
    public GameScreen thisScreen;
    public Spell(GameEngine passedEngine, String myName, int myImageName, int myManaCost) {
        myEngine = passedEngine;
        thisScreen = myEngine.thisScreen;
        manaCost = myManaCost; 
        spellName = myName;
        try {
            spellImage = generateImage(myImageName);
        } catch (Exception e) {

        }
    }

    public void castSpell(Unit castingUnit, int targetedX, int targetedY) {
        if (spellName == "Teleport") {
            teleportAction(castingUnit, targetedX, targetedY);
        }

        if (spellName == "Arcane Blast") {
            arcaneBlastAction(castingUnit, targetedX, targetedY);
        }
        if (spellName == "Raise Skeleton") {
            summonSkeletonAction(castingUnit, targetedX, targetedY);
        }
    }

    public void teleportAction(Unit castingUnit, int destinationX, int destinationY) {
        boolean teleportSuccess = castingUnit.moveTo(destinationX, destinationY);
        if (teleportSuccess && castingUnit == myEngine.myHero) {
            myEngine.myStatus.pushMessage("You have Teleported.");
          //  myEngine.thisScreen.resetCursor();
            myEngine.selectedSpell = null; // this could also be myHero.selectedSpell
            castingUnit.mana-= manaCost; 
            myEngine.successfulTurn();
        } else if (!teleportSuccess && castingUnit == myEngine.myHero) {
            myEngine.myStatus.pushMessage("You cannot Teleport there.");
        } else if (!teleportSuccess && castingUnit instanceof Monster) {
            // insert code here to make the enemy try teleporting again.
        }
    }

    public void arcaneBlastAction(Unit castingUnit, int destinationX, int destinationY) {
        boolean blastSuccess;
        if (myEngine.myTiles[destinationX][destinationY].myContents[UNITLAYER] != null) {
            if (castingUnit == myEngine.myHero) {
                if (myEngine.myTiles[destinationX][destinationY].myContents[UNITLAYER] instanceof Monster) {
                    Monster target = (Monster) myEngine.myTiles[destinationX][destinationY].myContents[UNITLAYER];
                    try {
                        target.takeDamage(myEngine.myHero.intelligence);
                    } catch (Exception exc) {

                    }
                    blastSuccess = true;
                    myEngine.myStatus.pushMessage("You have blasted the target for " + myEngine.myHero.intelligence + " damage.");
               //     myEngine.thisScreen.resetCursor();
                    myEngine.selectedSpell = null; // this could also be myHero.selectedSpell
                    castingUnit.mana -= manaCost; 
                    myEngine.successfulTurn();
                    
                } else {
                    myEngine.myStatus.pushMessage("You can not blast there");
                }
            }
        }
    }

    public void summonSkeletonAction(Unit castingUnit, int destinationX, int destinationY) {
      //  boolean summonSuccess;
        if (destinationX < myEngine.dungeonColumns && destinationX >= 0 && destinationY < myEngine.dungeonRows && destinationY >= 0) {
            if (myEngine.myTiles[destinationX][destinationY].myContents[UNITLAYER] == null) {

                myEngine.friendlyCreatures.add(new FriendlyCreature(destinationX, destinationY, R.drawable.death_knight, myEngine.myHero.intelligence * 3, myEngine.myHero.intelligence, myEngine));
                myEngine.myStatus.pushMessage("You have Summoned an Undead Ally.");
        //    myEngine.thisScreen.resetCursor();
            myEngine.selectedSpell = null; // this could also be myHero.selectedSpell
            castingUnit.mana -= manaCost; 
            myEngine.successfulTurn();

            }
            else
            {
                myEngine.myStatus.pushMessage("You can not Summon there.");
            }
        }
    }

    public Bitmap generateImage(int myImageName) throws IOException {
        return thisScreen.generateImage(myImageName);
    }
}
