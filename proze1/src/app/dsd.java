//package app;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.Insets;
//
//import javax.swing.JPanel;
//
//
//public class dsd extends JPanel {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * Create the panel.
//	 */
//	public dsd() {
//		GridBagLayout gridBagLayout = new GridBagLayout();
//		gridBagLayout.columnWidths = new int[]{335, 110, 0};
//		gridBagLayout.rowHeights = new int[]{299, 0};
//		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
//		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
//		setLayout(gridBagLayout);
//		
//		PanelGame panelGame = new PanelGame();
//		GridBagConstraints gbc_panelGame = new GridBagConstraints();
//		gbc_panelGame.insets = new Insets(0, 0, 0, 5);
//		gbc_panelGame.gridx = 0;
//		gbc_panelGame.gridy = 0;
//		add(panelGame, gbc_panelGame);
//		
//		RightGamePanel rightGamePanel = new RightGamePanel();
//		GridBagConstraints gbc_rightGamePanel = new GridBagConstraints();
//		gbc_rightGamePanel.gridx = 1;
//		gbc_rightGamePanel.gridy = 0;
//		add(rightGamePanel, gbc_rightGamePanel);
//
//	}
//
//}
