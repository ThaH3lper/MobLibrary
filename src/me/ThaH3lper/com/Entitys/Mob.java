package me.ThaH3lper.com.Entitys;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.ThaH3lper.com.MobLibrary;
import me.ThaH3lper.com.Skills.Detonate;
import me.ThaH3lper.com.Skills.DragIn;
import me.ThaH3lper.com.Skills.Enrage;
import me.ThaH3lper.com.Skills.FireStorm;
import me.ThaH3lper.com.Skills.HealthDepend;
import me.ThaH3lper.com.Skills.Ignite;
import me.ThaH3lper.com.Skills.LightningStorm;
import me.ThaH3lper.com.Skills.Potion;
import me.ThaH3lper.com.Skills.Shuffle;
import me.ThaH3lper.com.Skills.Skill;
import me.ThaH3lper.com.Skills.SpawnMobs;
import me.ThaH3lper.com.Skills.Teleport;
import me.ThaH3lper.com.Skills.Tnt;
import me.ThaH3lper.com.Skills.Toss;
import me.ThaH3lper.com.Skills.UsableOnce;
import me.frodenkvist.armoreditor.Store;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Mob
{
	private int damage;
	private String name;
	private List<String> drops = new ArrayList<String>();
	private LivingEntity entity;
	private List<Skill> skills = new ArrayList<Skill>();
	private List<Mob> adds = new ArrayList<Mob>();
	
	public Mob(LivingEntity entity, int damage, String name, List<String> drops, List<String> skills)
	{
		this.entity = entity;
		this.damage = damage;
		this.name = name;
		this.drops = drops;
		Iterator<String> itr = skills.iterator();
		while(itr.hasNext())
		{
			String s = itr.next();
			String[] split = s.split(" ");
			if(split.length <= 0)
				continue;
			if(split[0].equalsIgnoreCase("dragin"))
			{
				if(split.length != 3)
					continue;
				int radius = Integer.valueOf(split[1]);
				double chance = Double.valueOf(split[2]);
				this.skills.add(new DragIn(chance, radius));
			}
			else if(split[0].equalsIgnoreCase("tnt"))
			{
				if(split.length != 3)
					continue;
				int radius = Integer.valueOf(split[1]);
				double chance = Double.valueOf(split[2]);
				this.skills.add(new Tnt(chance, radius));
			}
			else if(split[0].equalsIgnoreCase("teleport"))
			{
				if(split.length != 3)
					continue;
				int radius = Integer.valueOf(split[1]);
				double chance = Double.valueOf(split[2]);
				this.skills.add(new Teleport(chance, radius));
			}
			else if(split[0].equalsIgnoreCase("ignite"))
			{
				if(split.length != 4)
					continue;
				int radius = Integer.valueOf(split[1]);
				int duration = Integer.valueOf(split[2]);
				double chance = Double.valueOf(split[3]);
				this.skills.add(new Ignite(chance, radius, duration));
			}
			else if(split[0].equalsIgnoreCase("potion"))
			{
				if(split.length != 3)
					continue;
				String[] data = split[1].split(":");
				if(data.length != 4)
					continue;
				int radius = Integer.valueOf(data[3]);
				double chance = Double.valueOf(split[2]);
				PotionEffect potion = new PotionEffect(PotionEffectType.getByName(data[0]), Integer.parseInt(data[1]) * 20, Integer.parseInt(data[2]) - 1);
				this.skills.add(new Potion(chance, radius, potion));
			}
			else if(split[0].equalsIgnoreCase("toss"))
			{
				if(split.length != 4)
					continue;
				int radius = Integer.valueOf(split[1]);
				int power = Integer.valueOf(split[2]);
				double chance = Double.valueOf(split[3]);
				this.skills.add(new Toss(chance, radius, power));
			}
			else if(split[0].equalsIgnoreCase("spawn"))
			{
				if(split.length != 5)
					continue;
				String cmdName = split[1];
				int healthNeedToCast = Integer.valueOf(split[2]);
				int amount = Integer.valueOf(split[3]);
				double chance = Double.valueOf(split[4]);
				this.skills.add(new SpawnMobs(chance, cmdName, amount, healthNeedToCast));
			}
			else if(split[0].equalsIgnoreCase("firestorm"))
			{
				if(split.length != 3)
					continue;
				int radius = Integer.valueOf(split[1]);
				double chance = Double.valueOf(split[2]);
				this.skills.add(new FireStorm(chance, radius));
			}
			else if(split[0].equalsIgnoreCase("lightningstorm"))
			{
				if(split.length != 3)
					continue;
				int radius = Integer.valueOf(split[1]);
				double chance = Double.valueOf(split[2]);
				this.skills.add(new LightningStorm(chance, radius));
			}
			else if(split[0].equalsIgnoreCase("shuffle"))
			{
				if(split.length != 3)
					continue;
				int radius = Integer.valueOf(split[1]);
				double chance = Double.valueOf(split[2]);
				this.skills.add(new Shuffle(chance, radius));
			}
			else if(split[0].equalsIgnoreCase("enrage"))
			{
				if(split.length != 3)
					continue;
				int duration = Integer.valueOf(split[1]);
				double chance = Double.valueOf(split[2]);
				this.skills.add(new Enrage(chance, duration));
			}
			else if(split[0].equalsIgnoreCase("detonate"))
			{
				if(split.length != 5)
					continue;
				int radius = Integer.valueOf(split[1]);
				int dmg = Integer.valueOf(split[2]);
				int delay = Integer.valueOf(split[3]);
				double chance = Double.valueOf(split[4]);
				this.skills.add(new Detonate(chance, radius, dmg, delay));
			}
		}
	}
	
	public boolean loadChunk()
	{
		Location loc = entity.getLocation();
		if(loc.getChunk().isLoaded())
			return false;
		return loc.getChunk().load();
	}
	
	public void executeSkills()
	{
		Iterator<Skill> itr = skills.iterator();
		while(itr.hasNext())
		{
			Skill temp = itr.next();
			if(temp instanceof UsableOnce)
			{
				if(((UsableOnce) temp).hasUsed())
					continue;
			}
			if(temp instanceof HealthDepend)
			{
				if(((HealthDepend) temp).getHealthNeedToCast() < entity.getHealth())
					continue;
			}
			if(Math.random() > temp.getChance())
				continue;
			try
			{
				temp.playSkill(entity);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void dropLoot()
	{
		Iterator<String> itr = drops.iterator();
		while(itr.hasNext())
		{
			String s = itr.next();
			String[] parts = s.split(" ");
			if(s.contains(":"))
			{
				String[] splits = parts[0].split(":");
				ItemStack stack = new ItemStack(Material.getMaterial(Integer.valueOf(splits[0])), Integer.valueOf(splits[2]), Short.valueOf(splits[1]));
				if(Math.random() > Double.valueOf(parts[1]))
					continue;
				entity.getWorld().dropItemNaturally(entity.getLocation(), stack);
			}
			else
			{
				ItemStack stack = MobLibrary.plugin.loadItems.getItem(parts[0]);
				if(stack == null)
					stack = Store.getItem(parts[0]);
				if(stack == null)
					continue;
				if(Math.random() > Double.valueOf(parts[1]))
					continue;
				entity.getWorld().dropItemNaturally(entity.getLocation(), stack);
			}
		}
	}
	
	public void addAdds(Mob add)
	{
		adds.add(add);
	}
	
	public void clearAdds()
	{
		Iterator<Mob> itr = adds.iterator();
		while(itr.hasNext())
		{
			Mob add = itr.next();
			if(add == null)
			{
				itr.remove();
				continue;
			}
			add.remove();
			itr.remove();
		}
		adds.clear();
	}
	
	public void remove()
	{
		clearAdds();
		entity.remove();
	}
	
	public void setCustomName(String customName)
	{
		entity.setCustomName(customName);
	}
	
	public boolean isDead()
	{
		return entity.isDead();
	}
	
	public double getHealth()
	{
		return entity.getHealth();
	}
	
	public Player getKiller()
	{
		return entity.getKiller();
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public boolean hasSkills()
	{
		return !skills.isEmpty();
	}
	
	public LivingEntity getEntity()
	{
		return entity;
	}
	
	public String getName()
	{
		return name;
	}
}
