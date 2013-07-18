package me.ThaH3lper.com.Skills;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillHandler
{
	public static List<Player> getPlayers(int radius, LivingEntity mob)
	{
		List<Player> list = new ArrayList<Player>();
		List<Entity> near = mob.getNearbyEntities(radius, radius, radius);
		for(Entity check:near){
			if(check instanceof Player){
				{
					list.add((Player) check);
				}
			}
		}
		return list;
	}
	
	/*public static void executeSkills(List<String> list, LivingEntity mob) throws IllegalArgumentException, Exception
	{
		for(String s: list)
		{
			String[] parts = s.split(" ");
			if(parts[0].equals("teleport"))
			{
				Double chance = Double.parseDouble(parts[2]);
				if(chance >= r.nextDouble())
				{
					int radius = Integer.parseInt(parts[1]);
					Teleport.playSkill(mob, radius);
				}
			}
			else if(parts[0].equals("dragin"))
			{
				Double chance = Double.parseDouble(parts[2]);
				if(chance >= r.nextDouble())
				{
					int radius = Integer.parseInt(parts[1]);
					DragIn.playSkill(mob, radius);
				}
			}
			else if(parts[0].equals("tnt"))
			{
				Double chance = Double.parseDouble(parts[2]);
				if(chance >= r.nextDouble())
				{
					int radius = Integer.parseInt(parts[1]);
					Tnt.playSkill(mob , radius);
				}
			}
			else if(parts[0].equals("spawn"))
			{
				String cmdName = parts[1];
				Double life = Double.parseDouble(parts[2]);
				if(mob.getHealth() < life){
						int amount = Integer.parseInt(parts[3]);
						SpawnMobs.playSkill(mob , amount, cmdName);
				}
			}
			else if(parts[0].equals("potion"))
			{
				Double chance = Double.parseDouble(parts[2]);
				if(chance >= r.nextDouble())
				{
					String[] data = parts[1].split(":");
					int radius = Integer.parseInt(data[3]);
					PotionEffect potion = new PotionEffect(PotionEffectType.getByName(data[0]), Integer.parseInt(data[1]) * 20, Integer.parseInt(data[2]) - 1);
					Potion.playSkill(mob , radius, potion);
				}
			}
			else if(parts[0].equals("ignite"))
			{
				int duration = Integer.parseInt(parts[2]);
				Double chance = Double.parseDouble(parts[3]);
				if(chance >= r.nextDouble())
				{
					int radius = Integer.parseInt(parts[1]);
					Ignite.playSkill(mob , radius, duration);
				}
			}
			else if(parts[0].equals("toss"))
			{
				Double chance = Double.parseDouble(parts[3]);
				if(chance >= r.nextDouble())
				{
					int power = Integer.parseInt(parts[2]);
					int radius = Integer.parseInt(parts[1]);
					Toss.playSkill(mob , radius, power);
				}
			}
		}
	}
	
	public static void executeSkillsOnDeath(List<String> list, EntityDeathEvent event) throws IllegalArgumentException, Exception
	{
		for(String s: list)
		{
			String[] parts = s.split(" ");
			if(parts[0].equals("droploot"))
			{
				Double chance = Double.parseDouble(parts[2]);
				if(chance >= r.nextDouble())
				{
					String cmdName = parts[1];
					DropLoot.playSkill(cmdName, event);
				}
			}
		}
	}*/
}
