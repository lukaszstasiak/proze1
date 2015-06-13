package info;

public class ViewInfo
{
	private BallType[][] oldTable;
	private BallType[][] afterExplosionTable;
	private BallType[][] newTable;
	
	public BallType[][] getOldTable()
	{
		return oldTable;
	}
	public void setOldTable(BallType[][] oldTable)
	{
		this.oldTable = oldTable;
	}
	public BallType[][] getAfterExplosionTable()
	{
		return afterExplosionTable;
	}
	public void setAfterExplosionTable(BallType[][] afterExplosionTable)
	{
		this.afterExplosionTable = afterExplosionTable;
	}
	public BallType[][] getNewTable()
	{
		return newTable;
	}
	public void setNewTable(BallType[][] newTable)
	{
		this.newTable = newTable;
	}
}