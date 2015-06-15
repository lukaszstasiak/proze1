package app;

public class AnimThread implements Runnable
{
	int delay;
	Thread thread;
	Boolean isTicking;
	RefreshInterface refresh;
	
	public AnimThread(int delay, RefreshInterface refresh)
	{
		this.delay = delay;
		this.refresh = refresh;
		stop();
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop()
	{
		isTicking = false;
	}
	
	public void start()
	{
		isTicking = true;
	}

	@Override
	public void run()
	{
		while(true)
		{
			if(refresh != null && isTicking)
				refresh.doRefresh();
			try
			{
				Thread.sleep(delay);
			}
			catch(InterruptedException interruptedexception)
			{
				System.out.println("WARNING: Ticker thread interrupted.");
			}
		}
		
	}
}