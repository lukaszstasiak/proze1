import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ZapisPliku {

	  public static void main(String[] args){
			List<Wyniki> wyniki = new ArrayList<>();
		

		wyniki.add(new Wyniki("£ukasz", 100));
		wyniki.add(new Wyniki("Kasia", 510));
		wyniki.add(new Wyniki("Janek", 52));
		wyniki.add(new Wyniki("Zosia", 1090));
		wyniki.add(new Wyniki("Jamnik", 222));
//		wyniki.add(new Wyniki()) -- dodany ten co go zapisujemy

		Collections.sort(wyniki, new Comparator<Wyniki>() {

			public int compare(Wyniki w1, Wyniki w2) {
				if (w1.getWynik() > w2.getWynik()) {
					return -1;
				} else if (w1.getWynik() < w2.getWynik()) {
					return 1;
				}
				return 0;
			}
		
			
			
		});
		System.out.println(wyniki);
		try (ObjectOutputStream os = new ObjectOutputStream(
				new FileOutputStream("wyniki.txt"))) {

			for (Wyniki wynik : wyniki) {
				os.writeObject(wynik);
			}
			
			os.close();
		} catch (FileNotFoundException e) {
			System.out.println("Nie znaleziono pliku");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
}
