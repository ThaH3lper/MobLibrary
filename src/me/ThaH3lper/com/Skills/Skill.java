package me.ThaH3lper.com.Skills;

import org.bukkit.entity.LivingEntity;

public abstract class Skill
{
	private double chance;
	
	protected Skill(double chance)
	{
		this.chance = chance;
	}
	
	public double getChance()
	{
		return chance;
	}
	
	public abstract void playSkill(LivingEntity entity) throws IllegalArgumentException, Exception;
}
