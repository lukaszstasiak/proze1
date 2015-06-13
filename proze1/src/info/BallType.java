package info;

public enum BallType
{
	DESTROYED,
	RED,
	GREEN,
	YELLOW,
	BLUE;
	
	private int toInt;
	
	static
	{
		DESTROYED.toInt = 0;
		RED.toInt = 1;
		GREEN.toInt = 2;
		YELLOW.toInt = 3;
		BLUE.toInt = 4;
	}
	 
	public int getInt()
	{
		return toInt;
	}
	
	public static BallType getByInt(int i)
    {
    	switch(i)
    	{
    	case 0:
    		return BallType.DESTROYED;
    	case 1:
    		return BallType.RED;
    	case 2:
    		return BallType.GREEN;
    	case 3:
    		return BallType.YELLOW;
    	case 4:
    		return BallType.BLUE;
    	default:
    		return BallType.DESTROYED;
    	}
    }
}
