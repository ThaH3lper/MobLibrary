package me.ThaH3lper.com.Skills;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Enrage extends Skill
{
	private int duration;
	
	public Enrage(double chance, int duration)
	{
		super(chance);
		this.duration = duration;
	}
	
	public void playSkill(LivingEntity caster)
	{
		SkillHandler.message(25, caster, ChatColor.RED + "Becomes Enraged!");
		caster.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,duration * 20,2));
		caster.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,duration * 20,2));
	}
}
