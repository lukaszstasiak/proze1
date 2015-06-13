package logic;

import info.BallType;
import info.ViewInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Map
{
	private ArrayList<Line> verticalLines, horizontalLines;
	private ViewInfo viewInfo;
	private int sizeX,sizeY;
	
	public Map(int sizeX, int sizeY)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		verticalLines = new ArrayList<Line>();
		horizontalLines = new ArrayList<Line>();
		
		for(int i=0;i<sizeX;i++)
			verticalLines.add(new Line());
		for(int i=0;i<sizeY;i++)
			horizontalLines.add(new Line());
		
		viewInfo = new ViewInfo();
		
		spawnNewBalls();
		
		viewInfo.setOldTable(getInfoTable());
		viewInfo.setAfterExplosionTable(getInfoTable());
		viewInfo.setNewTable(getInfoTable());
	}
	
	private void updateHorizontalLines()
	{
		for(Line line: horizontalLines)
		{
			line.clear();
		}
		
		for(Line line: verticalLines)
		{
			ArrayList<GameBall> balls = line.getBalls();
			for(GameBall ball: balls)
			{
				horizontalLines.get(line.getIndex(ball)).addBall(ball);
			}
		}
	}
		
	private void spawnNewBalls()
	{
		for(Line line: verticalLines)
		{
			line.removeDestroyed();
			while(line.getSize() < sizeY)
			{
				line.spawnRandomBall();
			}
		}
		updateHorizontalLines();
	}
	
	private Line getHorizontalLine(int pos)
	{
		return horizontalLines.get(pos-1);
	}
	
	private void replaceBalls(int firstBallX, int firstBallY, int secondBallX, int secondBallY)
	{
		GameBall firstBall = getHorizontalLine(firstBallY).getBall(firstBallX);
		GameBall secondBall = getHorizontalLine(secondBallY).getBall(secondBallX);
		GameBall hold = firstBall;
		hold = firstBall;
		firstBall = secondBall;
		secondBall = hold;
	}
	
	private ArrayList<GameBall> getDestroyedBalls()
	{
		ArrayList<GameBall> destroyedBalls = new ArrayList<GameBall>();
		Set<GameBall> set = new HashSet<GameBall>();
		
		for(Line line: verticalLines)
		{
			ArrayList<GameBall> destroyedBallsInLine = line.getBallsToDestroy();
			if(!destroyedBallsInLine.isEmpty())
				set.addAll(destroyedBallsInLine);
		}
		for(Line line: horizontalLines)
		{
			ArrayList<GameBall> destroyedBallsInLine = line.getBallsToDestroy();
			if(!destroyedBallsInLine.isEmpty())
				set.addAll(destroyedBallsInLine);
		}
		destroyedBalls.addAll(set);
		
		return destroyedBalls;
	}
	
	private void markDestroyedBalls(ArrayList<GameBall> destroyedBalls)
	{
		/*for(Line line: horizontalLines)
		{
			ArrayList<GameBall> balls = line.getBalls();
			for(GameBall ball: balls)
			{
				if(destroyedBalls.contains(ball))
				{
					ball.setBallType(BallType.DESTROYED);
				}
			}
		}*/
		
		for(GameBall ball: destroyedBalls)
		{
			ball.setBallType(BallType.DESTROYED);
		}
	}
	
	private BallType[][] getInfoTable()
	{
		BallType[][] infoTable = new BallType[sizeX][sizeY];
		
		int y = 0;
		for(Line line: horizontalLines)
		{
			ArrayList<GameBall> balls = line.getBalls();
			int x = 0;
			for(GameBall ball: balls)
			{
				infoTable[x][y] = ball.getBallType();
				x++;
			}
			y++;
		}
		
		return infoTable;
	}
	
	private void update(ArrayList<GameBall> destroyedBalls)
	{
		viewInfo = new ViewInfo();
		viewInfo.setOldTable(viewInfo.getNewTable());
		markDestroyedBalls(destroyedBalls);
		viewInfo.setAfterExplosionTable(getInfoTable());
		spawnNewBalls();
		viewInfo.setNewTable(getInfoTable());
	}
	
	public ViewInfo getViewInfo()
	{
		return viewInfo;
	}
	
	public boolean move(int firstBallX, int firstBallY, int secondBallX, int secondBallY)
	{
		replaceBalls(firstBallX,firstBallY,secondBallX,secondBallY);
		
		ArrayList<GameBall> destroyedBalls = getDestroyedBalls();
		if(destroyedBalls.isEmpty())
		{
			replaceBalls(firstBallX,firstBallY,secondBallX,secondBallY);
			return false;
		}
		else
		{
			update(destroyedBalls);
			return true;
		}
	}
	
}