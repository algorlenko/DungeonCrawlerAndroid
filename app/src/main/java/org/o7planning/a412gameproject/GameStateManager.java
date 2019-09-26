package org.o7planning.a412gameproject;

import java.util.ArrayList;
import java.io.IOException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import java.util.*;

public class GameStateManager {

    public ArrayList<GameState> gameStates;
    public GameScreen myScreen;
    private int currentState;
    public Hero myHero;
    GameEngine myGameEngine;
    Map<String, Bitmap> allImages;
    //public Design.Hero hero;
    public static final int MAINMENUSTATE = 2;
    public static final int ADVENTURESTATE = 0; // these numbers will be flipped later
    public static final int INVENTORYSTATE = 1;
    public static final int PAUSEMENUSTATE = 3;
    public static final int DEATHSTATE = 4;
    public static final int SHOPSTATE = 5;
    public static final int SPELLBOOKSTATE = 6; // Alex added this

    //   public static final int SPELLBOOKTATE = 3;
    //   public static final int NEWCHARACTERSTATE = 4;
    public GameStateManager(GameScreen passedScreen) throws IOException {
        // hero = new Design.Hero(R.drawable.player/base/demonspawn_black_m);
        gameStates = new ArrayList<GameState>();
        myScreen = passedScreen;
        allImages = new HashMap<String, Bitmap>();
        loadImages();

        // currentState = ADVENTURESTATE;
        //currentState = ADVENTURESTATE;
        myGameEngine = new GameEngine(myScreen, this); // Alex added this
        gameStates.add(myGameEngine); // Alex added this
        myHero = gameStates.get(0).myHero;
        gameStates.add(new InventoryState(myScreen, this));
        gameStates.add(new MainMenuState(myScreen, this));
        gameStates.add(new PauseMenuState(myScreen, this));
        gameStates.add(new DeathState(myScreen, this));
        gameStates.add(new ShopState(myScreen, this));
        gameStates.add(new SpellBookState(myScreen, this, myGameEngine)); //Alex added this
        setState(ADVENTURESTATE);
        //gameStates.add(new AdventureState(this));
        //gameStates.add(new InventoryState(this));
        //gameStates.add(new SpellBookState(this));
        //gameStates.add(new NewCharacterState(this));

    }

    public void resetGame() {
        try {
            gameStates.set(ADVENTURESTATE, new GameEngine(myScreen, this));
            myHero = gameStates.get(ADVENTURESTATE).myHero;
            gameStates.set(INVENTORYSTATE, new InventoryState(myScreen, this));
            gameStates.set(SHOPSTATE, new ShopState(myScreen, this));
            GameEngine myGameEngine = (GameEngine) gameStates.get(ADVENTURESTATE);
            gameStates.set(SPELLBOOKSTATE, new SpellBookState(myScreen, this, myGameEngine));
            
            setState(0);
        } catch (Exception exc) {

        }
    }

    public void setState(int state) {

        currentState = state;

        //       gameStates.get(currentState).draw(Canvas canvas); //there is no reason this should not work
        //gameStates.get(currentState).init();
//        myScreen.repaint();
    }

    public void update() {

        //  gameStates.get(currentState).update();
    }

    public void loadImages(){
        allImages.put("Hero0", myScreen.generateImage(R.drawable.dknight_1));
        allImages.put("Hero1", myScreen.generateImage(R.drawable.dknight_2));
        allImages.put("Hero2", myScreen.generateImage(R.drawable.dknight_3));
        allImages.put("Hero3", myScreen.generateImage(R.drawable.dknight_4));
        allImages.put("Door0", myScreen.generateImage(R.drawable.runed_door));
        allImages.put("Door1", myScreen.generateImage(R.drawable.open_door));
        allImages.put("Shopkeeper0", myScreen.generateImage(R.drawable.merchant_a_shopkeeper));
        allImages.put("Wall", myScreen.generateImage(R.drawable.wall_hedge_7));
        allImages.put("Floor", myScreen.generateImage(R.drawable.ground_dirt_dark_3));
        allImages.put("Cultist0", myScreen.generateImage(R.drawable.cultist_1));
        allImages.put("Cultist1", myScreen.generateImage(R.drawable.cultist_2));
        allImages.put("Cultist2", myScreen.generateImage(R.drawable.cultist_3));
        allImages.put("Cultist3", myScreen.generateImage(R.drawable.cultist_4));
        allImages.put("BeetleFireGiant0", myScreen.generateImage(R.drawable.beetle_fire_giant_1));
        allImages.put("BeetleFireGiant1", myScreen.generateImage(R.drawable.beetle_fire_giant_2));
        allImages.put("BeetleFireGiant2", myScreen.generateImage(R.drawable.beetle_fire_giant_3));
        allImages.put("BeetleFireGiant3", myScreen.generateImage(R.drawable.beetle_fire_giant_4));
        allImages.put("Satchel", myScreen.generateImage(R.drawable.satchel));
        allImages.put("SilverChest", myScreen.generateImage(R.drawable.chest_silver));
    }

    public void draw(Canvas canvas) {

        try {
            gameStates.get(currentState).draw(canvas);
        } catch (Exception exc) {
            exc.printStackTrace();

        }

    }



    public void mouseClicked(MotionEvent e) {
        //TODO make a decision, either have the checker here, or in the states.
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            gameStates.get(currentState).mouseClicked(e);
        }
        //   gameStates.get(currentState).draw(Canvas canvas);
        //    myScreen.repaint();
    }


}
