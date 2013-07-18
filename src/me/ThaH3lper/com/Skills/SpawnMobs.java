package me.ThaH3lper.com.Skills;

import me.ThaH3lper.com.Entitys.MobsHandler;
import me.ThaH3lper.com.Spawner.SpawnerPlace;

import org.bukkit.entity.LivingEntity;

public class SpawnMobs extends Skill
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
		if(!used)
		{
			for(int count = amount; count >= 0; count--)
			{
				LivingEntity add = MobsHandler.SpawnAPI(cmdName, mob.getLocation(), 1);
				SpawnerPlace spawner = me.ThaH3lper.com.EventListener.getSpawner(mob);
				spawner.getMobsList().add(add);
			}
			used = true;
		}
	}
}
