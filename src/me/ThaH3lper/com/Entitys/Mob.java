package me.ThaH3lper.com.Entitys;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.Items.ItemHandler;
import me.ThaH3lper.com.Skills.DeathSkill;
import me.ThaH3lper.com.Skills.DragIn;
import me.ThaH3lper.com.Skills.DropLoot;
import me.ThaH3lper.com.Skills.Ignite;
import me.ThaH3lper.com.Skills.Potion;
import me.ThaH3lper.com.Skills.Skill;
import me.ThaH3lper.com.Skills.SpawnMobs;
import me.ThaH3lper.com.Skills.Teleport;
import me.ThaH3lper.com.Skills.Tnt;
import me.ThaH3lper.com.Skills.Toss;
import me.frodenkvist.utils.MaterialFromInt;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Mob
{
	private LivingEntity entity;
	private List<Skill> skills = new ArrayList<Skill>();
	private List<DeathSkill> deathSkills = new ArrayList<DeathSkill>();
	private int damage;
	private List<String> drops = new ArrayList<String>();
	
	public Mob(LivingEntity le, List<String> skills, int damage, List<String> drop)
	{
		entity = le;
		for(String s : skills)
		{
			if(s.isEmpty())
				continue;
			String[] parts = s.split(" ");
			if(parts[0].equals("teleport"))
			{
				this.skills.add(new Teleport(Double.valueOf(parts[2]), Integer.valueOf(parts[1])));
			}
			else if(parts[0].equals("dragin"))
			{
				this.skills.add(new DragIn(Double.valueOf(parts[2]), Integer.valueOf(parts[1])));
			}
			else if(parts[0].equals("tnt"))
			{
				this.skills.add(new Tnt(Double.valueOf(parts[2]), Integer.valueOf(parts[1])));
			}
			else if(parts[0].equals("spawn"))
			{
				this.skills.add(new SpawnMobs(Double.valueOf(parts[3]), Integer.valueOf(parts[2]), parts[1]));
			}
			else if(parts[0].equals("potion"))
			{
				String[] data = parts[1].split(":");
				PotionEffect potion = new PotionEffect(PotionEffectType.getByName(data[0]), Integer.parseInt(data[1]) * 20, Integer.parseInt(data[2]) - 1);
				this.skills.add(new Potion(Double.valueOf(parts[2]), Integer.valueOf(data[3]), potion));
			}
			else if(parts[0].equals("ignite"))
			{
				this.skills.add(new Ignite(Double.valueOf(parts[3]), Integer.valueOf(parts[1]), Integer.valueOf(parts[2])));
			}
			else if(parts[0].equals("toss"))
			{
				this.skills.add(new Toss(Double.valueOf(parts[3]), Integer.valueOf(parts[1]), Integer.valueOf(parts[2])));
			}
			else if(parts[0].equals("droploot"))
			{
				this.deathSkills.add(new DropLoot(parts[1]));
			}
		}
		
		this.damage = damage;
		drops = drop;
	}
	
	public LivingEntity getEntity()
	{
		return entity;
	}
	
	public void setDamage(int damage)
	{
		this.damage = damage;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void executeSkills()
	{
		for(Skill skill : skills)
		{
			if(Math.random() >= skill.getChance())
				continue;
			try
			{
				skill.playSkill(entity);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void executeDeathSkills()
	{
		for(DeathSkill skill : deathSkills)
		{
			try
			{
				skill.playSkill(entity);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public List<ItemStack> dropLoot()
	{
		List<ItemStack> items = new ArrayList<ItemStack>();
		for(String s : drops)
		{
			String[] parts = s.split(" ");
			if(s.contains(":"))
			{
				String[] splits = parts[0].split(":");
				ItemStack stack = new ItemStack(MaterialFromInt.getMaterialFromInt(Integer.parseInt(splits[0])), Integer.parseInt(splits[2]), (short)Integer.parseInt(splits[1]));
				if(Math.random() <= Double.valueOf(parts[1]))
				{
					entity.getWorld().dropItemNaturally(entity.getLocation(), stack);
					items.add(stack);
				}
			}
			else
			{
				if(ItemHandler.getItem(parts[0]) != null)
				{
					ItemStack stack = ItemHandler.getItem(parts[0]);
					if(Math.random() <= Double.valueOf(parts[1]))
					{
						entity.getWorld().dropItemNaturally(entity.getLocation(), stack);
						items.add(stack);
					}
				}
			}
		}
		return items;
	}
}
