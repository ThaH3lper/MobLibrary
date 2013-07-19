package me.ThaH3lper.com.Spawner;

import me.ThaH3lper.com.MobLibrary;

public class Ticker implements Runnable
{
	MobLibrary ml;
	
	public Ticker(MobLibrary ml)
	{
		this.ml = ml;
	}
	
	@Override
	public void run()
	{
		for(SpawnerPlace sp : SpawnerHandler.getSpawners())
		{
			sp.tick();
		}	
	}

}
