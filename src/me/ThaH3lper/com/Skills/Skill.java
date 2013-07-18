package me.ThaH3lper.com.Skills;

import org.bukkit.entity.LivingEntity;

public abstract class Skill
{
	protected double chance;
	
	public Skill(double chance)
	{
		this.chance = chance;
	}
	
	public double getChance()
	{
		return chance;
	}
	
	public abstract void playSkill(LivingEntity le) throws IllegalArgumentException, Exception;
}
