import java.util.ArrayList;
import java.util.List;


public class Game_Objects {
    static PC pc = new PC();
    static ArrayList<Room> room = new ArrayList<Room>();

    static List<Object> NPCDataBase = new ArrayList<Object>();
    static List<Object> ItemDataBase = new ArrayList<Object>();


    public static void initializeNPCArray() {
        NPCDataBase.add(new NPC());
        NPCDataBase.add(new Demagogue());
        NPCDataBase.add(new Dragon());
        NPCDataBase.add(new Troll());
    }

    public static void initializeItemArray() {
        ItemDataBase.add(new Item());
        ItemDataBase.add(new Flaming_Sword());
        ItemDataBase.add(new Ring_of_Marvol());
        ItemDataBase.add(new Diamond_Ring());
    }

}
