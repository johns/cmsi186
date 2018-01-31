public class Tiger {
	int legs;
	String name;
	int bladderLevel;

	public Tiger () {
		this.bladderLevel = 0;
	}

	public int getBladderLevel () {
		return this.bladderLevel;
	}

	public void setBladderLevel (int level) {
		this.bladderLevel = level;
	}

	public void roar () {
		System.out.println("roar");
	}

	public void pee () {
		this.bladderLevel = 0;
		System.out.println("ahhh, much better");
	}

	public String hunts (String prey) {
		System.out.println("hey" + prey + "die! Now!");
		return "Ok I'm full.";
	}

	public static void main (String [] args) {
		Tiger tony = new Tiger();
		tony.roar();
		tony.pee();
		String response = tony.hunts("antelope");
		System.out.println(response);
		System.out.println(tony.getBladderLevel());
		Tiger t2 = new Tiger();
		t2.setBladderLevel(500);
		System.out.println(t2.getBladderLevel());
	}
}
