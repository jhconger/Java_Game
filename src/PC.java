import Items.Item;

import java.util.ArrayList;

public class PC {
	String name;
	int hp;
	int accuracy;
	int inRoom = 0;
	ArrayList<Item> item = new ArrayList<Item>();

	public void look() {

		 System.out.println("accuracy: " + accuracy);
		System.out.println("hp: " + hp);
	}
}


