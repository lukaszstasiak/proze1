import java.util.Scanner;

/**
 * Przechowuje rozklad elementow planszy i gracza dla danego poziomu. Dany
 * poziom ma swoj indywidualny numer.
 * 
 * Polozenia objektow sa wyrazone wielkosciami (0-16;0-8), gdzie puntk (0,0) to
 * lewy gorny rog, a (16,8) to prawy dolny.
 * 
 * @author Maurycy
 *
 */

public class ListaPlansz {

	private final int numerPoziomu;
	private final int liczbaRuchow;
	private final long czasNaPrzejscie;
	private final int wymaganaLiczbaPnktow;
	private final int punktyZaWybuch;

	/**
	 * Tworzy model planszy z paramtrami.
	 * 
	 * @param numpoz
	 *            - numer poziomu
	 * @param polplat
	 *            - lista wspolzednych platform
	 * @param polgrac
	 *            - plozenie poczatkowe gracza
	 * @param typbon
	 *            - typ bonusa
	 * @param polbon
	 *            - polozenie bonusu
	 * @param cnp
	 *            - czas na przejscie planszy
	 * @param punplat
	 *            - punkty za znikniecie platformy
	 * @param punprem
	 *            - premia punktowa
	 */
	public ListaPlansz(int numpoz, int liczRuch, long cnp, int wlp, int pzw) {
		numerPoziomu = numpoz;
		liczbaRuchow = liczRuch;
		czasNaPrzejscie = cnp;
		wymaganaLiczbaPnktow = wlp;
		punktyZaWybuch = pzw;
	}

	/**
	 * Zwraca numer poziomu.
	 * 
	 * @return numer poziomu.
	 */
	public int getNumerPoziomu() {
		return numerPoziomu;
	}

	
	
	
	/**
	 * Zwraca po³o¿enie platform.
	 * 
	 * @return po³o¿enie platform.
	 */
	public int getLiczbaRuchow() {
		return liczbaRuchow;
	}

	/**
	 * Zwraca czas na przejœcie.
	 * 
	 * @return czas na przejœcie.
	 */
	public long getCzasNaPrzejscie() {
		return czasNaPrzejscie;
	}

	
	/**
	 * Zwraca wymagana liczbe punktow.
	 * 
	 * @return wymaganaLiczbaPnktow.
	 */
	public int getWymaganaLiczbaPnktow() {
		return wymaganaLiczbaPnktow;
	}
	
	/**
	 * Wczytuje dane planszy z pliku. Ustawia proporcje i rozdzielczoœæ planszy.
	 * 
	 * @author Maurycy
	 *
	 */
	
	
			public int getPunktyZaWybuch() {
			return punktyZaWybuch;
		}
	
	public static class WczytajListePlansz {

		private Scanner skaner;
		private final int punktyZaWybuch;
		public final static String boardLimiter = "=====";


		/**
		 * Zapamietuje skaner z ktorego bedzie wczytywac level.
		 * 
		 * @param scanner
		 *            przechowuje wczytane z pliku konfiguracujnego dane
		 * @param punktyZaWybuch
		 *            - punkty za wybuchy.
		 */
		public WczytajListePlansz(Scanner scanner, int punktyZaWybuch) {
			skaner = scanner;
			this.punktyZaWybuch = punktyZaWybuch;
		}

		/**
		 * Wczytuje 1plansze o okreslonym formacie ze skanera. Zajmuje sie
		 * przeskalowaniem z 16x8 do 1024x512 pol.
		 * 
		 * @return plansza stworzona na podstawie odczytanych danych.
		 */
		public ListaPlansz getNextBoard() {
			int np = skaner.nextInt();
			int lr = skaner.nextInt();
			long cnp = skaner.nextLong() * 1000;
			int wlp = skaner.nextInt();;

			return new ListaPlansz(np, lr, cnp, wlp, this.punktyZaWybuch);
		}


	}
}