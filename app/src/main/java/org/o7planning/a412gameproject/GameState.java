package org.o7planning.a412gameproject;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.io.IOException;

public abstract class GameState {

    protected GameStateManager gsm;

    //public abstract void init();
    //public abstract void update();
    public Hero myHero;

    public abstract void draw(Canvas canvas) throws IOException;

    public abstract void mouseClicked(MotionEvent e);

}
