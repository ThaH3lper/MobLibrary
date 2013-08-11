package me.ThaH3lper.com.Skills;

import java.util.Iterator;
import java.util.List;

import me.ThaH3lper.com.MobLibrary;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import PvpBalance.PvpHandler;

public class Detonate extends Skill
{
	private int damage;
	private int radius;
	private int delay;
	
	public Detonate(double chance, int radius, int damage, int delay)
	{
		super(chance);
		this.radius = radius;
		this.damage = damage;
		this.delay = delay;
	}
	
	public void playSkill(final LivingEntity caster)
	{
		SkillHandler.message(20, caster, ChatColor.RED + "Is Charging Up!");
		Bukkit.getScheduler().scheduleSyncDelayedTask(MobLibrary.plugin, new Runnable()
		{
			@Override
			public void run()
			{
				caster.getWorld().createExplosion(caster.getLocation(), radius);
				List<Player> list = SkillHandler.getPlayers(radius, caster);
				if(list.isEmpty())
					return;
				Iterator<Player> itr = list.iterator();
				while(itr.hasNext())
				{
					Player temp = itr.next();
					PvpHandler.getPvpPlayer(temp).uncheckedDamage(damage);
					temp.damage(0);
				}
			}
		},delay * 20);
	}
}
