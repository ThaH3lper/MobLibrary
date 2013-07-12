package me.ThaH3lper.com.Spawner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.ThaH3lper.com.MobLibrary;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SpawnerPlace {
	public boolean locked = false;
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
		this.locked = false;
		spawnMob();
	}
	
	public void tick()
	{
		tick++;
		if(tick >= interval && locked == false)
		{
			tick = 0;
			if(mobs.size() >= Amount)
				return;
			else
				spawnMob();
				
		}
		if(tick > 1000000){
			tick = 1;
		}
        String display = ml.mobs.getCustomConfig().getString("Mobs." + this.cmdMob + ".Display");
		for(LivingEntity mob:mobs){
			mob.setCustomName(ChatColor.translateAlternateColorCodes('&', display)+ "");
		}
	}
	
	public void spawnMob()
	{
		Location l = getMobSpawnLocation();
		LivingEntity entity = ml.mobHandler.SpawnAPI(cmdMob, l, 1f);
		mobs.add(entity);
	}
	
	public Location getMobSpawnLocation()
	{
		double x = (loc.getX()-radious) +(r.nextInt((int) ((loc.getX()+radious)-(loc.getX()-radious))));
		double z = (loc.getZ()-radious) +(r.nextInt((int) ((loc.getZ()+radious)-(loc.getZ()-radious))));
		
		Location l = new Location(loc.getWorld(), x, loc.getY() + 2, z);
		return l;
	}
	public Location getLocation(){
		return this.loc;
	}
	public String getCmdMob(){
		return this.cmdMob;
	}
	public int getAmount(){
		return this.Amount;
	}
	public int getInterval(){
		return this.interval;
	}
	public int getRadius(){
		return this.radious;
	}
	public void setLocked(){
		locked = true;
	}
	public List<LivingEntity> getMobsList(){
		return this.mobs;
	}
	public void DeathMob(LivingEntity l)
	{
		if(!mobs.contains(l))
			return;
		mobs.remove(l);
	}
}
