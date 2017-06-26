/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Game
{
    private Parser parser;
    private Player player;
    private boolean finished;
    //private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        parser = new Parser();
        player = new Player();
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room cell, hallway, lab, basement;

        // create the rooms
        cell = new Room("in a cell");
        hallway = new Room("in a hallway");
        lab = new Room("in a destroyed lab");
        basement = new Room("in the basement");

        // initialise room exits
        cell.setExit("door", hallway);
        cell.setItem("key", "key");
        cell.setItem("crowbar", "weapon");
        
        hallway.setExit("door", cell);
        hallway.setExit("down", basement);
        hallway.setExit("left", lab);
        
        lab.setExit("right", hallway);
        
		player.setCurrentRoom(cell);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            Alive();
            
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Adventure!");
        System.out.println("Adventure is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);
        else if (commandWord.equals("look"))
        	Look();
        else if (commandWord.equals("inspect"))
        	inspect(command);
        else if (commandWord.equals("take"))
        	take(command); 
        else if (commandWord.equals("inventory"))
        	inventory();
        else if (commandWord.equals("test"))
        	test();
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("You wake up, locked in a cell.");
        System.out.println("You don't hear anybody, how did you end up here?");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            player.setCurrentRoom(nextRoom);
            System.out.printf("You are bleeding, you lose health everytime you move." + "\n");
            player.damage(5);
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
    }
    

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
    
    /**
     * Player inspects the current room or object in the current room
     */
    private void inspect(Command command) 
    {
    	if (command.hasSecondWord()) {
    		System.out.printf("Inspect what?");
    	}
    	else 
    	{ 
    		
    	}
    }
    
    private void test() {
    	
    }

    private void Look()
    {
    	System.out.println(player.getCurrentRoom().getLongDescription());
    	System.out.println(player.getCurrentRoom().getItems());
    }
    
    private void take(Command command)
    {
    	if (command.hasSecondWord()) {
    		player.TakeItem(command.getSecondWord());
    	}
    	else {
    		System.out.printf("Take what?" + "\n");
    	}
    }
    
    private void inventory() 
    {
    	System.out.printf(player.Getinventory());
    }
    
    /**
     * Checks if player is alive after a command is entered , quits the game when the player has no health left 
     */
    private void Alive() {
    	if (player.GetHealth() == 0) {
    		finished = true;
    		System.out.printf("You died." + "\n" + "Game over." + "\n");
    	}
    }

    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
    }
}
