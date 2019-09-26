package org.o7planning.a412gameproject;

public class Hero extends Unit {

    final int SLOTS = 6;
    Equipment[] equippedItems;
    Inventory myInventory;
    int inventorySpace;
    long goldCoins;
    int baseAttackPower;
    public Spell selectedSpell;
    int baseIntelligence; // added by Alex
    int intelligence;
    int maxArmor;
    int baseMaxHP;
    int armor; //Hunter     

    public Hero(Tile heroTile, int myMaxHP) {
        super(heroTile, myMaxHP, "Hero", 4);
        baseMaxHP = myMaxHP;
        goldCoins = 0;
        baseAttackPower = 20;
        attackPower = baseAttackPower;
        baseIntelligence = 50; // added by Alex
        intelligence = baseIntelligence;
        inventorySpace = 36;
        myInventory = new Inventory(36);
        maxMana = 100;
        baseMaxMana = maxMana;
        mana = maxMana;
        equippedItems = new Equipment[SLOTS];

        for (int i = 0; i < SLOTS; i++) {
            equippedItems[i] = null;
        }
    }

    public void heroMove(Tile newTile, StatusScreen myStatus) {
        moveToTile(newTile);
        if (newTile.myContents[2] instanceof LootBag) // this entire if statement could be converted into a more comprehensive pickUpItem function
        {
            long howMuchGold;
            String tempMessage;
            LootBag myGrabbedLoot;
            myGrabbedLoot = ((LootBag) newTile.myContents[2]);
            howMuchGold = myGrabbedLoot.goldCoins;
            if (myGrabbedLoot.droppedItems == null) {
                tempMessage = "You have picked up " + howMuchGold + " gold coins.";
            } else if (myGrabbedLoot.droppedItems.size() == 1) {
                tempMessage = "You have picked up an item, and " + howMuchGold + " gold coins.";
            } else {
                tempMessage = "You have picked up some items, and " + howMuchGold + " gold coins.";
            }
            if (pickUpItems(myGrabbedLoot)) {
                newTile.myContents[2] = null;
            } else {
                tempMessage = "Your Inventory is Full, but you grabbed the " + howMuchGold + " gold coins.";
            }
            myStatus.pushMessage(tempMessage);
        }
    }

    public void swap(MapObject objectToSwap) {
        Tile newTile = objectToSwap.currentTile;
        Tile oldTile = this.currentTile;
        this.moveToTile(newTile);
        objectToSwap.moveToTile(oldTile);
    }


    @Override
    public void takeDamage(int damageAmount) {
        hp -= damageAmount;
        if (hp <= 0) {
            deathFunction();
        }
    }

    public void attack(Unit recipient, StatusScreen myStatus, Tile myTiles[][]) {
        recipient.takeDamage(attackPower);
    }

    public boolean pickUpItems(LootBag target) { // this is still kinda poorly written

        goldCoins += target.goldCoins;
        target.goldCoins = 0;
        if (target.droppedItems != null) {
            for (int i = 0; i < target.droppedItems.size(); i++) {
                if (target.droppedItems.get(i) == null) {
                    return true;
                }
                if (myInventory.hasSpace()) {
                    myInventory.addItem(target.droppedItems.get(i));
                    target.droppedItems.remove(i);
                    i--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public void equip(Equipment addedEquipment, int slot) {
        equippedItems[slot] = addedEquipment;
        calculateStats();

    }

    public void unequip(Equipment removedEquip) {
        calculateStats();
    }

    public void calculateStats() {
        attackPower = baseAttackPower;
        intelligence = baseIntelligence;
        int oldMaxHP = maxHP;
        maxHP = baseMaxHP;
        for (int i = 0; i < SLOTS; i++) {
            if (equippedItems[i] != null) {
                attackPower += equippedItems[i].powerLevel;
                intelligence += equippedItems[i].intelligenceLevel;
                maxHP += equippedItems[i].armourLevel;

            }
        }
        hp = ((hp * maxHP) / (oldMaxHP)) + 1;
        if (hp > maxHP) {
            hp = maxHP;
        }
        // this will be a function that will make strength autmoaticallt update HP and damage, and will make equipping Items apply their relevant stat boosts
    }


    public void recover() {
        hp = maxHP;
        mana = maxMana;
    }

}
