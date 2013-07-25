package me.ThaH3lper.com.Spawner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.ThaH3lper.com.MobLibrary;
import me.ThaH3lper.com.Entitys.MobsHandler;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SpawnerPlace
{
	private boolean locked = false;
	private Location loc;
	private String cmdMob;
	private int Amount, interval, radious;
	private MobLibrary ml;
	private boolean AlreadySpawnedAdds;
	private int timeSinceLastSpell;
	
	public List<LivingEntity> mobs = new ArrayList<LivingEntity>();
	public List<LivingEntity> adds = new ArrayList<LivingEntity>();
	private int tick = 0;
	private Random r = new Random();
	private int timesSpawned;
	public boolean canCast;
	
	public SpawnerPlace(Location location, String cmdMob, int Amount, int interval, int radious, MobLibrary ml)
	{
		this.loc = location;
		this.cmdMob = cmdMob;
		this.Amount = Amount;
		this.interval = interval;
		this.ml = ml;
		this.radious = radious;
		this.locked = false;
		this.AlreadySpawnedAdds = false;
		this.timesSpawned = 0;
		this.timeSinceLastSpell = 0;
		spawnMob();
	}
	
	public void tick()
	{	
		if(this.timeSinceLastSpell <= 0){
			this.canCast = true;
		}
		if(this.timeSinceLastSpell > 0){
			this.timeSinceLastSpell--;
			this.canCast = false;
		}

		if(this.loc.getChunk().isLoaded() == false){
			this.loc.getChunk().load();
		}
		if(!(this.mobs.isEmpty())){
			for(int i = 0; i >= mobs.size(); i++){
				LivingEntity mob = mobs.get(i);
				if(mob == null){
					mobs.remove(mob);
				}
				if(this.mobs.isEmpty() == false){
					if(mob.getHealth() < 1){
						mobs.remove(mob);
					}
				}
			}
		}
		if(!(this.adds.isEmpty())){
			for(int i = 0; i >= adds.size(); i++){
				LivingEntity mob = adds.get(i);
				if(mob == null){
					mobs.remove(mob);
				}
				if(mob.getHealth() < 1){
					mobs.remove(mob);
				}
			}
		}
		tick++;
		if(tick >= interval && locked == false)
		{
			tick = 0;
			if(mobs.size() >= Amount){
				return;
			}
			else{
				spawnMob();
				this.setAlreadySpawnedAdds(false);
			}
		}
        String display = ml.mobs.getCustomConfig().getString("Mobs." + this.cmdMob + ".Display");
		for(LivingEntity mob:mobs)
		{
			mob.setCustomName(ChatColor.translateAlternateColorCodes('&', display)+ "");
		}
	}
	
	public void spawnMob()
	{
		this.timesSpawned++;
		Location l = getMobSpawnLocation();
		Chunk chunk = l.getChunk();
		chunk.load();
		LivingEntity entity = MobsHandler.SpawnAPI(cmdMob, l, 1f);
		mobs.add(entity);
	}
	
	public Location getMobSpawnLocation()
	{
		double x = (loc.getX()-radious) +(r.nextInt((int) ((loc.getX()+radious)-(loc.getX()-radious))));
		double z = (loc.getZ()-radious) +(r.nextInt((int) ((loc.getZ()+radious)-(loc.getZ()-radious))));
		
		Location l = new Location(loc.getWorld(), x, loc.getY() + 2, z);
		return l;
	}
	
	public Location getLocation()
	{
		return this.loc;
	}
	public int getTimeSinceLastSpell(){
		return this.timeSinceLastSpell;
	}
	public String getCmdMob()
	{
		return this.cmdMob;
	}
	public int getTimesSpawned()
	{
		return this.timesSpawned;
	}
	
	public int getAmount()
	{
		return this.Amount;
	}
	
	public int getInterval()
	{
		return this.interval;
	}
	
	public int getRadius()
	{
		return this.radious;
	}
	public void setTimeSinceLastSpell(int timeTillCanCast){
		this.timeSinceLastSpell = timeTillCanCast;
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
	public boolean AlreadySpawnedAdds(){
		return this.AlreadySpawnedAdds;
	}
	public void setAlreadySpawnedAdds(boolean alreadySpawnedAdds){
		this.AlreadySpawnedAdds = alreadySpawnedAdds;
	}
	public int getTick(){
		return this.tick;
	}
	public void reset(){
		for(LivingEntity mobs:this.adds){
			mobs.remove();
		}
		this.adds.clear();
		for(LivingEntity mobs:this.mobs){
			mobs.remove();
		}
		this.mobs.clear();
		this.setAlreadySpawnedAdds(false);
		this.tick = 0;
	}
}
