package me.ThaH3lper.com.Skills;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.Entitys.MobsHandler;
import me.ThaH3lper.com.Spawner.SpawnerPlace;

import org.bukkit.entity.LivingEntity;

public class SpawnMobs
{
	public static List<LivingEntity> usedSkill = new ArrayList<LivingEntity>();

	public static void playSkill(LivingEntity mob, int amount, String cmdName)
	{
		if(!(usedSkill.contains(mob))){
			SpawnerPlace spawner = me.ThaH3lper.com.EventListener.getSpawner(mob);
			for(int count = amount; count >= 0; count--)
			{
				if(spawner.AlreadySpawnedAdds() == false){
					LivingEntity add = MobsHandler.SpawnAPI(cmdName, mob.getLocation(), 1);
					spawner.getMobsList().add(add);
					spawner.adds.add(add);
				}
			}
			spawner.setAlreadySpawnedAdds(true);
			usedSkill.add(mob);
		}
		
	}

}
