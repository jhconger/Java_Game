import java.util.ArrayList;

public class PC {
	String name;
	int hp;
	int accuracy;
	int inRoom = 0;
	ArrayList<Item> item = new ArrayList<Item>();
	ArrayList<Item> inventoryItems = new ArrayList<Item>();

	public void look() {
		System.out.println("accuracy: " + accuracy);
		System.out.println("hp: " + hp);
	}

	public void remove(String[] x) {
		for (int i = 0; i < inventoryItems.size(); i++) {
			if (inventoryItems.get(i).id.equals(x[1])) {
				System.out.println("You remove a " + inventoryItems.get(i).id);
				item.add(inventoryItems.get(i));
				inventoryItems.remove(i);
			}
		}
	}
	public  void eq() {
		for (int i = 0; i < inventoryItems.size(); i++) {
			System.out.println(inventoryItems.get(i).name + ":" + inventoryItems.get(i).wearloc);
		}
	}
	public void wear(String[] x) {
		if (inventoryItems.size() == 0) {
			for (int i = 0; i < item.size(); i++) {
				if (x[1].equalsIgnoreCase(item.get(i).id) && item.get(i).isWearable) {
					inventoryItems.add(item.get(i));
					System.out.println("You wear a " + item.get(i).name);
					item.remove(i);
					break;
				}
			}
		} else {
			boolean isWearing = false;
			for (int i = 0; i < item.size(); i++) {
				for (int z = 0; z < item.size(); z++) {
					if (x[1].equalsIgnoreCase(item.get(z).id) && item.get(z).isWearable) {
						if (item.get(z).wearloc.equals(inventoryItems.get(i).wearloc)) {
							System.out.println("You already have something worn in that location.");
							isWearing = true;
						}
					}
				}
				if (!isWearing) {
					inventoryItems.add(item.get(i));
					System.out.println("You wear a " + item.get(i).name);
					item.remove(i);
					break;
				}
			}
		}
	}
}



