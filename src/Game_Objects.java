import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Game_Objects {
    static PC pc = new PC();
    static ArrayList<Room> room = new ArrayList<Room>();
    static List<Object> NPCDatabase = new ArrayList<Object>();

    public static void initializeNPCArray() {
        NPCDatabase.add(new NPC());
        NPCDatabase.add(new Demagogue());
    }
}