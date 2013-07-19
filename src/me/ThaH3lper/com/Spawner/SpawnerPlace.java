package me.ThaH3lper.com.Spawner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.ThaH3lper.com.MobLibrary;
import me.ThaH3lper.com.Entitys.MobsHandler;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SpawnerPlace
{
	private boolean locked = false;
	private Location loc;
	private String cmdMob;
	private int amount, interval, radius;
	private MobLibrary ml;
	
	private List<LivingEntity> mobs = new ArrayList<LivingEntity>();
	private int tick = 0;
	private Random r = new Random();
	
	public SpawnerPlace(Location location, String cmdMob, int amount, int interval, int radius, MobLibrary ml)
	{
		this.loc = location;
		this.cmdMob = cmdMob;
		this.amount = amount;
		this.interval = interval;
		this.ml = ml;
		this.radius = radius;
		this.locked = false;
		spawnMob();
	}
	
	public void tick()
	{
		tick++;
		if(tick >= interval && !locked)
		{
			tick = 0;
			if(mobs.size() >= amount)
				return;
			else
				spawnMob();
		}
		if(tick > 1000000)
		{
			tick = 1;
		}
        String display = ml.getMobConfig().getCustomConfig().getString("Mobs." + this.cmdMob + ".Display");
		for(LivingEntity mob : mobs)
		{
			mob.setCustomName(ChatColor.translateAlternateColorCodes('&', display) + "");
		}
	}
	
	public void spawnMob()
	{
		Location l = getMobSpawnLocation();
		LivingEntity entity = MobsHandler.SpawnAPI(cmdMob, l, 1f);
		mobs.add(entity);
	}
	
	public Location getMobSpawnLocation()
	{
		double x = (loc.getX()-radius) +(r.nextInt((int) ((loc.getX()+radius)-(loc.getX()-radius))));
		double z = (loc.getZ()-radius) +(r.nextInt((int) ((loc.getZ()+radius)-(loc.getZ()-radius))));
		
		Location l = new Location(loc.getWorld(), x, loc.getY() + 2, z);
		return l;
	}
	
	public Location getLocation()
	{
		return this.loc;
	}
	
	public String getCmdMob()
	{
		return this.cmdMob;
	}
	
	public int getAmount()
	{
		return this.amount;
	}
	
	public int getInterval()
	{
		return this.interval;
	}
	
	public int getRadius()
	{
		return this.radius;
	}
	
	public void setLocked()
	{
		locked = true;
	}
	
	public List<LivingEntity> getMobsList()
	{
		return this.mobs;
	}
	
	public void DeathMob(LivingEntity l)
	{
		if(!mobs.contains(l))
			return;
		mobs.remove(l);
	}
	
	public void save()
	{
		List<String> saves = MobLibrary.plugin.getSavesConfig().getCustomConfig().getStringList("Spawners");
		saves.add(getLocation().getBlockX() + "," + getLocation().getBlockY() + "," + getLocation().getBlockZ() + "," + getLocation().getWorld().getName() +
					"," + getCmdMob() + "," + getAmount() + "," + getInterval() + "," + getRadius());
		MobLibrary.plugin.getSavesConfig().getCustomConfig().set("Spawners", saves);
		MobLibrary.plugin.getSavesConfig().saveCustomConfig();
	}
}
