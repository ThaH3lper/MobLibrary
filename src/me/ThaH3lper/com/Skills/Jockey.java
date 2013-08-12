package me.ThaH3lper.com.Skills;

import java.util.List;
import java.util.Random;

import me.ThaH3lper.com.MobLibrary;
import me.ThaH3lper.com.Effects.FireWorkEffect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import PvpBalance.PvpHandler;

public class Jockey extends Skill
{
	private int radius;
	private int damage;
	
	public Jockey(double chance, int radius, int damage)
	{
		super(chance);
		this.radius = radius;
		this.damage = damage;
	}
	
	public void playSkill(LivingEntity caster)
	{
		if(caster.isInsideVehicle())
			return;
		FireWorkEffect fplayer = new FireWorkEffect();
		FireworkEffect fe = FireworkEffect.builder().withColor(Color.AQUA).with(Type.BURST).build();
		List<Player> list = SkillHandler.getPlayers(radius, caster);
		if(list.isEmpty())
			return;
		final Player target = list.get(new Random().nextInt(list.size()));
		caster.teleport(target);
		target.setPassenger(caster);
		try
		{
			fplayer.playFirework(target.getWorld(), target.getLocation(), fe);
			fplayer.playFirework(caster.getWorld(), caster.getLocation(), fe);
		}
		catch(Exception e)
		{
		}
		SkillHandler.message(30, caster, ChatColor.RED + "Grabs Onto " + ChatColor.AQUA + target.getName());
		PvpHandler.getPvpPlayer(target).uncheckedDamage(damage);
		target.damage(0D);
		Bukkit.getScheduler().scheduleSyncDelayedTask(MobLibrary.plugin, new Runnable()
		{
			@Override
			public void run()
			{
				PvpHandler.getPvpPlayer(target).uncheckedDamage(damage);
				target.damage(0D);
				Bukkit.getScheduler().scheduleSyncDelayedTask(MobLibrary.plugin, new Runnable()
				{
					@Override
					public void run()
					{
						PvpHandler.getPvpPlayer(target).uncheckedDamage(damage);
						target.damage(0D);
						Bukkit.getScheduler().scheduleSyncDelayedTask(MobLibrary.plugin, new Runnable()
						{
							@Override
							public void run()
							{
								PvpHandler.getPvpPlayer(target).uncheckedDamage(damage);
								target.damage(0D);
								Bukkit.getScheduler().scheduleSyncDelayedTask(MobLibrary.plugin, new Runnable()
								{
									@Override
									public void run()
									{
										target.eject();
									}
								}, 1*20);
							}
						}, 1*20);
					}
				}, 1*20);
			}
		}, 1*20);
	}
}
