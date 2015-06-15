package logic;

import info.BallType;
import info.ViewInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import app.Config;

public class Map {
	private ArrayList<Line> verticalLines, horizontalLines;
	private ViewInfo viewInfo;
	private int sizeX, sizeY;
	private int punkty = 0;
	private Config config = new Config("config.txt");
	
	public Map(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		verticalLines = new ArrayList<Line>();
		horizontalLines = new ArrayList<Line>();

		for (int i = 0; i < sizeX; i++)
			verticalLines.add(new Line());
		for (int i = 0; i < sizeY; i++)
			horizontalLines.add(new Line());

		viewInfo = new ViewInfo();

		spawnNewBalls();

		viewInfo.setOldTable(getInfoTable());
		viewInfo.setAfterExplosionTable(getInfoTable());
		viewInfo.setNewTable(getInfoTable());
		System.out.println(punkty + "\n\n");
	}

	private void updateHorizontalLines() {
		for (Line line : horizontalLines) {
			line.clear();
		}

		for (Line line : verticalLines) {
			ArrayList<GameBall> balls = line.getBalls();
			for (GameBall ball : balls) {
				horizontalLines.get(line.getIndex(ball)).addBall(ball);
			}
		}

	}

	private void spawnNewBalls() {
		System.out.println("spawning new balls!");
		for (Line line : verticalLines) {
			line.removeDestroyed();
			while (line.getSize() < sizeY) {
				line.spawnRandomBall();
			}
		}
		updateHorizontalLines();
	}

	private Line getHorizontalLine(int pos) {
		return horizontalLines.get(pos);
	}

	private Line getVerticalLine(int pos) {
		return verticalLines.get(pos);
	}

	private boolean replaceBalls(int firstBallX, int firstBallY,
			int secondBallX, int secondBallY) {
		GameBall firstBall = getHorizontalLine(firstBallY).getBall(firstBallX);
		GameBall secondBall = getHorizontalLine(secondBallY).getBall(
				secondBallX);
		if (firstBall.getBallType().getInt() == secondBall.getBallType()
				.getInt())
			return false;
		System.out.println("First type: " + firstBall.getBallType());
		System.out.println(firstBallX + " " + firstBallY);
		System.out.println("Second type: " + secondBall.getBallType());
		System.out.println(secondBallX + " " + secondBallY);
		// GameBall hold = firstBall;
		// hold = firstBall;
		// firstBall = secondBall;
		// secondBall = hold;

		BallType hold = firstBall.getBallType();
		firstBall.setBallType(secondBall.getBallType());
		secondBall.setBallType(hold);

		return true;
	}

	private ArrayList<GameBall> getDestroyedBalls(int x1, int y1, int x2, int y2) {
		ArrayList<GameBall> destroyedBalls = new ArrayList<GameBall>();
		Set<GameBall> set = new HashSet<GameBall>();
		Set<GameBall> temp = new HashSet<GameBall>();
		temp.addAll(getVerticalLine(x1).getBallsToDestroy(y1));
		System.out.println("Pion: " + x1);
		for (GameBall b : temp) {
			System.out.println(b.getBallType());
			
		}
		set.addAll(temp);
		temp.clear();

		temp.addAll(getVerticalLine(x2).getBallsToDestroy(y2));
		System.out.println("Pion: " + x2);
		for (GameBall b : temp) {
			System.out.println(b.getBallType());
			
		}
		set.addAll(temp);
		temp.clear();

		temp.addAll(getHorizontalLine(y1).getBallsToDestroy(x1));
		System.out.println("Poziom: " + y1);
		for (GameBall b : temp) {
			System.out.println(b.getBallType());
		}
		set.addAll(temp);
		temp.clear();
		temp.addAll(getHorizontalLine(y2).getBallsToDestroy(x2));
		System.out.println("Poziom: " + y2);
		for (GameBall b : temp) {
			System.out.println(b.getBallType());
		}

		set.addAll(temp);
		temp.clear();

		destroyedBalls.addAll(set);
		System.out.println(punkty + "\n\n");
		return destroyedBalls;
	}

	public int getPunkty() {
		return punkty;
	}

	private void markDestroyedBalls(ArrayList<GameBall> destroyedBalls) {
		for (Line line : horizontalLines) {
			ArrayList<GameBall> balls = line.getBalls();
			int zdobytePunkty = 0;
			int count = 1;
			for (GameBall ball : balls) {
				
				if (destroyedBalls.contains(ball)) {
					ball.setBallType(BallType.DESTROYED);

					zdobytePunkty = zdobytePunkty + (config.getPunktyZaWybuch()*(1+ (count/6)));
				}
				count++;
			}
			punkty = punkty + (int) Math.round(zdobytePunkty);
		}

		/*
		 * for(GameBall ball: destroyedBalls) {
		 * ball.setBallType(BallType.DESTROYED); }
		 */
	}

	private BallType[][] getInfoTable() {
		BallType[][] infoTable = new BallType[sizeX][sizeY];

		int y = 0;
		for (Line line : horizontalLines) {
			ArrayList<GameBall> balls = line.getBalls();
			int x = 0;
			for (GameBall ball : balls) {
				infoTable[x][y] = ball.getBallType();
				x++;
			}
			y++;
		}

		return infoTable;
	}
	
	private void loadFromInfoTable(BallType[][] infoTable)
	{
		// Clean up
		for (Line line : horizontalLines) {
			line.clear();
		}
		
		for (Line line : verticalLines) {
			line.clear();
		}
		
		// Load to vertical
		int idx = 0;
		for(Line line: verticalLines)
		{
			for(int i = 0; i<sizeY;i++)
			{
				line.addBall(new GameBall(infoTable[idx][i]));
			}
			idx++;
		}
		
		// Update horizontal references
		updateHorizontalLines();
		// Reset ViewInfo data
		viewInfo.setOldTable(getInfoTable());
		viewInfo.setAfterExplosionTable(getInfoTable());
		viewInfo.setNewTable(getInfoTable());
	}
	
	public void undoMovement()
	{
		loadFromInfoTable(viewInfo.getOldTable());
	}

	private void update(ArrayList<GameBall> destroyedBalls) {
		BallType[][] oldTable = viewInfo.getOldTable();
		viewInfo = new ViewInfo();
		viewInfo.setOldTable(oldTable);
		markDestroyedBalls(destroyedBalls);
		viewInfo.setAfterExplosionTable(getInfoTable());
		spawnNewBalls();
		viewInfo.setNewTable(getInfoTable());
	}

	public ViewInfo getViewInfo() {
		return viewInfo;
	}

	public boolean move(int firstBallX, int firstBallY, int secondBallX,
			int secondBallY) {
		if (Math.abs(firstBallX - secondBallX) > 1
				|| Math.abs(firstBallY - secondBallY) > 1)
			return false;
		if (firstBallX != secondBallX && firstBallY != secondBallY)
			return false;
		if (firstBallX == secondBallX && firstBallY == secondBallY)
			return false;

		// Same color ?
		boolean colorTest = replaceBalls(firstBallX, firstBallY, secondBallX,
				secondBallY);
		if (colorTest == false)
			return false;

		ArrayList<GameBall> destroyedBalls = getDestroyedBalls(firstBallX,
				firstBallY, secondBallX, secondBallY);
		if (destroyedBalls.isEmpty()) {
			System.out.println("Empty :C");
			replaceBalls(firstBallX, firstBallY, secondBallX, secondBallY);
			return false;
		} else {
			System.out.println("Not empty?! :C");
			update(destroyedBalls);
			return true;
		}
	}

}