package me.ThaH3lper.com.Skills;

import me.ThaH3lper.com.MobLibrary;
import me.ThaH3lper.com.Effects.FireWorkEffect;
import me.ThaH3lper.com.Entitys.Mob;
import me.ThaH3lper.com.Entitys.MobsHandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.LivingEntity;

public class Parry extends Skill
{
	private FireWorkEffect fplayer = new FireWorkEffect();
	
	public Parry(double chance)
	{
		super(chance);
	}
	
	public void playSkill(final LivingEntity caster)
	{
		final Mob mob = MobsHandler.getMob(caster);
		
		try
		{
			fplayer.playFirework(caster.getWorld(), caster.getLocation(), FireworkEffect.builder().withColor(Color.NAVY).withFade(Color.FUCHSIA).with(Type.BURST).build());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		mob.setParrying(true);
		SkillHandler.message(30, caster, ChatColor.RED + "Parries");
		Bukkit.getScheduler().scheduleSyncDelayedTask(MobLibrary.plugin, new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					fplayer.playFirework(caster.getWorld(), caster.getLocation(), FireworkEffect.builder().withColor(Color.RED).withFade(Color.FUCHSIA).with(Type.BURST).build());
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				mob.setParrying(false);
				SkillHandler.message(30, caster, ChatColor.RED + "Lets Down His Guard");
			}
		},5*20);
	}
}
