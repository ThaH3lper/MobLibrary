package me.ThaH3lper.com.Spawner;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.MobLibrary;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SpawnerPlace {

	public Location loc;
	public String cmdMob;
	public int Amount, interval;
	MobLibrary ml;
	
	List<LivingEntity> mobs = new ArrayList<LivingEntity>();
	int tick = 0;
	
	public SpawnerPlace(Location location, String cmdMob, int Amount, int interval, MobLibrary ml)
	{
		this.loc = loc;
		this.cmdMob = cmdMob;
		this.Amount = Amount;
		this.interval = interval;
		this.ml = ml;
	}
	
	public void tick()
	{
		tick++;
		if(tick >= interval)
		{
			tick = 0;
			if(mobs.size() >= Amount)
				return;
			else
				spawnMob();
				
		}
	}
	
	public void spawnMob()
	{
		Location l = new Location(loc.getWorld(), loc.getX(), loc.getY() + 2, loc.getZ());
		LivingEntity entity = ml.mobHandler.SpawnAPI(cmdMob, l, 1f);
		mobs.add(entity);
	}
}
