package aplikacja;
public class Score {
	
	private final int points;
	private final String playerNick;
	
	/**
	 * Tworzy Wynik z parametrami.
	 * @param points  - liczba punkt�w.
	 * @param playerNick - nick gracza.
	 */
	public Score (int points, String playerNick) {
		this.points = points;
		this.playerNick = playerNick;		
	}

	/**
	 * Zwraca liczbe punkt�w.
	 * @return liczba punkt�w.
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Zwraca nick gracza.
	 * @return nick gracza.
	 */
	public String getNick() {
		return playerNick;
	}

}