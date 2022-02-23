
public class Simple_RPG {
		static Game_Logic gl= new Game_Logic();
	public static void main(String[] args) {

		Game_Objects.initializeNPCArray();
		Game_Objects.initializeItemArray();
		while(true)
		{
		game_loop();
		}

	}
	public static void game_loop()
	{
		gl.waitForCommand();
	}
}
