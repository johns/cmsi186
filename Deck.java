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