import java.util.ArrayList;
import java.util.List;

public class Game_Objects {
    static PC pc = new PC();
    static ArrayList<Room> room = new ArrayList<Room>();

    static List<Object> NPCDatabase = new ArrayList<Object>();
    static List<Object> ItemDatabase = new ArrayList<Object>(


    );

    public static void initializeNPCArray() {
        NPCDatabase.add(new NPC());
        NPCDatabase.add(new Demagogue());
        NPCDatabase.add(new Dragon());
        NPCDatabase.add(new Troll());
    }

    public static void initializeItemArray() {
        ItemDatabase.add(new Item());
        ItemDatabase.add(new Flaming_Sword());
        ItemDatabase.add(new Ring_of_Marvol());
    }

}
