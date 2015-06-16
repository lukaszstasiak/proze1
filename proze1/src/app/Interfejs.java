package app;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Interfejs extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nazwaUzytkownika;

	private JPanel contentPane;
	public static BufferedImage image;
	private PanelGame panelGry;
	private RightGamePanel panelWyniku;
	private Score obecnyWynik;

	public static void main(String[] args) {

		// /////////////////////// Tworzy sie watek z JFrame
		// ////////////////////////////////

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Interfejs frame = new Interfejs();
				frame.setTitle("Candy Crush by Pasieka&Stasiak");
				frame.setLocationRelativeTo(null); // okno w centrum ekranu
				frame.setVisible(true);
			}
		});

	}

	/**
	 * Create the frame.
	 */
	// /////////////////////// Konstruktor panelu z menu
	// ////////////////////////////////

	public Interfejs() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 480); // wielkosc okna
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 109, 315, 93, 0 };
		gbl_contentPane.rowHeights = new int[] { 120, 0, 120, 0, 119, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		// /////////////////////// Button "Graj"
		// ////////////////////////////////

		JButton btnGraj = new JButton("GRAJ");
		GridBagConstraints gbc_btnGraj = new GridBagConstraints();
		gbc_btnGraj.fill = GridBagConstraints.BOTH;
		gbc_btnGraj.insets = new Insets(0, 0, 5, 5);
		gbc_btnGraj.gridx = 1;
		gbc_btnGraj.gridy = 0;
		contentPane.add(btnGraj, gbc_btnGraj);

		// /////////////////////// Wyswietla sie plansza z gra
		// ////////////////////////////////

		btnGraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {

				getContentPane().removeAll(); // usuniecie pierwotnego okna
				getContentPane().setLayout(new BorderLayout());
				setBounds(100, 100, 600, 500);
				setResizable(true);

				// podzielenie okna na dwa panele
				JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				jsp.setDividerSize(0);

				panelGry = new PanelGame();
				panelWyniku = new RightGamePanel();

				panelWyniku.setNazwa(nazwaUzytkownika);
				panelWyniku.setWynik(panelGry.getMap().getPunkty());
				panelWyniku.setLicznikRuchow(panelGry.getMap()
						.getLicznikRuchow());
				jsp.add(panelGry);
				jsp.add(panelWyniku);
				jsp.setResizeWeight(0.8);
				getContentPane().add(jsp);
				getContentPane().addNotify();
				setVisible(true);
				setLocationRelativeTo(null);

			}

		});

		// /////////////////////// Wywoluje sie okno dialogowe
		// "Please enter your name" ////////////////////////////////

		btnGraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				PodajNazweUzytkownikaFrame okno = new PodajNazweUzytkownikaFrame();
				nazwaUzytkownika = okno.getUzytkownik();
			}
		});

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		contentPane.add(separator, gbc_separator);

		// /////////////////////// Button "Ustawienia"
		// ////////////////////////////////

		JButton btnUstweiania = new JButton("USTAWIENIA");
		GridBagConstraints gbc_btnUstweiania = new GridBagConstraints();
		gbc_btnUstweiania.fill = GridBagConstraints.BOTH;
		gbc_btnUstweiania.insets = new Insets(0, 0, 5, 5);
		gbc_btnUstweiania.gridx = 1;
		gbc_btnUstweiania.gridy = 2;
		contentPane.add(btnUstweiania, gbc_btnUstweiania);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 3;
		contentPane.add(separator_1, gbc_separator_1);

		// /////////////////////// Button "Tablica wynikow"
		// ////////////////////////////////

		JButton btnTablicaWynikw = new JButton("TABLICA WYNIK\u00D3W");
		GridBagConstraints gbc_btnTablicaWynikw = new GridBagConstraints();
		gbc_btnTablicaWynikw.fill = GridBagConstraints.BOTH;
		gbc_btnTablicaWynikw.insets = new Insets(0, 0, 0, 5);
		gbc_btnTablicaWynikw.gridx = 1;
		gbc_btnTablicaWynikw.gridy = 4;
		contentPane.add(btnTablicaWynikw, gbc_btnTablicaWynikw);

		// /////////////////////// Wyswietlenie tablicy wynikow
		// ////////////////////////////////

		btnTablicaWynikw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				TablicaWynikowFrame frameTab = new TablicaWynikowFrame();
				frameTab.setLocationRelativeTo(null);
				frameTab.setVisible(true);
			}
		});

	}

	public Score getObecnyWynik() {
		return obecnyWynik;
	}

	public void undoButtonPressed() {
		panelGry.undoMovement();
	}

	public void updatePoints() {
		panelWyniku.setWynik(panelGry.getMap().getPunkty());
	}

	public void updateMoveCount() {
		panelWyniku.setLicznikRuchow(panelGry.getMap().getLicznikRuchow());
		if (panelGry.getMap().getLicznikRuchow() == 0) {
			new JFrame();
			JOptionPane.showMessageDialog(null, "TWOJ WYNIK TO:\t"
					+ panelGry.getMap().getPunkty(), "KONIEC GRY",
					JOptionPane.OK_CANCEL_OPTION);

			setLocationRelativeTo(null);
		}
	}
}
