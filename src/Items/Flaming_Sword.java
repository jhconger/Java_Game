package Items;

public class Flaming_Sword extends Item {
    int accuracy = 10;
    int damage = 20;

    public Flaming_Sword() {
        id = "Flaming_Sword";
        name = "Flaming Sword";
        isWearable = true;
        wearloc = "wield";
    }
}