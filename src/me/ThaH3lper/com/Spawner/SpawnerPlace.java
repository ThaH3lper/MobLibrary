package me.ThaH3lper.com.Spawner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.ThaH3lper.com.MobLibrary;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SpawnerPlace {

	public Location loc;
	public String cmdMob;
	public int Amount, interval, radious;
	MobLibrary ml;
	
	List<LivingEntity> mobs = new ArrayList<LivingEntity>();
	int tick = 0;
	Random r = new Random();
	
	public SpawnerPlace(Location location, String cmdMob, int Amount, int interval, int radious, MobLibrary ml)
	{
		this.loc = location;
		this.cmdMob = cmdMob;
		this.Amount = Amount;
		this.interval = interval;
		this.ml = ml;
		this.radious = radious;
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
		Location l = getLocation();
		LivingEntity entity = ml.mobHandler.SpawnAPI(cmdMob, l, 1f);
		mobs.add(entity);
	}
	
	public Location getLocation()
	{
		double x = (loc.getX()-radious) +(r.nextInt((int) ((loc.getX()+radious)-(loc.getX()-radious))));
		double z = (loc.getZ()-radious) +(r.nextInt((int) ((loc.getZ()+radious)-(loc.getZ()-radious))));
		
		Location l = new Location(loc.getWorld(), x, loc.getY() + 2, z);
		return l;
	}
	
	public void DeathMob(LivingEntity l)
	{
		if(!mobs.contains(l))
			return;
		mobs.remove(l);
	}
}
