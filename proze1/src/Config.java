import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import ListaPlansz.WczytajListePlansz;


/**
 * Konfiguracja gry. Konstruktor wczytuje z pliku.
 * 
 * @author Maurycy
 * 
 */
public class Config {

	private final int liczbaZyc;
	private final int punktyZaWybuch;
	private final List<ListaPlansz> listaPoziomow;

	/**
	 * Wczytuje dane z pliku konfiguracyjnego i zleca ztworzenie modeli wszytkich plansz.
	 * @param configFileName - pliku konfiguracyjny.
	 * @throws FileNotFoundException  - jeœli nie ma pliku.
	 */
	public Config(String configFileName)
			throws FileNotFoundException {
		Scanner skaner = new Scanner(new File(configFileName));
		if (!skaner.hasNext()) {
			skaner.close();
			throw new FileNotFoundException("nie znaleziono pliku");
		}
		liczbaZyc = skaner.nextInt();
		punktyZaWybuch = skaner.nextInt();
		listaPoziomow = new Vector<>();
		skaner.next(WczytajListePlansz.boardLimiter);
		WczytajListePlansz listaPlansz = new WczytajListePlansz(skaner, punktyZaWybuch);
		while (skaner.hasNext()) {
			listaPoziomow.add(listaPlansz.getNextBoard());
		}
		skaner.close();
	}

	
	
	/**
	 * Zwraca liczbê ¿yæ.
	 * @return - liczba ¿yæ.
	 */
	public int getLiczbaZyc() {
		return liczbaZyc;
	}

	/**
	 *  Zwraca listê modeli plansz.
	 * @return - lista modeli plansz.
	 */
	public List<ListaPlansz> getLevelsList() {
		return listaPoziomow;
	}
}