package app;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class KoniecGryFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	PanelGame panelGry = new PanelGame();
	public KoniecGryFrame() {

		
		while (text.isEmpty()) {
			text = JOptionPane.showInputDialog("KONIEC GRY\n TWOJ WYNIK TO:\t" + panelGry.getMap().getPunkty());
			setLocationRelativeTo(null);
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
