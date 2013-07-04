package me.ThaH3lper.com.Skills;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SkillHandler {
	private static Random r = new Random();
	public static List<Player> getPlayers(int rad, LivingEntity l)
	{
		List<Player> list = new ArrayList<Player>();
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(l.getLocation().distance(p.getLocation()) <= rad)
			{
				list.add(p);
			}
		}
		return list;
	}
	
	public static void executeSkills(List<String> list, LivingEntity l) throws IllegalArgumentException, Exception
	{
		for(String s: list)
		{
			String[] parts = s.split(" ");
			if(parts[0].equals("teleport"))
			{
				Double chance = Double.parseDouble(parts[2]);
				if(chance >= r.nextDouble())
				{
					int radious = Integer.parseInt(parts[1]);
					Teleport.playSkill(l, radious);
				}
			}
			if(parts[0].equals("dragin"))
			{
				Double chance = Double.parseDouble(parts[2]);
				if(chance >= r.nextDouble())
				{
					int radious = Integer.parseInt(parts[1]);
					DragIn.playSkill(l, radious);
				}
			}
			if(parts[0].equals("tnt"))
			{
				Double chance = Double.parseDouble(parts[2]);
				if(chance >= r.nextDouble())
				{
					int radious = Integer.parseInt(parts[1]);
					Tnt.playSkill(l, radious);
				}
			}
			if(parts[0].equals("potion"))
			{
				Double chance = Double.parseDouble(parts[2]);
				if(chance >= r.nextDouble())
				{
					String[] data = parts[1].split(":");
					int radious = Integer.parseInt(data[3]);
					PotionEffect pe = new PotionEffect(PotionEffectType.getByName(data[0]), Integer.parseInt(data[1]) * 20, Integer.parseInt(data[2]) - 1);
					Potion.playSkill(l, radious, pe);
				}
			}
		}
	}

}
