import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Game_Logic {
	public Game_Logic() {
		Game_Objects.room.add(new Room(1));
		List<String> roomInfo = new ArrayList<>();
		try {
			roomInfo = readLines("Text Files/Room Descriptions.txt");
		}
		catch (IOException e) {
			e.printStackTrace();
			for (int i = 0; i < roomInfo.size(); i++) {
				String[] firstWord = roomInfo.get(i).split(" ");
				String[] everythingElse = roomInfo.get(i).split(" ");
				if (firstWord[0].equals("RoomName: ")) {
					int currentRoomSize = Game_Objects.room.size();
					Game_Objects.room.add(new Room(currentRoomSize));
					Game_Objects.room.get(Game_Objects.room.size() - 1).name = everythingElse[1];
					Game_Objects.room.get(Game_Objects.room.size() - 1).number = currentRoomSize;
					int roomCount = 0;
				}
			}
			Game_Objects.room.get(0).name = "In a dark cellar.";
			Game_Objects.room.get(0).desc.add("The walls are wet.");
			Game_Objects.room.get(0).desc.add("The rooms smells of mildew.");
			Game_Objects.room.get(0).desc.add("You are dizzy.");
			Game_Objects.room.get(0).desc.add("It is time to leave.");
			Game_Objects.room.get(0).exits.add("North 2");
			Game_Objects.room.get(0).exits.add("West 3");
		}
	}
public List<String> readLines(String filename) throws IOException {
	FileReader fileReader = new FileReader(filename);
	BufferedReader bufferedReader = new BufferedReader(fileReader);
	List<String> lines = new ArrayList<String>();
	String line = null;
	while((line = bufferedReader.readLine()) != null) {
		lines.add(line);
	}
	bufferedReader.close();
	return lines;
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
		if (x[0].equals("get")) {
			get(x);
		}
		if (x[0].equals("create")) {
			create_item(x);
		}
		if (x[0].equals("wear")) {
			Game_Objects.pc.wear(x);
		}
		if (x[0].equals("eq")) {
			Game_Objects.pc.eq();
		}
		if (x[0].equals("remove")) {
			Game_Objects.pc.remove(x);
		}
		if (x[0].equals("move")) {
			move(x);
		}
	}
	public void get(String[] x) {
		if (x.length == 1) {
			System.out.println("Get what exactly?");
		}
		if (x.length == 2) {
			for (int i = 0; i < Game_Objects.ItemDataBase.size(); i++) {

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
		public void create_item(String[] x) {
			if (x.length == 1) {
				System.out.println("Create what exactly?");
			}
			if (x.length == 2) {
				for (int i = 0; i < Game_Objects.ItemDataBase.size(); i++) {
					Item localItem = (Item) Game_Objects.ItemDataBase.get(i);
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
					for (int y = 0; y < Game_Objects.room.get(i).npc.size(); y++) {

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
				System.out.println("You are carrying: ");
				for (int i = 0; i < Game_Objects.pc.item.size(); i++) {
					System.out.println(Game_Objects.pc.item.get(i).name);
				}
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
		public void summon(String[]x) {
			if (x.length == 1) {
				System.out.println("Summon what exactly?");
			}
			if (x.length == 2) {
				for (int i = 0; i < Game_Objects.NPCDataBase.size(); i++) {
					NPC localNPC = (NPC) Game_Objects.NPCDataBase.get(i);
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
public void move(String[] x) {
		if (x.length == 1) {
			System.out.println("Move where?");
		}
		if (x.length == 2) {
			for (int i = 0; i < Game_Objects.room.size(); i++) {
				if (Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) {
					for (int y = 0; y < Game_Objects.room.get(i).exits.size(); y++) {
						String exitRequested = Game_Objects.room.get(i).exits.get(y);
						String[] exitArray = exitRequested.split(" ");
						if (x[1].equalsIgnoreCase(exitArray[1])) {
							Game_Objects.pc.inRoom = Integer.parseInt(exitArray[2]);
							System.out.println("You leave " + exitArray[1]);
							String[] badProgramming = new String[1];
							badProgramming[0] = "nothing";
							look(badProgramming);
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
			System.out.println("The Gods have looked kindly upon you " + Game_Objects.pc.name + " and granted you 100hp and 75 accuracy in order to begin the game.");
			Game_Objects.pc.hp = 100;
			Game_Objects.pc.accuracy = 75;
			Game_Objects.pc.inRoom = 1;
		}
	}


