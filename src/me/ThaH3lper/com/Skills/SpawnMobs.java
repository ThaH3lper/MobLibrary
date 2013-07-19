package me.ThaH3lper.com.Skills;

import me.ThaH3lper.com.Entitys.MobsHandler;
import me.ThaH3lper.com.Spawner.SpawnerHandler;
import me.ThaH3lper.com.Spawner.SpawnerPlace;

import org.bukkit.entity.LivingEntity;

public class SpawnMobs extends Skill implements UsableOnce
{
	private int amount;
	private String cmdName;
	private boolean used;
	
	public SpawnMobs(double chance, int amount, String cmdName)
	{
		super(chance);
		this.amount = amount;
		this.cmdName = cmdName;
		used = false;
	}
	
	public void playSkill(LivingEntity mob)
	{
		if(isUsed())
			return;
		for(int i=0; i < amount; ++i)
		{
			LivingEntity le = MobsHandler.SpawnAPI(cmdName, mob.getLocation(), 1);
			SpawnerPlace spawner = SpawnerHandler.getSpawner(mob);
			spawner.getMobsList().add(le);
		}
		setUsed(true);
	}

	@Override
	public boolean isUsed()
	{
		return used;
	}

	@Override
	public void setUsed(boolean value)
	{
		used = value;
	}

}
