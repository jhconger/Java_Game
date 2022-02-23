//import NPCS.NPC;
//import Items.Item;
import java.util.Scanner;

public class Game_Logic {
	public Game_Logic() {
		Game_Objects.room.add(new Room(1));
		Game_Objects.room.get(0).name = "In a dark cellar.";
		Game_Objects.room.get(0).desc.add("Desc Line 1");
		Game_Objects.room.get(0).desc.add("Desc Line 2");
		Game_Objects.room.get(0).desc.add("Desc Line 3");
		Game_Objects.room.get(0).desc.add("Desc Line 4");
		Game_Objects.room.get(0).exits.add("North 2");
		Game_Objects.room.get(0).exits.add("West 3");
	}

	public void waitForCommand() {
		if (Game_Objects.pc.inRoom == 0) {
			createCharacter();
		}
		System.out.println("What to do?");
		Scanner sc = new Scanner(System.in);
		String com = sc.nextLine();
		// parse the command by spaces
		// read each word into an array valueString s = "This is a sample
		// sentence.";
		String[] words = com.split(" ");
		processCommand(words);

	}

	public void processCommand(String[] x) {
		if (x[0].equals("look")) {
			look(x);
		}
		if (x[0].equals("summon")) {
			summon(x);
		}
		if (x[0].equals("create")) {
			create_item(x);
		}
		if (x[0].equals("get")) {
			get(x);
		}

	}

	public void get(String[] x) {
		if (x.length == 1) {
			System.out.println("Get what exactly?");
		}
		if (x.length == 2) {
			for (int i = 0; i < Game_Objects.ItemDatabase.size(); i++) {

				for (int y = 0; y < Game_Objects.room.size(); y++) {

					if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) {
						for (int z = 0; z < Game_Objects.room.get(y).item.size(); z++) {
							if (x[1].equalsIgnoreCase(Game_Objects.room.get(y).item.get(z).id)) {
								Item localItem = Game_Objects.room.get(y).item.get(z);

								Game_Objects.pc.item.add(localItem);
								System.out.println("You pick up a " + localItem.name);
								Game_Objects.room.get(y).item.remove(z);
								break;
							}
						}
					}
				}
			}
		}
	}
		public void create_item (String[]x){
			if (x.length == 1) {
				System.out.println("Create what exactly?");
			}
			if (x.length == 2) {
				for (int i = 0; i < Game_Objects.ItemDatabase.size(); i++) {
					Item localItem = (Item) Game_Objects.ItemDatabase.get(i);
					if (localItem.id.equalsIgnoreCase(x[1])) {
						for (int y = 0; y < Game_Objects.room.size(); y++) {
							if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) {
								try {
									Game_Objects.room.get(y).item.add((Item) Class.forName(localItem.id).newInstance());
								} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
									e.printStackTrace();
								}
								System.out.println("You create a "
										+ Game_Objects.room.get(y).item.get(Game_Objects.room.get(y).item.size() - 1).name);
							}
						}
					}
				}
			}
		}
	public void look(String[] x) {
		if (x.length == 1) {
			for (int i = 0; i < Game_Objects.room.size(); i++) {
				if (Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) {
					System.out.println(Game_Objects.room.get(i).name);
					for (int y = 0; y < Game_Objects.room.get(i).desc.size(); y++) {
						System.out.println(Game_Objects.room.get(i).desc.get(y));
					}
					System.out.println("Exits: ");
					for (int y = 0; y < Game_Objects.room.get(i).exits.size(); y++) {

						String exitNameFull = Game_Objects.room.get(i).exits.get(y);
						String[] exitName = exitNameFull.split(" ");
						System.out.println(exitName[0]);
					}
					for (int y = 0; y < Game_Objects.room.get(i).exits.size(); y++) {

						System.out.println(Game_Objects.room.get(i).npc.get(y).desc);
					}
					for (int y = 0; y < Game_Objects.room.get(i).item.size(); y++) {

						System.out.println(Game_Objects.room.get(i).item.get(y).desc);
					}
				}
			}
		}
		if (x.length == 2) {
			if (x[1].equals("self")) {
				Game_Objects.pc.look();

			}
			for (int y = 0; y < Game_Objects.room.size(); y++) {
				if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) {
					for (int i = 0; i < Game_Objects.room.get(y).npc.size(); i++) {
						if (x[1].equalsIgnoreCase(Game_Objects.room.get(y).npc.get(i).id)) {
							Game_Objects.room.get(y).npc.get(i).look();
						}
					}
				}
			}
		}
	}



		public void summon (String[]x) {
			if (x.length == 1) {
				System.out.println("Summon what exactly?");
			}
			if (x.length == 2) {
				for (int i = 0; i < Game_Objects.NPCDatabase.size(); i++) {
					NPC localNPC = (NPC) Game_Objects.NPCDatabase.get(i);
					if (localNPC.id.equalsIgnoreCase(x[1])) {
						for (int y = 0; y < Game_Objects.room.size(); y++) {
							if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) {
								try {
									Game_Objects.room.get(y).npc.add((NPC) Class.forName(localNPC.id).newInstance());
									System.out.println("You summon a " + Game_Objects.room.get(y).npc.get(Game_Objects.room.get(y).npc.size() - 1).name);
								} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}

		public void createCharacter () {
			System.out.println("Welcome to the game.  What is your name?");
			Scanner sc = new Scanner(System.in);
			Game_Objects.pc.name = sc.next();
			System.out.println("The Gods have looked kindly upon you and granted you 100hp and 75 accuracy in order to begin the game.");
			Game_Objects.pc.hp = 100;
			Game_Objects.pc.accuracy = 75;
			Game_Objects.pc.inRoom = 1;
		}
	}


