package org.o7planning.a412gameproject;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.io.IOException;
import java.lang.Math;


public class ShopState extends GameState { // completely fix this shop state asap HIGH PRIORITY.

    public GameStateManager myGSM;
    public GameScreen thisScreen;
    public Equipment myTestItem;

    public Bitmap menuImage;
    public Inventory heroInventory;
    public Hero myHero;
    public int rows;
    public int columns;
    public int hoveredSlot;
    public int selectedSlot;
    public final int HELMET = 0;
    public final int TALISMAN = 1;
    public final int ARMOR = 2;
    public final int WEAPON = 3;
    public final int OFFHAND = 4;

    public final int BOOTS = 5;

    public InventoryItem[] shopStock;

    public final int STOCKSIZE = 16;
    public final int STOCKROWS = 4;
    public final int STOCKCOLS = 4;
    public final int SHOPOFFSET = 100; //this is a suprememely dumb and hardcoded value that should ideally be replaced


    public Rect myRectangle;

    public GameEngine myEngine;

    int myHeight;
    int myWidth;


    public ShopState(GameScreen myScreen, GameStateManager passedGSM) throws IOException {
        thisScreen = myScreen;
        myGSM = passedGSM;
        myEngine = (GameEngine) myGSM.gameStates.get(0); //this is probably crap and should desperately be reworked
        myHero = myGSM.myHero;
        heroInventory = myHero.myInventory;
        rows = (int) Math.sqrt(heroInventory.storageSpace);
        columns = rows;
        hoveredSlot = -1;
        menuImage = generateImage(R.drawable.tempshopkeep);
        //menuImage = generateImage(R.drawable.staff_mummy);
        selectedSlot = -1;
        shopStock = new InventoryItem[STOCKSIZE];
        myRectangle = new Rect();
        initStock();
    }

    public void initStock() {

//I found a try catch here that needed to be removed, all the IOExceptions hinder my program
        //TODO remove almost all try catch statements
        shopStock[0] = new Equipment(R.drawable.scythe3, 100, 0, 0, "Scythe of Power - Damage 100", "ScytheofPower", "Weapon", myEngine); //populating the shop through brute force
        shopStock[1] = new Equipment(R.drawable.falchion1, 15, 0, 0, "Basic Falchion - Damage 15", "BasicFalchion", "Weapon", myEngine);
        shopStock[2] = new Equipment(R.drawable.triple_sword, 25, 0, 0, "Triple Sword - Damage 25", "TripleSword", "Weapon", myEngine);
        shopStock[3] = new Equipment(R.drawable.short_sword1, 15, 0, 0, "Short Sword - Damage 15", "ShortSword", "Weapon", myEngine);
        shopStock[4] = new Equipment(R.drawable.scythe1, 20, 0, 0, "Scythe - Damage 20", "Scyth", "Weapon", myEngine);
        shopStock[5] = new Equipment(R.drawable.spear1, 5, 0, 0, "Rusty Spear - Damage 5", "Spear", "Weapon", myEngine);
        shopStock[6] = new Equipment(R.drawable.staff_mummy, 10, 0, 0, "Staff - Damage 10", "Staff", "Weapon", myEngine); //could boost magic once i see that implementation
        shopStock[7] = new Equipment(R.drawable.hand_axe1, 5, 0, 0, "Hand Axe - Damage 15", "Hand Axe", "Weapon", myEngine);
        shopStock[8] = new Equipment(R.drawable.long_sword1, 40, 0, 0, "Hero's Sword - Damage 40", "HeroSword", "Weapon", myEngine);
        shopStock[9] = new Equipment(R.drawable.helmet1, 0, 5, 0, "Basic Helmet - Armor + 5", "BasicHelmet", "Helmet", myEngine);//Not sure how to classify
        shopStock[10] = new Equipment(R.drawable.helmet_ego1, 0, 30, 0, "Helm of Vahalla - Armor + 30", "VallhalaHelm", "Helmet", myEngine);//Not sure how to classify
        shopStock[11] = new Equipment(R.drawable.gold_dragon_armour, 0, 20, 0, "Dragon Aromour - +20 Armor", "Dragon Armor", "Armor", myEngine);//Not sure how to classify
        shopStock[12] = new Equipment(R.drawable.cloak3, 0, 0, 25, "Wizard Robe - Intellegence + 25", "WizardRobe", "Armor", myEngine); //potential magic boost
        shopStock[13] = new Equipment(R.drawable.boots2_jackboots, 0, 10, 0, "Boots - Armor +10", "Boots", "Boots", myEngine);
        shopStock[14] = new Equipment(R.drawable.urand_shadows, 0, 10, 25, "Ring of Power, + 20 Intellegence + 10 Defense ", "RingOfPower", "Talisman", myEngine); //potential magic boost
        shopStock[15] = new Equipment(R.drawable.urand_octoring, 0, 0, 15, "Magic Ring + 15 Intellengence", "MagicRing", "Talisman", myEngine); //potential magic boost

        shopStock[0].goldValue = 1000; //setting values for the shop, hardcoding cuz thinking is hard
        shopStock[1].goldValue = 200;
        shopStock[2].goldValue = 300;
        shopStock[3].goldValue = 200;
        shopStock[4].goldValue = 250;
        shopStock[5].goldValue = 100;
        shopStock[6].goldValue = 100;
        shopStock[7].goldValue = 100;
        shopStock[8].goldValue = 500;
        shopStock[9].goldValue = 100;
        shopStock[10].goldValue = 400;
        shopStock[11].goldValue = 300;
        shopStock[12].goldValue = 300;
        shopStock[13].goldValue = 200;
        shopStock[14].goldValue = 700;
        shopStock[15].goldValue = 500;

    }

    public void draw(Canvas canvas) {
        //thisScreen.gbi.drawImage(myTestItem.image, 50, 50, null);
        //if (DeleteThisTestVariable == 1) {
        //   thisScreen.gbi.drawString(myTestItem.itemDescription, 50, 50);
        //  thisScreen.gbi.drawString(("This item is Level " + myTestItem.powerLevel), 50, 40);
        //}

        myHeight = canvas.getHeight();
        myWidth = canvas.getWidth() * 3 / 8;

        //int myWidth = thisScreen.myBufferedDimension.getWidth() * 3 / 8; the way that it used to be
        myRectangle.set(0, 0, canvas.getWidth(), myHeight);
        canvas.drawBitmap(menuImage, null, myRectangle, null);
        drawInventory(canvas);
        drawEquipped(canvas);
        drawShopStock(canvas);
        drawDescription(canvas);
    }

    public void drawInventory(Canvas canvas) {


        int left;
        int top;
        int itemWidth = (int) (myWidth * 0.8) / columns;
        int itemHeight = (int) (myHeight * 0.8) / rows;
        int itemNumber = 0;

        for (int i = 0; i < columns; i++) {
            left = ((myWidth / columns) * i) + 15;
            for (int j = 0; j < rows; j++) {
                itemNumber = (j * columns) + i;
                if (itemNumber < heroInventory.storageSpace) {
                    if (heroInventory.items[itemNumber] != null) {

                        top = (myHeight / rows) * j + 20;

                        myRectangle.set(left, top, left + itemWidth, top + itemHeight);
                        canvas.drawBitmap(heroInventory.items[itemNumber].image, null, myRectangle, null);
                        // thisScreen.gbi.drawImage(heroInventory.items[itemNumber].image, ((myWidth / columns) * i) + 15, (myHeight / rows) * j + 20, (int) (myWidth * 0.8) / columns, (int) (myHeight * 0.8) / rows, null);
                    }

                }

            }
        }
    }

    public void drawEquipped(Canvas canvas) {

        int left = ((myWidth / columns) * 10) + 15;
        int top;
        int itemWidth = (int) (myWidth * 0.8) / columns;
        int itemHeight = (int) (myHeight * 0.8) / rows;
        for (int i = 0; i < myHero.SLOTS; i++) {
            if (myHero.equippedItems[i] != null) {

                top = (myHeight / rows) * i + 20;
                myRectangle.set(left, top, left + itemWidth, top + itemHeight);
                canvas.drawBitmap(myHero.equippedItems[i].image, null, myRectangle, null);
                //  thisScreen.gbi.drawImage(myHero.equippedItems[i].image, ((myWidth / columns) * 10) + 15, ((myHeight / rows) * i) + 20, (int) (myWidth * 0.8) / columns, (int) (myHeight * 0.8) / rows, null);
            }
        }

    }

    public void drawShopStock(Canvas canvas) {
        int left;
        int top;
        int itemWidth = (int) (myWidth * 0.8) / columns;
        int itemHeight = (int) (myHeight * 0.8) / rows;
        for (int i = 0; i < STOCKSIZE; i++) {
            if (shopStock[i] != null) {
                left = ((myWidth / columns) * (12 + (i % STOCKROWS))) + 15;
                top = ((myHeight / rows) * (2 + (i / STOCKROWS))) + 20;
                myRectangle.set(left, top, left + itemWidth, top + itemHeight);
                canvas.drawBitmap(shopStock[i].image, null, myRectangle, null);
                //    thisScreen.gbi.drawImage(shopStock[i].image, ((myWidth / columns) * (12 + (i % STOCKROWS))) + 15, ((myHeight / rows) * (2 + (i / STOCKROWS))) + 20, (int) (myWidth * 0.8) / columns, (int) (myHeight * 0.8) / rows, null);
            }
        }

    }

    public void drawDescription(Canvas canvas) {
        if (hoveredSlot != -1 && hoveredSlot < heroInventory.storageSpace) {
            if (heroInventory.items[hoveredSlot] != null) {
                int y = hoveredSlot / rows;
                int x = hoveredSlot - (y * rows);
                // thisScreen.gbi.drawString(heroInventory.items[hoveredSlot].itemDescription + " PRICE: " +heroInventory.items[hoveredSlot].goldValue, ((myWidth / columns) * x), (myHeight / rows) * y + 50); //we need to replace this 50 with something non hardcoded ASAP
            }
        } else if (hoveredSlot >= heroInventory.storageSpace && hoveredSlot < (heroInventory.storageSpace + 6)) {
            int hoveredEquipSlot = hoveredSlot - heroInventory.storageSpace;
            if (myHero.equippedItems[hoveredEquipSlot] != null) {
                int y = hoveredEquipSlot;
                int x = 9;
                //  thisScreen.gbi.drawString(myHero.equippedItems[hoveredEquipSlot].itemDescription + " PRICE: " + myHero.equippedItems[hoveredEquipSlot].goldValue , ((myWidth / columns) * x), (myHeight / rows) * y + 50); //we need to replace this 50 with something non hardcoded ASAP
            }
        } else if (hoveredSlot >= SHOPOFFSET) {
            int y = 3;
            int x = 7;
            if (shopStock[hoveredSlot - SHOPOFFSET] != null) {
                //   thisScreen.gbi.drawString(shopStock[hoveredSlot - SHOPOFFSET].itemDescription + " PRICE: " + shopStock[hoveredSlot - SHOPOFFSET].goldValue, ((myWidth / columns) * x), (myHeight / rows) * y + 50); //we need to replace this 50 with something non hardcoded ASAP
            }
        }

        // thisScreen.gbi.drawString("Current Attack Power: " + myHero.attackPower, 750, 50);
        //  thisScreen.gbi.drawString("Current Gold is: " + (int) myHero.goldCoins, 750, 100);
    }

   /* public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_S) {
            lightlyResetInventory();
            myGSM.setState(0); // Back to adventure screen

        }
        if (key == KeyEvent.VK_ESCAPE) {
            lightlyResetInventory();
            myGSM.setState(0); // Back to adventure screen I dont see a reason to go to main menu here.

        }

    }
*/


    public void lightlyResetInventory() {
        selectedSlot = -1;
        // thisScreen.resetCursor();
    }



   /* public void mouseMoved(MouseEvent e) {
        hoveredSlot = calculateSlot(e.getX(), e.getY());
        //if (hoveredSlot >= heroInventory.storageSpace) { delete this soon
        //  hoveredSlot = -1;
        //}
    }*/

    public int calculateSlot(int x, int y) {
        int myWidth = thisScreen.getWidth() * 3 / 8;
        int myHeight = thisScreen.getHeight();

        if (x * columns / myWidth < columns && y * rows / myHeight < rows) {
            return (x * columns / myWidth) + ((y * rows / myHeight) * columns);
        } else if (x * columns / myWidth >= columns && y * rows / myHeight < rows && x < 2 * myWidth) {
            return heroInventory.storageSpace + (y * rows / myHeight);
        } else if (x >= 2 * myWidth && y * rows / myHeight >= 2) {
            // System.out.println(SHOPOFFSET + (x * columns / myWidth) - 12 + (((y * rows / myHeight) - 2) * STOCKROWS));
            return SHOPOFFSET + (x * columns / myWidth) - 12 + (((y * rows / myHeight) - 2) * STOCKROWS);

        } else if (x >= 2 * myWidth && y * rows / myHeight < 2) {
            //System.out.println(SHOPOFFSET - 1);
            return SHOPOFFSET - 1;
        } else {
            return -1;
        }
    }

    public void mouseClicked(MotionEvent e) {
        if (e.getY() > (myHeight * 4) / 5) {
            lightlyResetInventory();
            myGSM.setState(0);
        } else {
            hoveredSlot = calculateSlot((int) e.getX(), (int) e.getY());

            if (hoveredSlot > -1) {
                if (hoveredSlot < heroInventory.storageSpace) // this part is buggy
                {

                    if (heroInventory.items[hoveredSlot] != null && selectedSlot == -1) {
                        selectedSlot = hoveredSlot;
                        // thisScreen.changeCursor(heroInventory.items[selectedSlot].image);
                    } else if (selectedSlot > -1) {
                        if (selectedSlot < heroInventory.storageSpace) {
                            swapItems(selectedSlot, hoveredSlot);
                        } else if (selectedSlot >= heroInventory.storageSpace && selectedSlot < SHOPOFFSET) {
                            if (heroInventory.items[hoveredSlot] == null) {
                                swapItems(selectedSlot, hoveredSlot);
                            } else {
                                if (attemptEquippingItem(hoveredSlot, selectedSlot - heroInventory.storageSpace)) {
                                    lightlyResetInventory();
                                }
                            }
                        }
                    }
                } else if (hoveredSlot >= heroInventory.storageSpace && hoveredSlot < SHOPOFFSET - 1) {
                    if (selectedSlot != -1) {
                        if (attemptEquippingItem(selectedSlot, hoveredSlot - heroInventory.storageSpace)) {
                            lightlyResetInventory();
                        }
                    } else if (myHero.equippedItems[hoveredSlot - heroInventory.storageSpace] != null) {
                        selectedSlot = hoveredSlot;
                        // thisScreen.changeCursor(myHero.equippedItems[hoveredSlot - heroInventory.storageSpace].image);
                    }
                } else if (hoveredSlot == SHOPOFFSET - 1 && selectedSlot > -1) {
                    sellItem();
                } else if (hoveredSlot >= SHOPOFFSET && selectedSlot == -1) {
                    buyItem();
                }
            }
        }
    }

    public void sellItem() {

        if (selectedSlot < heroInventory.storageSpace) {
            if (heroInventory.items[selectedSlot].isSellable) {
                myHero.goldCoins += heroInventory.items[selectedSlot].goldValue;
                heroInventory.items[selectedSlot] = null;
                lightlyResetInventory();
            }
        } else if (myHero.equippedItems[selectedSlot - heroInventory.storageSpace].isSellable) {
            myHero.goldCoins += myHero.equippedItems[selectedSlot - heroInventory.storageSpace].goldValue;
            myHero.unequip(myHero.equippedItems[selectedSlot - heroInventory.storageSpace]);
            myHero.equippedItems[selectedSlot - heroInventory.storageSpace] = null;
            lightlyResetInventory();
        }

    }

    public void buyItem() {
        if (shopStock[hoveredSlot - SHOPOFFSET] != null) {
            if (myHero.goldCoins >= shopStock[hoveredSlot - SHOPOFFSET].goldValue && myHero.myInventory.hasSpace()) {
                myHero.myInventory.addItem(shopStock[hoveredSlot - SHOPOFFSET]);
                myHero.goldCoins -= shopStock[hoveredSlot - SHOPOFFSET].goldValue;
                shopStock[hoveredSlot - SHOPOFFSET] = null;
            } else {
                // do something else idk for now it will just do nothing which is fine
            }
        }
    }

    public void swapItems(int firstSelectedSlot, int secondSelectedSlot) {
        if (selectedSlot != -1) {
            if (firstSelectedSlot < heroInventory.storageSpace && secondSelectedSlot < heroInventory.storageSpace) {
                InventoryItem tempItem;
                tempItem = heroInventory.items[firstSelectedSlot];
                heroInventory.items[firstSelectedSlot] = heroInventory.items[secondSelectedSlot];
                heroInventory.items[secondSelectedSlot] = tempItem;
                lightlyResetInventory();
            } else if (firstSelectedSlot < heroInventory.storageSpace && secondSelectedSlot >= heroInventory.storageSpace) {
                Equipment tempEquip;
                tempEquip = (Equipment) heroInventory.items[firstSelectedSlot];
                heroInventory.items[firstSelectedSlot] = myHero.equippedItems[secondSelectedSlot - heroInventory.storageSpace];
                myHero.unequip(myHero.equippedItems[secondSelectedSlot - heroInventory.storageSpace]);
                myHero.equip(tempEquip, secondSelectedSlot - heroInventory.storageSpace);

                lightlyResetInventory();
            } else if (secondSelectedSlot < heroInventory.storageSpace && firstSelectedSlot >= heroInventory.storageSpace) {
                Equipment tempEquip;
                tempEquip = (Equipment) heroInventory.items[secondSelectedSlot];
                heroInventory.items[secondSelectedSlot] = myHero.equippedItems[firstSelectedSlot - heroInventory.storageSpace];
                myHero.unequip(myHero.equippedItems[firstSelectedSlot - heroInventory.storageSpace]);
                myHero.equip(tempEquip, firstSelectedSlot - heroInventory.storageSpace);
                lightlyResetInventory();
            }

        }
    }

    public boolean attemptEquippingItem(int attemptedSlot, int attemptedEquipSlot) {
        if (hoveredSlot >= heroInventory.storageSpace && selectedSlot >= heroInventory.storageSpace) {
            return false;
        }
        InventoryItem attemptedItem;
        if (attemptedSlot < heroInventory.storageSpace) {
            attemptedItem = heroInventory.items[attemptedSlot];
        } else {
            attemptedItem = myHero.equippedItems[attemptedSlot - heroInventory.storageSpace];
        }

        if (attemptedItem == null) {
            swapItems(selectedSlot, hoveredSlot);
            return true;
        }
        if (attemptedItem instanceof Equipment) {
            Equipment attemptedEquipment = (Equipment) attemptedItem;
            if (equipmentCanGoThere(attemptedEquipment, attemptedEquipSlot)) {
                swapItems(selectedSlot, hoveredSlot);
                return true;
            }

        }
        return false;
    }

    public boolean equipmentCanGoThere(Equipment aE, int aES) {
        return (aE.equipType == "Helmet" && aES == HELMET)
                || (aE.equipType == "Talisman" && aES == TALISMAN)
                || (aE.equipType == "Armor" && aES == ARMOR)
                || (aE.equipType == "Weapon" && aES == WEAPON)
                || (aE.equipType == "Offhand" && aES == OFFHAND)
                || (aE.equipType == "Boots" && aES == BOOTS);
    }


    public Bitmap generateImage(int myImageName) throws IOException {
        return thisScreen.generateImage(myImageName);
    }
}
