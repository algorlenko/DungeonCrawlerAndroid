package org.o7planning.a412gameproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;


import java.io.IOException;

public class DiaryState extends GameState {

    // This could be used for...LORE
    
    public GameStateManager myGSM;
    public GameScreen thisScreen;
    public Bitmap menuImage;
    public Bitmap selector;

    public DiaryState(GameScreen myScreen, GameStateManager passedGSM) throws IOException {
        thisScreen = myScreen;
        myGSM = passedGSM;
       // menuImage = generateImage(R.drawable.Pause_Menu_II); /* CHANGE THIS */
    }

    @Override
    public void draw(Canvas canvas) throws IOException {

    }

    @Override
    public void mouseClicked(MotionEvent e) {

    }
}

/* So...here's how it works: essentially an inventory-esque screen, or a journal
if you will. The left side is for quest-related stuff; if you were to, say, meet
a friendly named NPC the left side will contain a "note" that you can click on for 
information about them. We could also 
The right side, meanwhile, is where all "lore" goes. We could have droppable inventory
items - journals, notes, encrypted messages from the future - that will appear on 
the right side of this screen instead of in your inventory, and when you click on them
you'll get to read the message/journal entry/book/whatever.


*/