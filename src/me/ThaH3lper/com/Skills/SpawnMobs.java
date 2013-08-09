package me.ThaH3lper.com.Skills;

import me.ThaH3lper.com.Entitys.Mob;
import me.ThaH3lper.com.Entitys.MobsHandler;

import org.bukkit.entity.LivingEntity;

public class SpawnMobs extends Skill implements UsableOnce, HealthDepend
{
	private String cmdName;
	private int amount;
	private boolean used = false;
	private int healthNeededToCast;
	
	public SpawnMobs(double chance, String cmdName, int amount, int healthNeededToCast)
	{
		super(chance);
		this.cmdName = cmdName;
		this.amount = amount;
		this.healthNeededToCast = healthNeededToCast;
	}
	
	public void playSkill(LivingEntity le)
	{
		for(int i = 0;i < amount; ++i)
		{
			Mob mob = MobsHandler.getMob(le);
			mob.addAdds(MobsHandler.SpawnAPI(cmdName, le.getLocation(), 1));
		}
		setUsed(true);
		/*if(!(usedSkill.contains(mob)))
		{
			SpawnerPlace spawner = me.ThaH3lper.com.EventListener.getSpawner(mob);
			for(int count = amount; count >= 0; count--)
			{
				if(spawner.AlreadySpawnedAdds() == false)
				{
					LivingEntity add = MobsHandler.SpawnAPI(cmdName, mob.getLocation(), 1);
					spawner.getMobsList().add(add);
					spawner.adds.add(add);
				}
			}
			spawner.setAlreadySpawnedAdds(true);
			usedSkill.add(mob);
		}*/
	}
	
	public void setUsed(boolean value)
	{
		used = value;
	}
	
	public boolean hasUsed()
	{
		return used;
	}
	
	@Override
	public int getHealthNeedToCast()
	{
		return healthNeededToCast;
	}
}
