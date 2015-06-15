import info.BallType;
import info.ViewInfo;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import logic.Map;

public class PanelGame extends JPanel implements MouseListener,
		MouseMotionListener {

	private static final long serialVersionUID = 1L;
	public static BufferedImage background;
	
	private Map map;
	private ViewInfo viewInfo;
	
	private int posX1;
	private int posY1;
	private int posX2;
	private int posY2;
	Ball ball1;
	Ball ball2;
	
	public PanelGame() {
		addMouseListener(this);
		map  = new Map(10,10);
		viewInfo = map.getViewInfo();
		try {
			setSize(600, 600);
			background = ImageIO.read(new File("background.png"));
			
		} catch (IOException e) {
			System.out.println("Image not found");
		}

	}



	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int newBallHeight = getHeight() / 10;
		int newBallWidth = getWidth() / 10;
		
		
		// STILL TODO!
		// WARNING: CODE IS IN ALPHA PHASE LOL!
		
		BallType kulkiPlaceholder[][] = viewInfo.getAfterExplosionTable();

		for (int j = 0; j < getHeight() / newBallHeight; j++)
			for (int i = 0; i < getWidth() / newBallWidth; i++) 
			{
//				Random rand = new Random();
//				 ViewInfo tablicaZKulkami = new ViewInfo();
//				
////						.getByInt(rand.nextInt(4) + 2);
//				
//				
//				tablicaKulek[i][j] = new Ball(tablicaZKulkami.getNewTable()[i][j].getByInt(rand.nextInt(4) + 2));
//
				Ball ball = new Ball(kulkiPlaceholder[i][j]);
				g.drawImage(ball.getImg(), newBallWidth * i,
						newBallHeight * j, newBallWidth * (i + 1),
						newBallHeight * (j + 1), 0, 0, 40, 40, null);
			}
	}

//	public Ball getTablicaKulek(int x, int y) {
//		return tablicaKulek[x][y];
//	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		posX1 = (int)(Math.floor(x / (getWidth() / 10)));
		posY1 = (int)(Math.floor(y / (getHeight() / 10)));
		
		//ball1 = getTablicaKulek(posX1, posY1);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		posX2 = (int)(Math.floor(x / (getWidth() / 10)));
		posY2 = (int)(Math.floor(y / (getHeight() / 10)));
		
		//ball2 = getTablicaKulek(posX2, posY2);
		map.move(posX1, posY1, posX2, posY2);
		viewInfo = map.getViewInfo();
		repaint();
	}

//	public boolean czyMoznaZamienicMiejscami(Ball kula1, Ball kula2) {
//		
//		if ( ball1 = getTablicaKulek(posX2 +1+, posY2)){
//			
//		}
//	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
