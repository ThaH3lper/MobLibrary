package me.ThaH3lper.com.Spawner;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.Entitys.MobTemplet;
import me.ThaH3lper.com.Entitys.MobsHandler;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;

public class SpawnerHandler
{
	private static List<SpawnerPlace> spawners = new ArrayList<SpawnerPlace>();
	
	public static boolean addSpawner(SpawnerPlace sp)
	{
		return spawners.add(sp);
	}
	
	public static SpawnerPlace getSpawner(LivingEntity l)
	{
		for(SpawnerPlace sp : spawners)
		{
			for(LivingEntity mob : sp.getMobsList())
			{
				if(mob == l)
				{
					return sp;
				}
			}
		}
		return null;
	}
	
	public static MobTemplet getMobTempletFromSpawner(LivingEntity l)
	{
		for(SpawnerPlace sp : spawners)
		{
			for(LivingEntity mob : sp.getMobsList())
			{
				if(mob == l)
				{
					return getMobTempletFromCmdName(sp.getCmdMob());
				}
			}
		}
		return null;
	}
	
	public static MobTemplet getMobTemplet(LivingEntity l)
	{
		for(MobTemplet mt : MobsHandler.getMobTemplets())
		{
			String name = mt.display;
			name = ChatColor.translateAlternateColorCodes('&', name);
			name = name.replace(" ", "_");

			if(l.getCustomName() != null)
			{
				if(l.getCustomName().equals(name))
				{
					return mt;
				}
			}
		}
		return null;
	}
	
	public static MobTemplet getMobTempletFromCmdName(String cmdName)
	{
		for(MobTemplet mt : MobsHandler.getMobTemplets())
		{
			String name = mt.cmdName;
			if(cmdName != null)
			{
				if(cmdName.equals(name))
				{
					return mt;
				}
			}
		}
		return null;
	}
	
	public static List<SpawnerPlace> getSpawners()
	{
		return spawners;
	}
	
	public static boolean removeSpawner(SpawnerPlace sp)
	{
		return spawners.remove(sp);
	}
	
	public static void clear()
	{
		spawners.clear();
	}
}
