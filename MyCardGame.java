import java.util.Arrays;

class MyCardGame {
	public static void main(String []args) {
		Card c = new Card (5,1);
		System.out.println("Rank: " + c.getRank());
		System.out.println("Suit: " + c.getSuit());
		Deck d = new Deck();
		System.out.println(d);
	}
}

class Deck {
	private Card [] cards;

	public Deck() {
		cards = new Card[52];
		int index = 0;
		for(int i = 1; i <= 13; i++){
			for(int j = 1; j <= 4; j++){
				cards[index] = new Card(i,j);
				index++;
			}
		}
	}

	public String toString(){
		return Arrays.toString(this.cards);
	}
}

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