package org.o7planning.a412gameproject;


import java.io.IOException;


public class Equipment extends InventoryItem {

    int powerLevel;
    int armourLevel; 
    int intelligenceLevel;
    String equipType;

    public Equipment(int myImageName, int myPowerLevel, int myArmourLevel, int myIntellegenceLevel, String myDescription, String myItemName, String myType, GameScreen myScreen) throws IOException {
        super(myImageName, myDescription, myItemName, myScreen);
        powerLevel = myPowerLevel;
        armourLevel =myArmourLevel;
        intelligenceLevel = myIntellegenceLevel; 
        equipType = myType;
        isSellable = true;
    }

}