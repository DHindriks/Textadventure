public class Player {
	private Room currentRoom;
	private int health = 100;
	private Inventory inventory; 

	public Player() {
		inventory = new Inventory();
	}
	
	public int GetHealth() {
		return health;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	public void DropItem(Command command) {
		
	}
	
	public  void TakeItem(String command) {
		if (inventory.GetMaxsize() <= 8) {
			System.out.printf("You take the " + command + "\n");
			currentRoom.removeItem(command);
			//inventory.AddItem(command, type);
		}
	}
	
	
	public String Getinventory(){
		return inventory.GetItemlist();
	}
	
	public void damage(int amount) {
		health -= amount;
		System.out.printf("took " + amount + " damage." + "\n");
		System.out.printf("Your health is now " + health + "." + "\n");
	}

	public void heal(int amount) {
		health += amount;
		System.out.printf("You healed your wounds, restoring " + amount + " health." + "\n");
		System.out.printf("Your health is now " + health + "." + "\n");
	}
	}
