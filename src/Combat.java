public class Combat {
    public void attack(String[] x) {
        for (int i = 0; i < Game_Objects.room.size(); i++) {
            if (Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) {
                for (int y = 0; y < Game_Objects.room.get(i).npc.size(); y++) {
                    if (Game_Objects.room.get(i).npc.get(y).id.equalsIgnoreCase(x[1])) {
                        int npc_hit = Game_Objects.rng.returnRandom(100);
                        npc_hit = npc_hit + (Game_Objects.room.get(i).npc.get(y).accuracy / 2);
                        if (npc_hit > 50) {
                            int npc_damage = Game_Objects.rng.returnRandom(10);
                            Game_Objects.pc.hp = Game_Objects.pc.hp - npc_damage;
                            System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " hit you for " + npc_damage);
                        } else {
                            System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " missed.");
                        }
                        int pc_hit = Game_Objects.rng.returnRandom(100);
                        pc_hit = npc_hit + (Game_Objects.room.get(i).npc.get(y).accuracy / 2);
                        if (pc_hit > 50) {
                            int pc_damage = Game_Objects.rng.returnRandom(10);
                            Game_Objects.room.get(i).npc.get(y).hp = Game_Objects.room.get(i).npc.get(y).hp - pc_damage;
                            System.out.println("You hit for " + pc_damage);
                            if (Game_Objects.room.get(i).npc.get(y).hp <= 0) ;
                            {
                                npc_death(i, y);
                            }
                        } else {
                            System.out.println("You missed.");
                        }
                    }
                }
            }
        }
    }

    public void npc_death(int i, int y) {
        System.out.println("A " + Game_Objects.room.get(i).npc.get(y).name + "had died.");
        Game_Objects.room.get(i).npc.remove(y);
    }
}
