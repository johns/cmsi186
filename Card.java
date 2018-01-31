class Card {
	private int rank;
	private int suit;

	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getRank(){
		return this.rank;
	}

	public int getSuit(){
		return this.suit;
	}

	public String toString(){
		return "Rank: " + this.rank + "Suit: " + this.suit;
	}
}