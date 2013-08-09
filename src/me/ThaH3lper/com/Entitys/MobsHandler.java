package me.ThaH3lper.com.Entitys;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
//import org.bukkit.metadata.FixedMetadataValue;
//import org.bukkit.metadata.MetadataValue;
//import org.bukkit.plugin.Plugin;

import me.ThaH3lper.com.MobLibrary;
import me.frodenkvist.armoreditor.Store;

public class MobsHandler
{
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
		if(!ml.mobs.getCustomConfig().contains("Mobs"))
			return false;
		for(String Name : ml.mobs.getCustomConfig().getConfigurationSection("Mobs").getKeys(false))
		{
			if(check(Name))
			{
				String mob = ml.mobs.getCustomConfig().getString("Mobs." + Name + ".Mob");
				String display = ml.mobs.getCustomConfig().getString("Mobs." + Name + ".Display");
				double speed = ml.mobs.getCustomConfig().getDouble("Mobs." + Name + ".Speed");
				int health = ml.mobs.getCustomConfig().getInt("Mobs." + Name + ".Health");
				int damage = ml.mobs.getCustomConfig().getInt("Mobs." + Name + ".Damage");
				float aggro = (float)ml.mobs.getCustomConfig().getDouble("Mobs." + Name + ".Aggro");
				boolean despawn = ml.mobs.getCustomConfig().getBoolean("Mobs." + Name + ".Despawn");
				List<String> equip = ml.mobs.getCustomConfig().getStringList("Mobs." + Name + ".Equipment");
				List<String> drops = ml.mobs.getCustomConfig().getStringList("Mobs." + Name + ".Drops");
				List<String> skills = ml.mobs.getCustomConfig().getStringList("Mobs." + Name + ".Skills");
				
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
	
	public static Mob SpawnAPI(String cmdName, Location loc, float multi)
	{
		if(getTemplet(cmdName) == null)
		{
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
		
		Mob mob = new Mob(l, mt.damage, mt.drops, mt.skills);
		mobs.add(mob);
		return mob;
	}
	
	public static List<ItemStack> getDrops(List<String> drops)
	{
		List<ItemStack> items = new ArrayList<ItemStack>();
		for(String s : drops)
		{
			String[] parts = s.split(" ");
			if(s.contains(":"))
			{
				Random rand = new Random();
				String[] splits = parts[0].split(":");
				ItemStack stack = new ItemStack(Material.getMaterial(Integer.parseInt(splits[0])), Integer.parseInt(splits[2]), (short)Integer.parseInt(splits[1]));
				if(rand.nextFloat() < Float.parseFloat(parts[1]))
				{
					items.add(stack);
				}
			}
			else
			{
				if(ml.loadItems.getItem(parts[0]) != null)
				{
					Random rand = new Random();
					ItemStack stack = ml.loadItems.getItem(parts[0]);
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
		Iterator<String> itr = items.iterator();
		while(itr.hasNext())
		{
			ItemStack is = null;
			String s = itr.next();
			String[] split = s.split(" ");
			if(s.contains(":"))
			{
				String[] parts = split[0].split(":");
				is = new ItemStack(Integer.valueOf(parts[0]),0,Short.valueOf(parts[1]));
			}
			else
			{
				if(isInteger(split[0]))
				{
					is = new ItemStack(Integer.valueOf(split[0]));
				}
				else
				{
					is = ml.loadItems.getItem(split[0]);
					if(is == null)
						is = Store.getItem(split[0]);
				}
			}
			if(split[1].equals("0"))
			{
				l.getEquipment().setItemInHand(is);
				l.getEquipment().setItemInHandDropChance(0f);
			}
			else if(split[1].equals("4"))
			{
				l.getEquipment().setHelmet(is);
				l.getEquipment().setHelmetDropChance(0f);
			}
			else if(split[1].equals("3"))
			{
				l.getEquipment().setChestplate(is);
				l.getEquipment().setChestplateDropChance(0f);
			}
			else if(split[1].equals("2"))
			{
				l.getEquipment().setLeggings(is);
				l.getEquipment().setLeggingsDropChance(0f);
			}
			else if(split[1].equals("1"))
			{
				l.getEquipment().setBoots(is);
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
		if(!ml.mobs.getCustomConfig().contains("Mobs." + s + ".Mob"))
			return false;
		if(!ml.mobs.getCustomConfig().contains("Mobs." + s + ".Display"))
			return false;
		if(!ml.mobs.getCustomConfig().contains("Mobs." + s + ".Speed"))
			return false;
		if(!ml.mobs.getCustomConfig().contains("Mobs." + s + ".Health"))
			return false;
		if(!ml.mobs.getCustomConfig().contains("Mobs." + s + ".Damage"))
			return false;
		if(!ml.mobs.getCustomConfig().contains("Mobs." + s + ".Aggro"))
			return false;
		if(!ml.mobs.getCustomConfig().contains("Mobs." + s + ".Despawn"))
			return false;
		if(!ml.mobs.getCustomConfig().contains("Mobs." + s + ".Equipment"))
			return false;
		if(!ml.mobs.getCustomConfig().contains("Mobs." + s + ".Drops"))
			return false;
		return true;
	}
	
	/*public static MobTemplet getMobTempletFromSpawner(LivingEntity l)
	{
		for(SpawnerPlace sign: ml.spawnerList)
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
	}*/
	
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
	
	/*public static List<String> getSkills(LivingEntity l)
	{
		if(getMobTempletFromSpawner(l) != null)
		{
			MobTemplet mt = getMobTempletFromSpawner(l);
			return mt.skills;
		}
		
		return null;
	}*/
	
	public static List<MobTemplet> getMobTemplets()
	{
		return mobTemplets;
	}
	
	public static void clearMobs()
	{
		Iterator<Mob> itr = mobs.iterator();
		while(itr.hasNext())
		{
			itr.next().remove();
		}
		mobs.clear();
		/*if(MobLibrary.plugin.spawnerList.isEmpty())
		{
			return;
		}
		else
		{
			for(int i = 0; i<= MobLibrary.plugin.spawnerList.size() - 1; i++)
			{
				MobLibrary.plugin.spawnerList.get(i).setLocked();
				List<LivingEntity> mobs = MobLibrary.plugin.spawnerList.get(i).getMobsList();
				if(!mobs.isEmpty())
				{
					for(LivingEntity mob:mobs)
					{
						if(mob != null){
							mob.remove();
						}
						
					}
				}
			}
			MobLibrary.plugin.spawnerList.clear();
		}*/
	}
	
	/*public static SpawnerPlace getSpawnerFromMob(LivingEntity l)
	{
		for(SpawnerPlace sign: ml.spawnerList)
		{
			for(LivingEntity mob:sign.getMobsList())
			{
				if(mob == l)
				{
					return sign;
				}
			}
		}
		return null;
	}*/
	
	/*public static void clearFromSkillLists(LivingEntity mob)
	{
		if(SpawnMobs.usedSkill.contains(mob))
		{
			SpawnMobs.usedSkill.remove(mob);
		}
		if(MobsHandler.getSpawnerFromMob(mob).adds.contains(mob))
		{
			MobsHandler.getSpawnerFromMob(mob).adds.remove(mob);
		}
	}*/
	
	public static Mob getMob(LivingEntity le)
	{
		Iterator<Mob> itr = mobs.iterator();
		while(itr.hasNext())
		{
			Mob temp = itr.next();
			if(temp.getEntity().equals(le))
				return temp;
		}
		return null;
	}
	
	private static boolean isInteger(String s)
	{
		try
		{
			Integer.valueOf(s);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
