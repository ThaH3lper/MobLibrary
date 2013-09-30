package me.ThaH3lper.com.Entitys;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
//import org.bukkit.metadata.FixedMetadataValue;
//import org.bukkit.metadata.MetadataValue;
//import org.bukkit.plugin.Plugin;

import me.ThaH3lper.com.MobLibrary;
import me.ThaH3lper.com.Items.ItemHandler;
import me.ThaH3lper.com.SaveLoad.SaveLoad;
import me.ThaH3lper.com.Spawner.SpawnerHandler;
import me.ThaH3lper.com.Spawner.SpawnerPlace;
import me.frodenkvist.utils.MaterialFromInt;

public class MobsHandler {

	private static MobLibrary ml;
	private static List<MobTemplet> mobTemplets = new ArrayList<MobTemplet>();
	private static List<Mob> mobs = new ArrayList<Mob>(); 
	
	public static void load(MobLibrary instance)
	{
		ml = instance;
		loadAllMobs();
	}
	
	private static boolean loadAllMobs()
	{
		SaveLoad mobs = ml.getMobConfig();
		if(!mobs.getCustomConfig().contains("Mobs"))
			return false;
		for(String Name : mobs.getCustomConfig().getConfigurationSection("Mobs").getKeys(false))
		{
			if(check(Name))
			{
				String mob = mobs.getCustomConfig().getString("Mobs." + Name + ".Mob");
				String display = mobs.getCustomConfig().getString("Mobs." + Name + ".Display");
				double speed = mobs.getCustomConfig().getDouble("Mobs." + Name + ".Speed");
				int health = mobs.getCustomConfig().getInt("Mobs." + Name + ".Health");
				int damage = mobs.getCustomConfig().getInt("Mobs." + Name + ".Damage");
				float aggro = (float)mobs.getCustomConfig().getDouble("Mobs." + Name + ".Aggro");
				boolean despawn = mobs.getCustomConfig().getBoolean("Mobs." + Name + ".Despawn");
				List<String> equip = mobs.getCustomConfig().getStringList("Mobs." + Name + ".Equipment");
				List<String> drops = mobs.getCustomConfig().getStringList("Mobs." + Name + ".Drops");
				List<String> skills = mobs.getCustomConfig().getStringList("Mobs." + Name + ".Skills");
				
				mobTemplets.add(new MobTemplet(Name, mob, display, speed, health, damage, aggro, despawn, equip, drops, skills));
			}
			else
			{
				ml.logger.warning(Name + " could not be loaded! Error in Mobs.yml!");
				return false;
			}
		}
		return true;
	}
	
	public static LivingEntity SpawnAPI(String cmdName, Location loc, float multi)
	{
		if(getTemplet(cmdName) == null)
		{
			Bukkit.broadcastMessage("NULL:" + cmdName);
			return null;
		}
		MobTemplet mt = getTemplet(cmdName);
		LivingEntity l = AllEntitys.SpawnMob(mt.mob, loc, mt.speed, mt.damage, mt.health, mt.aggro, multi);
		
		String display = mt.display.replace("_", " ");
		display = ChatColor.translateAlternateColorCodes('&', display);
		l.setCustomName(display);
		l.setCustomNameVisible(true);
		
		l.setRemoveWhenFarAway(mt.despawn);
		
		setEquipment(l, mt.equip);
		mobs.add(new Mob(l,mt.skills, mt.damage, mt.drops));
		return l;
	}
	
	public static LivingEntity SpawnAPI(String cmdName, Location loc)
	{
		if(getTemplet(cmdName) == null)
		{
			Bukkit.broadcastMessage("NULL:" + cmdName);
			return null;
		}
		MobTemplet mt = getTemplet(cmdName);
		LivingEntity l = AllEntitys.SpawnMob(mt.mob, loc, mt.speed, mt.damage, mt.health, mt.aggro, 1F);
		
		String display = mt.display.replace("_", " ");
		display = ChatColor.translateAlternateColorCodes('&', display);
		l.setCustomName(display);
		l.setCustomNameVisible(true);
		
		l.setRemoveWhenFarAway(mt.despawn);
		
		setEquipment(l, mt.equip);
		mobs.add(new Mob(l,mt.skills, mt.damage, mt.drops));
		return l;
	}
	
	public static List<ItemStack> getDrops(List<String> drops) //TODO might need deprecation!
	{
		List<ItemStack> items = new ArrayList<ItemStack>();
		for(String s : drops)
		{
			String[] parts = s.split(" ");
			if(s.contains(":"))
			{
				Random rand = new Random();
				String[] splits = parts[0].split(":");
				ItemStack stack = new ItemStack(MaterialFromInt.getMaterialFromInt(Integer.parseInt(splits[0])), Integer.parseInt(splits[2]), (short)Integer.parseInt(splits[1]));
				if(rand.nextFloat() < Float.parseFloat(parts[1]))
				{
					items.add(stack);
				}
			}
			else
			{
				if(ItemHandler.getItem(parts[0]) != null)
				{
					Random rand = new Random();
					ItemStack stack = ItemHandler.getItem(parts[0]);
					if(rand.nextFloat() < Float.parseFloat(parts[1]))
					{
						items.add(stack);
					}
				}
			}
		}
		return items;
	}
	
	public static void setEquipment(LivingEntity l, List<String> items)
	{
		for(String s : items)
		{
			String[] splits = s.split(" ");
			if(splits[1].equals("0"))
			{
				l.getEquipment().setItemInHand(ItemHandler.getItem(splits[0]));
				l.getEquipment().setItemInHandDropChance(0f);
			}
			else if(splits[1].equals("4"))
			{
				l.getEquipment().setHelmet(ItemHandler.getItem(splits[0]));
				l.getEquipment().setHelmetDropChance(0f);
			}
			else if(splits[1].equals("3"))
			{
				l.getEquipment().setChestplate(ItemHandler.getItem(splits[0]));
				l.getEquipment().setChestplateDropChance(0f);
			}
			else if(splits[1].equals("2"))
			{
				l.getEquipment().setLeggings(ItemHandler.getItem(splits[0]));
				l.getEquipment().setLeggingsDropChance(0f);
			}
			else if(splits[1].equals("1"))
			{
				l.getEquipment().setBoots(ItemHandler.getItem(splits[0]));
				l.getEquipment().setBootsDropChance(0f);
			}
		}
	}
	
	public static MobTemplet getTemplet(String cmdName)
	{
		for(MobTemplet mt : mobTemplets)
		{
			if(mt.cmdName.equals(cmdName))
				return mt;
		}
		return null;
	}
	public static MobTemplet getTempletDisplay(String display)
	{
		for(MobTemplet mt : mobTemplets)
		{
			String d = mt.display.replace("_", " ");
			d = ChatColor.translateAlternateColorCodes('&', d);
			if(d.equals(display))
				return mt;
		}
		return null;
	}
	
	private static boolean check(String s)
	{
		SaveLoad mobs = ml.getMobConfig();
		if(!mobs.getCustomConfig().contains("Mobs." + s + ".Mob"))
			return false;
		if(!mobs.getCustomConfig().contains("Mobs." + s + ".Display"))
			return false;
		if(!mobs.getCustomConfig().contains("Mobs." + s + ".Speed"))
			return false;
		if(!mobs.getCustomConfig().contains("Mobs." + s + ".Health"))
			return false;
		if(!mobs.getCustomConfig().contains("Mobs." + s + ".Damage"))
			return false;
		if(!mobs.getCustomConfig().contains("Mobs." + s + ".Aggro"))
			return false;
		if(!mobs.getCustomConfig().contains("Mobs." + s + ".Despawn"))
			return false;
		if(!mobs.getCustomConfig().contains("Mobs." + s + ".Equipment"))
			return false;
		if(!mobs.getCustomConfig().contains("Mobs." + s + ".Drops"))
			return false;
		return true;
	}
	
	public static MobTemplet getMobTempletFromSpawner(LivingEntity l)
	{
		for(SpawnerPlace sign : SpawnerHandler.getSpawners())
		{
			for(LivingEntity mob:sign.getMobsList())
			{
				if(mob == l)
				{
					return getMobTempletFromCmdName(sign.getCmdMob());
				}
			}
		}
		return null;
	}
	
	public static MobTemplet getMobTempletFromCmdName(String cmdName)
	{
		for(MobTemplet mt : mobTemplets)
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
	
	public static List<String> getSkills(LivingEntity l)
	{
		if(getMobTempletFromSpawner(l) != null)
		{
			MobTemplet mt = getMobTempletFromSpawner(l);
			return mt.skills;
		}
		return null;
	}
	
	public static List<MobTemplet> getMobTemplets()
	{
		return mobTemplets;
	}
	
	public static void clearMobs()
	{
		mobs.clear();
		if(SpawnerHandler.getSpawners().isEmpty())
		{
			return;
		}
		else
		{
			for(SpawnerPlace sp : SpawnerHandler.getSpawners())
			{
				sp.setLocked();
				List<LivingEntity> mobs = sp.getMobsList();
				if(!mobs.isEmpty())
				{
					for(LivingEntity mob:mobs)
					{
						mob.remove();
						mob = null;
					}
				}
			}
			
			SpawnerHandler.clear();
			
			for(Mob mob : mobs)
			{
				if(mob == null)
					continue;
				if(mob.getEntity() == null)
					continue;
				mob.getEntity().remove();
				mob = null;
			}
			mobs.clear();
		}
	}
	
	public static boolean addMob(Mob m)
	{
		return mobs.add(m);
	}
	
	public static Mob getMob(LivingEntity le)
	{
		for(Mob mob : mobs)
		{
			if(mob.getEntity().equals(le))
				return mob;
		}
		return null;
	}
	
	public static boolean removeMob(LivingEntity le)
	{
		for(Mob mob : mobs)
		{
			if(mob.getEntity().equals(le))
				return mobs.remove(mob);
		}
		return false;
	}
}
