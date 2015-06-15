package aplikacja;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class TablicaWynikowFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2004012863096586301L;

	public TablicaWynikowFrame() {
		
		super.setSize(200, 200);
		super.setResizable(false);
		
		
		getContentPane().setLayout(new BorderLayout());
		DefaultListModel dodawanieElementow = new DefaultListModel();
		JList listaWynikow = new JList(dodawanieElementow);
		listaWynikow.setVisibleRowCount(10);
		listaWynikow.setSelectionBackground(Color.YELLOW);
		add(new JScrollPane(listaWynikow));
		JButton buttonTab = new JButton("COFNIJ");
		Container cont = getContentPane();
		cont.add(listaWynikow, BorderLayout.CENTER);
		cont.add(buttonTab, BorderLayout.SOUTH);
		
		
		
		buttonTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});
		try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(
				"wyniki.txt"))) {
			int i = 1;
			
			while (true) {
				try {
					dodawanieElementow.addElement(i + ". " + os.readObject().toString() + "\n");
					i++;
				} catch (EOFException e) {
					break;
				} catch (ClassNotFoundException e1) {
				}
			}
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// } catch (ClassNotFoundException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
}
