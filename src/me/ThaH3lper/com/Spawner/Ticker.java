package me.ThaH3lper.com.Spawner;

public class Ticker implements Runnable
{
	public Ticker()
	{
	}
	
	@Override
	public void run()
	{
		SpawnerHandler.tick();	
	}
}
