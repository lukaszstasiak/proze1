package app;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import app.ListaPlansz.WczytajListePlansz;


/**
 * Konfiguracja gry. Konstruktor wczytuje z pliku.
 * 
 * 
 */
public class Config {

	private  int liczbaZyc;
	private  int punktyZaWybuch;
	private int liczbaRuchow;
	private  List<ListaPlansz> listaPoziomow;

	/**
	 * Wczytuje dane z pliku konfiguracyjnego i zleca ztworzenie modeli wszytkich plansz.
	 * @param configFileName - pliku konfiguracyjny.
	 * @throws FileNotFoundException  - jeœli nie ma pliku.
	 */
	public Config(String configFileName)
			 {
		Scanner skaner;
		try {
			skaner = new Scanner(new File(configFileName));
			if (!skaner.hasNext()) {
				skaner.close();
				throw new FileNotFoundException("nie znaleziono pliku");
			}
			liczbaZyc = skaner.nextInt();
			punktyZaWybuch = skaner.nextInt();
			setLiczbaRuchow(skaner.nextInt());
			listaPoziomow = new Vector<>();
			skaner.next(WczytajListePlansz.boardLimiter);
//			WczytajListePlansz listaPlansz = new WczytajListePlansz(skaner, punktyZaWybuch);
//			while (skaner.hasNext()) {
//				listaPoziomow.add(listaPlansz.getNextBoard());
//			}
			skaner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	public int getPunktyZaWybuch() {
		return punktyZaWybuch;
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



	public int getLiczbaRuchow() {
		return liczbaRuchow;
	}



	public void setLiczbaRuchow(int liczbaRuchow) {
		this.liczbaRuchow = liczbaRuchow;
	}
}