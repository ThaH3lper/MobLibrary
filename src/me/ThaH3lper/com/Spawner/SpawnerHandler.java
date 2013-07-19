package me.ThaH3lper.com.Spawner;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.MobLibrary;
import me.ThaH3lper.com.Entitys.MobTemplet;
import me.ThaH3lper.com.Entitys.MobsHandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
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
	
	public static void load()
	{
		FileConfiguration config = MobLibrary.plugin.getSavesConfig().getCustomConfig();
		List<String> savedSpawners = config.getStringList("Spawners");
		for(String s : savedSpawners)
		{
			String[] split = s.split(",");
			if(split.length != 8)
				continue;
			Location loc = new Location(Bukkit.getWorld(split[3]), Integer.valueOf(split[0]),Integer.valueOf(split[1]),Integer.valueOf(split[2]));
			SpawnerPlace sp = new SpawnerPlace(loc, split[4], Integer.valueOf(split[5]), Integer.valueOf(split[6]), Integer.valueOf(split[7]), MobLibrary.plugin);
			Chunk chunk = loc.getChunk();
			chunk.load(true);
			if(loc.getBlock().getType() != Material.SIGN)
			{
				Block block = loc.getBlock();
				block.setType(Material.SIGN_POST);
			}
			Sign sign = (Sign)loc.getBlock().getState();
			sign.setLine(0, ChatColor.GREEN + "[MobSpawner]");
			sign.setLine(1, "" + sp.getRadius());
			sign.setLine(2, sp.getCmdMob());
			sign.setLine(3, sp.getAmount() + "i,"+ sp.getInterval() + "s");
			sign.update();
			spawners.add(sp);
		}
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
