package app;
import info.BallType;
import info.ViewInfo;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import logic.Map;

public class PanelGame extends JPanel implements MouseListener, MouseMotionListener, RefreshInterface
{

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
	
	private boolean animationInProgress;
	private AnimThread animThread;
	private float animProgress;
	
	private boolean dragInProgress;
	private int dragPosX, dragPosY;
	
	public PanelGame() {
		dragInProgress = false;
		animationInProgress = false;
		animThread = new AnimThread(25,this);
		animProgress = 0;
		
		addMouseListener(this);
		addMouseMotionListener(this);
		map = new Map(10, 10);
		viewInfo = map.getViewInfo();
		try {
			setSize(600, 600);
			background = ImageIO.read(new File("background.png"));

		} catch (IOException e) {
			System.out.println("Image not found");
		}
		
	}

	public Map getMap() {
		return map;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int newBallHeight = getHeight() / 10;
		int newBallWidth = getWidth() / 10;
		
		// Update points
		((Interfejs)getRootPane().getParent()).updatePoints();
		// Update licznikRuchow
		((Interfejs)getRootPane().getParent()).updateMoveCount();
		
		if(animationInProgress)
		{
			animate(g);
			return;
		}

		drawTable(g, viewInfo.getNewTable());
		if(dragInProgress)
		{
			Ball ball = new Ball(viewInfo.getOldTable()[posX1][posY1]);
			g.drawImage(ball.getImg(), dragPosX - newBallWidth/2, dragPosY - newBallHeight/2,
					dragPosX + newBallWidth/2, dragPosY + newBallHeight/2, 0, 0,
					40, 40, null);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		posX1 = (int) (Math.floor(x / (getWidth() / 10)));
		posY1 = (int) (Math.floor(y / (getHeight() / 10)));
		dragInProgress = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		posX2 = (int) (Math.floor(x / (getWidth() / 10)));
		posY2 = (int) (Math.floor(y / (getHeight() / 10)));

		if(animationInProgress)
			return;
		animationInProgress = map.move(posX1, posY1, posX2, posY2);
		viewInfo = map.getViewInfo();
		repaint();
		animProgress = 0;
		dragInProgress = false;
	}

	// Nothing to do here
	@Override
	public void mouseClicked(MouseEvent arg0){}
	@Override
	public void mouseEntered(MouseEvent arg0){}
	@Override
	public void mouseExited(MouseEvent arg0){}
	
	// Cofanie! (Tylko 1 ruch!)
	public void undoMovement()
	{
		map.undoMovement();
		viewInfo.getNewTable();
		repaint();
	}
	
	private void animate(Graphics g)
	{
		int newBallHeight = getHeight() / 10;
		int newBallWidth = getWidth() / 10;
		
		if(!animationInProgress)
			stopAnimThread();
		else
			startAnimThread();
		
		//draw part of old table - past destroyed balls
		drawPartOfTable(g, viewInfo.getAfterExplosionTable());
		
		//for each column
		for(int col=0;col<10;col++)
		{
			//get last destroyed ball
			int destroyed = getDestroyed(col);
			//get last normal ball
			int normal = getNormal(col);
			//get distance between them
			int distance = calcDistance(destroyed, normal)*newBallHeight;
			//calculate passed distance by animProgress
			int passedDistance = (int) (distance*animProgress);
			//draw
			if(normal != 10)
			for(int i=0;i<destroyed+1;i++)
			{
				Ball ball = new Ball(viewInfo.getNewTable()[col][i]);
				g.drawImage(ball.getImg(), newBallWidth * col,
						(newBallHeight * i - distance + passedDistance),
						newBallWidth * (col + 1), (newBallHeight * (i+1) - distance + passedDistance), 0, 0,
						40, 40, null);
			}

		}
		
		animProgress += 0.03;
		if(animProgress >= 1.0f)
		{
			animationInProgress = false;
			System.out.println("End of animation!");
			stopAnimThread();
		}
	}
	
	private int calcDistance(int destroyed, int normal)
	{
		return destroyed-normal+1;
	}

	private int getNormal(int col)
	{
		int result;
		
		for(result = 0;result < 10;result++)
		{
			BallType[][] tab = viewInfo.getAfterExplosionTable();
			if(tab[col][result] == BallType.DESTROYED)
				return result;
		}
		
		return result;
	}

	private int getDestroyed(int col)
	{
		int result;
		
		for(result = 9;result > 0;result--)
		{
			BallType[][] tab = viewInfo.getAfterExplosionTable();
			if(tab[col][result] == BallType.DESTROYED)
				return result;
		}
		
		return result;
	}

	private void startAnimThread()
	{
		animThread.start();
	}
	
	private void stopAnimThread()
	{
		animThread.stop();
		// Fix last frame of animation
		repaint();
	}

	@Override
	public void doRefresh()
	{
		repaint();
		
	}
	
	private void drawPartOfTable(Graphics g, BallType[][] table)
	{
		int newBallHeight = getHeight() / 10;
		int newBallWidth = getWidth() / 10;
		
		//find destroyed balls
		int destroyedPos[] = new int[10];
		for(int col=0;col<10;col++)
		{
			destroyedPos[col] = getDestroyed(col);
		}

		for (int j = 0; j < getHeight() / newBallHeight; j++)
			for (int i = 0; i < getWidth() / newBallWidth; i++) 
			{
				if(j < destroyedPos[i]) continue;
				if(posX1 == i && posY1 == j && dragInProgress) continue;
				Ball ball = new Ball(table[i][j]);
				g.drawImage(ball.getImg(), newBallWidth * i, newBallHeight * j,
						newBallWidth * (i + 1), newBallHeight * (j + 1), 0, 0,
						40, 40, null);
			}
	}

	
	private void drawTable(Graphics g, BallType[][] table)
	{
		int newBallHeight = getHeight() / 10;
		int newBallWidth = getWidth() / 10;
		

		for (int j = 0; j < getHeight() / newBallHeight; j++)
			for (int i = 0; i < getWidth() / newBallWidth; i++) 
			{
				if(posX1 == i && posY1 == j && dragInProgress) continue;
				Ball ball = new Ball(table[i][j]);
				g.drawImage(ball.getImg(), newBallWidth * i, newBallHeight * j,
						newBallWidth * (i + 1), newBallHeight * (j + 1), 0, 0,
						40, 40, null);
			}
	}

	@Override
	public void mouseDragged(MouseEvent arg0)
	{
		dragPosX = arg0.getX();
		dragPosY = arg0.getY();
		repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0)
	{
	}
}
