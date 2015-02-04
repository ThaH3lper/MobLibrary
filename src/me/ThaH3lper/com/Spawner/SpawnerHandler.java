package me.ThaH3lper.com.Spawner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.ThaH3lper.com.Entitys.Mob;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SpawnerHandler
{
	private static List<SpawnerPlace> spawners = new ArrayList<SpawnerPlace>();
	
	public static boolean addSpawner(SpawnerPlace sp)
	{
		return spawners.add(sp);
	}
	
	public static boolean removeSpawner(Location loc)
	{
		Iterator<SpawnerPlace> itr = spawners.iterator();
		while(itr.hasNext())
		{
			SpawnerPlace temp = itr.next();
			if(isSameBlockLoc(loc,temp.getLocation()))
			{
				itr.remove();
				return true;
			}
		}
		return false;
	}
	
	public static SpawnerPlace getSpawner(Location loc)
	{
		Iterator<SpawnerPlace> itr = spawners.iterator();
		while(itr.hasNext())
		{
			SpawnerPlace temp = itr.next();
			if(isSameBlockLoc(loc,temp.getLocation()))
			{
				return temp;
			}
		}
		return null;
	}
	
	public static SpawnerPlace getSpawner(LivingEntity le)
	{
		Iterator<SpawnerPlace> itr = spawners.iterator();
		while(itr.hasNext())
		{
			SpawnerPlace temp = itr.next();
			if(temp.contains(le))
			{
				return temp;
			}
		}
		return null;
	}
	
	public static void tick()
	{
		Iterator<SpawnerPlace> itr = spawners.iterator();
		while(itr.hasNext())
		{
			SpawnerPlace temp = itr.next();
			temp.tick();
		}
	}
	
	public static boolean isSpawnerInChunk(Chunk c)
	{
		Iterator<SpawnerPlace> itr = spawners.iterator();
		while(itr.hasNext())
		{
			SpawnerPlace temp = itr.next();
			if(temp.getLocation().getChunk().equals(c))
				return true;
			Iterator<Mob> mobItr = temp.getMobsList().iterator();
			while(mobItr.hasNext())
			{
				Mob mTemp = mobItr.next();
				if(mTemp == null)
				{
					itr.remove();
					continue;
				}
				if(mTemp.getLocation().getChunk().equals(c))
					return true;
			}
		}
		return false;
	}
	
	public static void removeMob(LivingEntity le)
	{
		Iterator<SpawnerPlace> itr = spawners.iterator();
		while(itr.hasNext())
		{
			SpawnerPlace sp = itr.next();
			if(sp.removeMob(le))
				return;
		}
	}
	
	public static void clear()
	{
		Iterator<SpawnerPlace> itr = spawners.iterator();
		while(itr.hasNext())
		{
			itr.next().clear();
		}
	}
	
	public static Iterator<SpawnerPlace> getSpanwerItr()
	{
		return spawners.iterator();
	}
	
	private static boolean isSameBlockLoc(Location loc1, Location loc2)
	{
		if(loc1 == null)
			return false;
		if(loc2 == null)
			return false;
		if(loc1.getBlockX() == loc2.getBlockX() && loc1.getBlockY() == loc2.getBlockY() && loc1.getBlockZ() == loc2.getBlockZ() && loc1.getWorld().getName().equalsIgnoreCase(loc2.getWorld().getName()))
			return true;
		return false;
	}
}
