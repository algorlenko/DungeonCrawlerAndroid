    package org.o7planning.a412gameproject;

    import android.graphics.Bitmap;
    import android.graphics.*;
    import android.graphics.BitmapFactory;
    import android.content.res.Resources;
    import android.graphics.Canvas;
    import android.graphics.Color;

    import android.view.MotionEvent;

    import java.io.IOException;
    import java.io.InputStream;
    import java.util.ArrayList; // import the ArrayList class

    public class GameEngine extends GameState {

        public GameScreen thisScreen;

        public int currentFloor;
        //public Hero myHero; now all the states have a hero, who will be the same every time. This is how I am currently tackling having a shared inventory.
        Tile[][] myTiles;
        public Bitmap statusImage;
        ArrayList<Monster> myMonsters;

    ArrayList<FriendlyCreature> friendlyCreatures; // Gorlenko Added this
     public FriendlyCreature selectedAlly;


     public Rect myRectangle;


        public Point hoveredTile;

        ArrayList<Wall> myWalls;

        public Monster selectedMonster;
        public int monsterIndex;

        Unit turnHolder;

        public Door myDoor;

        public final int UNITLAYER = 3; //if you change this unitlayer, you need to also change it in the Unit class.

        public int dungeonColumns;
        public int dungeonRows;

        public GameStateManager myGSM;

        public StatusScreen myStatus;

        public Bitmap emptyImage;
        public Bitmap[] FPStest; //delete everything associated with this hardcoded garbage very soon.
        public int heroFrame; // delete this
        public Spell selectedSpell;
    public ShopKeeper localShopKeep;

    int experimentalHeight;
    int experimentalWidth;

        public Canvas myGraphic;


        public GameEngine(GameScreen myScreen, GameStateManager passedGSM) throws IOException {
myGraphic = new Canvas(); // this is a test
            experimentalHeight = 100;
            experimentalWidth = 100;

            myRectangle = new Rect();
           currentFloor = 1;

            dungeonColumns = 6;
            dungeonRows = 6;
            thisScreen = myScreen;
            //statusImage = BitmapFactory.decodeFile(R.drawable.statushud);
           // statusImage = thisScreen.generateImage(R.drawable.statushud);
            statusImage = thisScreen.generateImage(R.drawable.empty);
            myTiles = new Tile[dungeonColumns][dungeonRows];
            hoveredTile = new Point(-1, -1);
            Typeface GeneralFont = Typeface.defaultFromStyle(Typeface.NORMAL);
         //   myScreen.canvas.setTypeFace(GeneralFont);




            myGSM = passedGSM;
            //emptyImage = generateImage
            for (int i = 0; i < dungeonColumns; i++) {
                for (int j = 0; j < dungeonRows; j++) {
                    myTiles[i][j] = new Tile(i, j, R.drawable.ground_dirt_dark_3, this);
                    //myTiles[i][j].syncTileWithScreen();
                }

            }

            FPStest = new Bitmap [4];
            FPStest[0] = thisScreen.generateImage(R.drawable.dknight_1); // delete all this
            FPStest[1] = thisScreen.generateImage(R.drawable.dknight_2);
            FPStest[2] = thisScreen.generateImage(R.drawable.dknight_3);
            FPStest[3] = thisScreen.generateImage(R.drawable.dknight_4);
            heroFrame = 0;
            selectedSpell = null; // Alex added this


            friendlyCreatures = new ArrayList<FriendlyCreature>(); // Gorlenko added this
            myMonsters = new ArrayList<Monster>(); // Create an ArrayList object
            myMonsters.add(new Monster(4, 4, R.drawable.beetle_fire_giant_1, new Equipment(R.drawable.mace3, 10,0,0, "Mace of Power : damage + 10", "MaceOfPower", "Weapon", this), 100, this));
            myMonsters.add(new Monster(4, 3, R.drawable.cultist_3, new InventoryItem(R.drawable.key_gold, "The key to the next level.", "L1Key", this), 100, this));
            myWalls = new ArrayList<Wall>();

            for (int i = 1; i < dungeonColumns - 1; i++)
            {
                myWalls.add(new Wall(i, 0, R.drawable.wall_hedge_15, this));
            }
            for (int i = 0; i < dungeonColumns; i++)
            {
                for(int j=0;j<dungeonRows;j++){
                    if( j==dungeonRows-1 || i==0 || i==dungeonColumns-1)
                        myWalls.add(new Wall(i, j, R.drawable.wall_hedge_7, this));
                }

            }

    //        myWalls.add(new Wall(2, 2, myTiles, R.drawable.Floor/uf_terrain/wall_hedge_15));
    //        myWalls.add(new Wall(3, 3, myTiles, R.drawable.Floor/uf_terrain/wall_hedge_15));

            myHero = new Hero(1, 1, R.drawable.dknight_1, 100, this);
            turnHolder = myHero;
            myStatus = new StatusScreen(this);
            myDoor = new Door(2, 2, this);
            localShopKeep = new ShopKeeper(3, 3, this);
            this.myStatus.pushMessage("You wake up in an open garden. How ");
            this.myStatus.pushMessage("did you get here? There is a crazy ");
            this.myStatus.pushMessage("person guarding the only way out of here.");
        }

        public void makeNewLevel() throws IOException {

            try{
                // restore this asap HIGH PRIORITY
            //LevelGenerator.makeNewLevel(this);
            }
            catch (Exception exc){


            }
        }

     /*   @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_I) {
                myHero.selectedSpell= null;
                thisScreen.resetCursor();
                myGSM.setState(1); // Goes to Inventory Screen
            }

            if (key == KeyEvent.VK_ESCAPE) {
                if (myHero.selectedSpell == null){
                    myGSM.setState(2); // Goes to Main Menu
                }
                else{
                    myHero.selectedSpell= null;
                    thisScreen.resetCursor();
                }

            }

            if (key == KeyEvent.VK_P) {
                thisScreen.resetCursor();
                myHero.selectedSpell= null;
                myGSM.setState(3);
            }

            if (key == KeyEvent.VK_S) {
                spellButtonClicked();
                return;
            }

            if (myHero.isAlive) {
                if (turnHolder == myHero) {
                    if (key == KeyEvent.VK_LEFT) {
                        if (myHero.move(-1, 0, myTiles, dungeonColumns, dungeonRows, myStatus) == true) {
                            successfulTurn();
                        }

                    }
                    if (key == KeyEvent.VK_RIGHT) {
                        if (myHero.move(1, 0, myTiles, dungeonColumns, dungeonRows, myStatus) == true) {
                            successfulTurn();
                        }

                    }
                    if (key == KeyEvent.VK_UP) {
                        if (myHero.move(0, -1, myTiles, dungeonColumns, dungeonRows, myStatus) == true) {
                            successfulTurn();
                        }

                    }
                    if (key == KeyEvent.VK_DOWN) {
                        if (myHero.move(0, 1, myTiles, dungeonColumns, dungeonRows, myStatus) == true) {
                            successfulTurn();
                        }

                    }

                    if (turnHolder instanceof Monster) {
                        try // I fucking hate Java for this, I need to get rid of this garbage, but I can't currently, it is all because I need to generate a Treasure Chest Image in the Monster Class for the LootBag
                        {
                            if (turnHolder instanceof Monster) {
                                monsterTurn();
                            }
                        } catch (Exception exc) {
                            exc.printStackTrace();

                        }
                        turnHolder = myHero;
                    }

                }
            }

        }
    */


        public void attemptUsage(Point selectedTile) {
            if (myTiles[selectedTile.x][selectedTile.y].myContents[UNITLAYER] instanceof Useable) {
                if (selectedTile.x <= myHero.x + 1 && selectedTile.x >= myHero.x - 1 && selectedTile.y <= myHero.y + 1 && selectedTile.y >= myHero.y - 1) {
                    int useResult = ((Useable) myTiles[selectedTile.x][selectedTile.y].myContents[UNITLAYER]).tryUse();
                    if (useResult == 3) // if the object you used was a shopkeeper
                    {
                        successfulTurn();
                        try {
                            monsterTurn(); // Monsters will still get their turn to avoid glitches, I may change this later.
                        } catch (Exception exc) {
                            exc.printStackTrace();

                        }
                        myGSM.setState(5); // set State to Shop State
                    }
                    if (useResult == 1) {
                        successfulTurn();
                        try {
                            monsterTurn();
                        } catch (Exception exc) {
                            exc.printStackTrace();

                        }

                    } else if (useResult == 2) {
                        try {
                            makeNewLevel();
                        } catch (Exception exc) {
                            exc.printStackTrace();

                        }
                    }
                } else {
                    myStatus.pushMessage("Your Target is out of Range.");
                }

            }
        }

        public void attemptAttack(Point selectedTile) {
            if (myTiles[selectedTile.x][selectedTile.y].myContents[UNITLAYER] instanceof Monster) {
                if (selectedTile.x <= myHero.x + 1 && selectedTile.x >= myHero.x - 1 && selectedTile.y <= myHero.y + 1 && selectedTile.y >= myHero.y - 1) {
                    try // I fucking hate Java for this, I need to get rid of this garbage, but I can't currently, it is all because I need to generate a Treasure Chest Image in the Monster Class for the LootBag
                    {
                        myHero.attack((Unit) myTiles[selectedTile.x][selectedTile.y].myContents[UNITLAYER], myStatus, myTiles);
                        successfulTurn();
                        if (turnHolder instanceof Monster) {
                            monsterTurn();
                        }
                    } catch (Exception exc) {
                        exc.printStackTrace();

                    }
                } else {
                    myStatus.pushMessage("Your Target is out of Range.");
                }
            } else {
                myStatus.pushMessage("You cannot Attack this area.");
            }

        }

        public void successfulTurn() { // this Function modified by Gorlenko
            if (turnHolder == myHero) {
                if(friendlyCreatures.size() != 0)
                {
                    turnHolder = friendlyCreatures.get(0);
                    allyTurn();
                    if(myMonsters.size() != 0)
                    {
                        turnHolder = myMonsters.get(0);
                    }
                    else
                    {
                        turnHolder = myHero;
                    }
                }
                else if(myMonsters.size() != 0)
                {
                turnHolder = myMonsters.get(0);
                return;
                }
            }


                    if (turnHolder == myHero && myMonsters.size() != 0) {
                turnHolder = myMonsters.get(0);
                return;
            }
            // if (turnHolder instanceof Monster) {
            //      if (monsterIndex == myMonsters.length - 1) {
            //         turnHolder = myHero;
            //      }
            //  }
        }

        public void allyTurn() // This function was added by Gorlenko
        {
            if(friendlyCreatures.size() == 0)
            {
                return;
            }
            else
            {
                for (int i = 0; i < friendlyCreatures.size(); i++) {

                if (friendlyCreatures.get(i).isAlive == false) {
                    friendlyCreatures.remove(i);
                    if (i != friendlyCreatures.size()) {
                        i--;
                        continue;
                    } else {
                        break;
                    }

                }

                if (friendlyCreatures.size() == 0) {
                    break;
                }

                turnHolder = friendlyCreatures.get(i);
                selectedAlly = (FriendlyCreature) turnHolder; //this may need improvments
                try
                {
                selectedAlly.aiAction();
                }
                catch(Exception exc)
                {

                }
                if (friendlyCreatures.get(i).isAlive == false) {
                    friendlyCreatures.remove(i);
                    if (i != friendlyCreatures.size()) {
                        i--;
                    }
                }

            }

            }

        }


        public void monsterTurn() {
            for (int i = 0; i < myMonsters.size(); i++) {

                if (myMonsters.get(i).isAlive == false) {
                    myMonsters.remove(i);
                    if (i != myMonsters.size()) {
                        i--;
                        continue;
                    } else {
                        break;
                    }

                }

                if (myMonsters.size() == 0) {
                    break;
                }

                turnHolder = myMonsters.get(i);
                selectedMonster = (Monster) turnHolder; //this may need improvments
                selectedMonster.aiAction();

                if (myMonsters.get(i).isAlive == false) {
                    myMonsters.remove(i);
                    if (i != myMonsters.size()) {
                        i--;
                    }
                }

            }
            turnHolder = myHero;
            if (!myHero.isAlive) {
                myHero.selectedSpell= null;
                myGSM.setState(4);
            }
        }

      /*  public void mouseMoved(MouseEvent e) {
            hoveredTile = calculateTile(e.getX(), e.getY());

            if (hoveredTile.x < dungeonColumns && hoveredTile.x >= 0 && hoveredTile.y < dungeonRows && hoveredTile.y >= 0) {
                return;
            }
            hoveredTile.x = -1;
            hoveredTile.y = -1;
        }
    */
        public void mouseClicked(MotionEvent e) {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {

                if (e.getY() >= thisScreen.getHeight() * 9 / 10) // Alex added this statement
                {
                    spellButtonClicked();
                    return;
                }

            if (e.getY() >= thisScreen.getHeight() * 4 / 5) // Alex added this statement
            {
                myGSM.setState(1); // takes us to Inventory
                return;
           }


            // I might remove this condition

                Point selectedTile = calculateTile((int) e.getX(), (int) e.getY());
                if (selectedTile.x < dungeonColumns && selectedTile.x >= 0 && selectedTile.y < dungeonRows && selectedTile.y >= 0) {
// TODO fix any other errors hanging out here, i had a rediculous one in here so there could be many more logic errors, delete this todo if there aren't
                    if (myHero.isAlive) {
                        if (turnHolder == myHero) {
                            if (selectedSpell == null) // this condition was added by Alex
                            {
                                if ((selectedTile.x - myHero.x) * (selectedTile.x - myHero.x) < 4 && (selectedTile.y - myHero.y) * (selectedTile.y - myHero.y) < 4) {
                                    if (myTiles[selectedTile.x][selectedTile.y].myContents[UNITLAYER] == null || myTiles[selectedTile.x][selectedTile.y].myContents[UNITLAYER] instanceof FriendlyCreature) {

                                        myHero.heroMove(selectedTile.x - myHero.x, selectedTile.y - myHero.y);
                                        // TODO clean up the entire hero move function.
                                        //myHero.move(selectedTile.x - myHero.x, selectedTile.y - myHero.y, myTiles, dungeonColumns, dungeonRows, myStatus); // You need to click to move
                                        //TODO the AI is a little funky
                                        successfulTurn();
                                        monsterTurn();

                                        // TODO clean this up into one elegant hero move function
                                        return;
                                    }
                                    attemptAttack(selectedTile);
                                    attemptUsage(selectedTile);
                                }
                            }else // this else was added by Alex
                            {
                                selectedSpell.castSpell(myHero, selectedTile.x, selectedTile.y);
                                if (turnHolder instanceof Monster) {
                                    monsterTurn();

                                }
                            }
                        }

                    }
                }
           }
        }

        public void spellButtonClicked() // Alex added this
        {
            myGSM.setState(6); // goes to SpellBook
        }

        public Point calculateTile(int x, int y) {
            Point myReturn = new Point();
         //   myReturn.x = x * dungeonColumns / thisScreen.getMeasuredWidth();
      //      myReturn.y = y * dungeonRows / ((thisScreen.getMeasuredHeight() / 5) * 4);

           // myReturn.x = x * dungeonColumns / thisScreen.getWidth();
        //    myReturn.y = y * dungeonRows / ((thisScreen.getHeight() / 5) * 4);
            myReturn.x = x * dungeonColumns / experimentalWidth;
            myReturn.y = y * dungeonRows / experimentalHeight;
            return myReturn;
        }



        public void draw(Canvas canvas) throws IOException {
           // Canvas canvas = thisScreen.canvas;

            myGraphic = canvas;

            drawTiles();
            drawStatus();
           // drawHovering(myGraphic);
        }

        private void drawTiles() {

         //   int myHeight = (thisScreen.myBufferedDimension.getHeight() / 5) * 4;
         //   int myWidth = thisScreen.myBufferedDimension.getWidth();
            int myHeight = (myGraphic.getHeight() / 5) * 4;
            int myWidth = myGraphic.getWidth();

            experimentalHeight = myHeight;
            experimentalWidth = myWidth;


            for (int i = 0; i < dungeonColumns; i++) {
                for (int j = 0; j < dungeonRows; j++) {
                    if (myTiles[i][j].myContents[UNITLAYER] == myHero) //delete this
                    { // delete this
                        myTiles[i][j].image[UNITLAYER] = myHero.image;// delete this

                    } //delete this
                    for (int layer = 0; layer < 4; layer++) {
                        if (myTiles[i][j].imageName[layer] != R.drawable.empty) {
                            myRectangle.set( (myWidth / dungeonColumns) * i, (myHeight / dungeonRows) * j, (myWidth / dungeonColumns) * (i + 1), (myHeight / dungeonRows) * (j + 1));
                            myGraphic.drawBitmap(myTiles[i][j].image[layer],null, myRectangle, null);
                        }

                    }

                }
            }
            heroFrame += 1; // delete this
            heroFrame %= 4;
            myHero.image = FPStest[heroFrame]; // delete this
        }

      /*  public void drawHovering(Graphics2D myGraphic) {
            if (hoveredTile.x != -1 && hoveredTile.y != -1) {
                int myHeight = (thisScreen.myBufferedDimension.height / 5) * 4;
                int myWidth = thisScreen.myBufferedDimension.width;
                MapObject hoveredObject = myTiles[hoveredTile.x][hoveredTile.y].myContents[UNITLAYER];
                if (hoveredObject instanceof Unit) {
                    Unit hoveredUnit = (Unit) hoveredObject;
                    myGraphic.drawString(hoveredUnit.hp + " / " + hoveredUnit.maxHP, ((myWidth / dungeonColumns) * hoveredTile.x), (myHeight / dungeonRows) * hoveredTile.y);
                }
            }
        }*/

        private void drawStatus() throws IOException {

            //int myHeight = (thisScreen.myBufferedDimension.getHeight() / 5) * 4;
            //  int myWidth = thisScreen.myBufferedDimension.getWidth();
            int myHeight = (myGraphic.getHeight() / 5) * 4;
            int myWidth = myGraphic.getWidth();
            myStatus.drawStatus();
        }

    }
