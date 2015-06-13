import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PodajNazweUzytkownikaFrame extends JFrame {
	
	private String uzytkownik = "";
	public PodajNazweUzytkownikaFrame() {
		
		while (uzytkownik.isEmpty()) {
			uzytkownik = JOptionPane.showInputDialog("Please enter your name");
			setLocationRelativeTo(null);
		}	
	}
	
	public String getUzytkownik() {
		return uzytkownik;
	}
	public void setUzytkownik(String uzytkownik) {
		this.uzytkownik = uzytkownik;
	}
	
}