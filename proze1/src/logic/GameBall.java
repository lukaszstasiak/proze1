package logic;

import info.BallType;

public class GameBall
{
	private BallType ballType;

	public GameBall(BallType ballType)
	{
		this.setBallType(ballType);
	}

	public BallType getBallType()
	{
		return ballType;
	}
	public void setBallType(BallType ballType)
	{
		this.ballType = ballType;
	}
	public boolean isDestroyed()
	{
		if(ballType == BallType.DESTROYED)
			return true;
		else
			return false;
	}
}