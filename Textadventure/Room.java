import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  For each existing exit, the room
 * stores a reference to the neighboring room.
 *
 * @author  Michael Kolling and David J. Barnes	
 * @version 1.0 (February 2002)
 */

class Room
{
    private String description;
    private String name;
    private String type;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private HashMap<String, String> items;		// stores items in a room.
    
    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court
     * yard".
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new HashMap<String, String>();
    }

    /**
     * Define an exit from this room.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }
    
    public void setItem(String name, String type)
    { 
    	items.put(name, type);
    	this.name = name;
    	this.type = type;
    }
    
    public void removeItem(String command)
    {
    	items.remove(command);
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, in the form:
     *     You are in the kitchen.
     *     Exits: north west
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    public String getItems()
    {
    	if (name != null) {
    		return  itemList();
    	}
    	else {
    		name = "nothing";
    		return "found " + name + "\n";
    	}
    }
    
    
    private String itemList()
    {
    	String returnString = "You search the room, you find: " + "\n";
        Set<String> keys = items.keySet();
        for(Iterator<String> iter = keys.iterator(); iter.hasNext(); )
            returnString += " " + "\n" + iter.next();
        return returnString;
    }
    
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(Iterator<String> iter = keys.iterator(); iter.hasNext(); )
            returnString += " " + iter.next();
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction)
    {
        return (Room)exits.get(direction);
    }
}
