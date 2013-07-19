package me.ThaH3lper.com.Skills;

import org.bukkit.entity.LivingEntity;

public abstract class DeathSkill
{
	public abstract void playSkill(LivingEntity le) throws IllegalArgumentException, Exception;
}
