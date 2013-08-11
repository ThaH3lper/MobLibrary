package me.ThaH3lper.com.Spawner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import me.ThaH3lper.com.MobLibrary;
import me.ThaH3lper.com.Entitys.Mob;
import me.ThaH3lper.com.Entitys.MobsHandler;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

public class SpawnerPlace
{
	private boolean locked = false;
	private Location loc;
	private String cmdMob;
	private int Amount, interval, radious;
	//private boolean AlreadySpawnedAdds;
	//private int timeSinceLastSpell;
    //private String display;
	
	private List<Mob> mobs = new ArrayList<Mob>();
	//public List<LivingEntity> adds = new ArrayList<LivingEntity>();
	private int tick = 0;
	private Random r = new Random();
	private int timesSpawned;
	//public boolean canCast;
	
	public SpawnerPlace(Location location, String cmdMob, int Amount, int interval, int radious, MobLibrary ml)
	{
		this.loc = location;
		this.cmdMob = cmdMob;
		this.Amount = Amount;
		this.interval = interval;
		this.radious = radious;
		this.locked = false;
		//this.AlreadySpawnedAdds = false;
		this.timesSpawned = 0;
		//this.timeSinceLastSpell = 0;
        //display = ml.mobs.getCustomConfig().getString("Mobs." + this.cmdMob + ".Display");
		spawnMob();
	}
	
	public void tick()
	{	
		/*if(this.timeSinceLastSpell <= 0)
		{
			this.canCast = true;
		}
		if(this.timeSinceLastSpell > 0)
		{
			this.timeSinceLastSpell--;
			this.canCast = false;
		}*/

		if(!this.loc.getChunk().isLoaded())
		{
			this.loc.getChunk().load();
			Iterator<Mob> itr = mobs.iterator();
			while(itr.hasNext())
			{
				itr.next().loadChunk();
			}
		}
		tick++;
		if(tick >= interval && locked == false)
		{
			tick = 0;
			if(mobs.size() >= Amount)
				return;
			spawnMob();
			//this.setAlreadySpawnedAdds(false);
		}
		Iterator<Mob> itr = mobs.iterator();
		while(itr.hasNext())
		{
			Mob mob = itr.next();
			if(mob == null)
			{
				itr.remove();
				continue;
			}
			if(!mob.getLocation().getChunk().isLoaded())
			{
				mob.getLocation().getChunk().load(true);
			}
			if(mob.isDead())
			{
				mob.remove();
				itr.remove();
				continue;
			}
			if(!mob.isValid())
			{
				mob.remove();
				itr.remove();
				continue;
			}
			mob.setCustomName(mob.getName());
		}
		/*if(!this.mobs.isEmpty())
		{
			itr = mobs.iterator();
			while(itr.hasNext())
			{
				Mob mob = itr.next();
				if(mob.isDead())
				{
					mobs.remove(mob);
				}
				else if(mob.getHealth() <= 0)
				{
					mobs.remove(mob);
				}
				else if(mob.getKiller() != null)
				{
					mobs.remove(mob);
				}
			}
		}*/
		/*if(!(this.adds.isEmpty())){
			for(int i = 0; i >= adds.size(); i++){
				LivingEntity mob = adds.get(i);
				if(mob == null){
					mobs.remove(mob);
				}
				else if(mob.getHealth() < 1){
					mobs.remove(mob);
				}
				else if(mob.getKiller() != null){
					mobs.remove(mob);
				}
			}
		}*/
	}
	
	public void spawnMob()
	{
		this.timesSpawned++;
		Location l = null;
		int counter = 0;
		while(true)
		{
			l = getMobSpawnLocation();
			if(l != null)
				break;
			++counter;
			if(counter >= 25)
				return;
		}
		Chunk chunk = l.getChunk();
		chunk.load();
		Mob mob = MobsHandler.SpawnAPI(cmdMob, l, 1f);
		mobs.add(mob);
	}
	
	public Location getMobSpawnLocation()
	{
		int counter = 0;
		double x = (loc.getX()-radious) +(r.nextInt((int) ((loc.getX()+radious)-(loc.getX()-radious))));
		double z = (loc.getZ()-radious) +(r.nextInt((int) ((loc.getZ()+radious)-(loc.getZ()-radious))));
			
		Location l = new Location(loc.getWorld(), x, loc.getY() + 2, z);
		while(true)
		{
			Block b = l.getBlock();
			if(isSpawnableBlock(b))
			{
				if(isSpawnableBlock(l.clone().add(0,1,0).getBlock()))
				{
					return l;
				}
			}
			l.add(0,1,0);
			++counter;
			if(counter >= radious + 2);
				return null;
		}
	}
	
	public Location getLocation()
	{
		return this.loc;
	}
	/*public int getTimeSinceLastSpell(){
		return this.timeSinceLastSpell;
	}*/
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
	/*public void setTimeSinceLastSpell(int timeTillCanCast){
		this.timeSinceLastSpell = timeTillCanCast;
	}*/
	public void setLocked()
	{
		locked = true;
	}
	
	public List<Mob> getMobsList()
	{
		return this.mobs;
	}
	
	public void DeathMob(LivingEntity l)
	{
		if(!mobs.contains(l))
			return;
		mobs.remove(l);
	}
	
	/*public boolean AlreadySpawnedAdds()
	{
		return this.AlreadySpawnedAdds;
	}*/
	
	public int getAmoutOfMobs()
	{
		return mobs.size();
	}
	
	/*public void setAlreadySpawnedAdds(boolean alreadySpawnedAdds)
	{
		this.AlreadySpawnedAdds = alreadySpawnedAdds;
	}*/
	
	public int getTick()
	{
		return this.tick;
	}
	
	public void clear()
	{
		Iterator<Mob> itr = mobs.iterator();
		while(itr.hasNext())
		{
			Mob temp = itr.next();
			temp.remove();
		}
		mobs.clear();
	}
	
	public void reset()
	{
		/*for(LivingEntity mobs:this.adds)
		{
			mobs.remove();
		}
		this.adds.clear();
		for(LivingEntity mobs:this.mobs)
		{
			mobs.remove();
		}*/
		Iterator<Mob> itr = mobs.iterator();
		while(itr.hasNext())
		{
			itr.next().remove();
			itr.remove();
		}
		
		this.mobs.clear();
		//this.setAlreadySpawnedAdds(false);
		this.tick = 0;
	}
	
	private boolean isSpawnableBlock(Block b)
	{
		if(b.getType().equals(Material.AIR) || b.getType().equals(Material.WATER) || b.getType().equals(Material.WEB) || b.getType().equals(Material.CROPS) || b.getType().equals(Material.DEAD_BUSH)
				|| b.getType().equals(Material.VINE))
			return true;
		return false;
	}
	
	public boolean removeMob(LivingEntity le)
	{
		Iterator<Mob> itr = mobs.iterator();
		while(itr.hasNext())
		{
			Mob temp = itr.next();
			if(temp.getEntity().equals(le))
			{
				itr.remove();
				if(mobs.isEmpty())
					reset();
				return true;
			}
		}
		return false;
	}
	
	public boolean contains(LivingEntity le)
	{
		Iterator<Mob> itr = mobs.iterator();
		while(itr.hasNext())
		{
			Mob temp = itr.next();
			if(temp.getEntity().equals(le))
			{
				return true;
			}
		}
		return false;
	}
}
