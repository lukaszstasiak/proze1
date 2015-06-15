package app;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class RightGamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int wynik;
	private String nazwa;
	JTextArea textArea;
	
	public int getWynik() {
		return wynik;
	}

	public void setWynik(int wynik) {
		this.wynik = wynik;
		textArea.setText("Wynik:\n" + nazwa + ":\t" + wynik);
		
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	/**
	 * Create the panel.
	 */
	public RightGamePanel() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{80};
		gbl_panel.rowHeights = new int[]{20, 20, 20, 0};
		gbl_panel.columnWeights = new double[]{0.0};
		gbl_panel.rowWeights = new double[]{0.1, 0.1, 0.1, 0.7};
		panel.setLayout(gbl_panel);
		
		
		//przcisk COFNIJ dzia³ajacy tak, ze kasuje dany Interfejs i tworzy go od nowa 
		JButton btnNewButton = new JButton("Wróæ");
		btnNewButton.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        ((Interfejs)getRootPane().getParent()).dispose();
				Interfejs frame = new Interfejs();
				frame.setTitle("Candy Crush by Pasieka&Stasiak");
				frame.setLocationRelativeTo(null); // okno w centrum ekranu
				frame.setVisible(true);
		    }
		});
		
		// Cofanie!
		JButton undoButton = new JButton("Cofnij");
		undoButton.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        ((Interfejs)getRootPane().getParent()).undoButtonPressed();
		    }
		});
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		GridBagConstraints gbc_undoButton = new GridBagConstraints();
		gbc_undoButton.insets = new Insets(0, 0, 5, 0);
		gbc_undoButton.gridx = 0;
		gbc_undoButton.gridy = 1;
		panel.add(undoButton, gbc_undoButton);
		
		textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 3;
		panel.add(textArea, gbc_textArea);
		textArea.append("Wynik:\n" + nazwa + ":\t" + wynik);

	}
	
}
