import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Inventory {
	private HashMap<String, String> Itemlist;

	public Inventory() 
	{
		// TODO Auto-generated constructor stub
		Itemlist = new HashMap<String, String>();
	}
	
	
	public int GetMaxsize() 
	{
		return Itemlist.size();
	}
	
	public void AddItem(String name, String type) 
	{
		if (Itemlist.size() <= 8) 
		{
			Itemlist.put(name, type);
		}
		else 
		{
			System.out.printf("Your inventory is full, You'll need to drop something else before picking this up" + "\n");
		}
	}
	
	public String GetItemlist()
	{
		String returnString;
		if (Itemlist.size() == 0)
		{
			returnString = "Your inventory is empty right now." + "\n" + "Space: 0/8" + "\n";
			return returnString + "\n";
		}
		
		if (Itemlist.size() == 8)
        {
        returnString = "Your inventory is full, you can't carry any more ";
        }
		
		returnString = "Your items: " + "\n" + "Inventory space " + Itemlist.size() + "/8" + "\n";
        Set<String> keys = Itemlist.keySet();
        for(Iterator<String> iter = keys.iterator(); iter.hasNext(); )
            returnString += " " + iter.next();
        return returnString;
	}

}
