package logic;

import info.BallType;

import java.util.ArrayList;
import java.util.Random;


public class Line
{
	private ArrayList<GameBall> balls;

	
	public Line()
	{
		balls = new ArrayList<GameBall>();
	}

	public ArrayList<GameBall> getBalls()
	{
		return balls;
	}
	
	public void spawnRandomBall()
	{
		Random rand = new Random();
		BallType ballType = BallType.getByInt(rand.nextInt(4) + 1);
		GameBall ball = new GameBall(ballType);
		balls.add(0, ball);
	}
	
	public ArrayList<GameBall> getBallsToDestroy(int pos)
	{
		ArrayList<GameBall> result = new ArrayList<GameBall>();
		ArrayList<GameBall> temp = new ArrayList<GameBall>();
		BallType oldType, currentType;
		
		oldType = BallType.DESTROYED;
		
		for(GameBall ball: balls)
		{
			currentType = ball.getBallType();
			if(oldType != currentType)
			{
				if(temp.size() >= 3)
				{
					if(temp.contains(getBall(pos)))
					{
						result.addAll(temp);
					}
				}
				temp.clear();
				oldType = currentType;
			}
			temp.add(ball);
		}
		if(temp.size() >= 3)
		{
			if(temp.contains(getBall(pos)))
			{
				result.addAll(temp);
			}
		}
		return result;
	}
	
	public void addBall(GameBall ball)
	{
		balls.add(ball);
	}
	
	public void clear()
	{
		balls.clear();
	}
	
	public int getIndex(GameBall ball)
	{
		return balls.indexOf(ball);
	}
	public int getSize()
	{
		return balls.size();
	}
	
	public GameBall getBall(int pos)
	{
		return balls.get(pos);
	}
	
	public void removeDestroyed()
	{
		ArrayList<GameBall> toDestroy = new ArrayList<GameBall>();
		
		for(GameBall ball: balls)
		{
			if(ball.isDestroyed())
			{
				System.out.println("isDestroyed!");
				toDestroy.add(ball);
			}
		}
		
		balls.removeAll(toDestroy);
	}
}