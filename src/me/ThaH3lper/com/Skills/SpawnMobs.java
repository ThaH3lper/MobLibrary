package me.ThaH3lper.com.Skills;

import me.ThaH3lper.com.MobLibrary;
import me.ThaH3lper.com.Entitys.MobsHandler;
import me.ThaH3lper.com.Spawner.SpawnerPlace;

import org.bukkit.entity.LivingEntity;

public class SpawnMobs {

	public static void playSkill(LivingEntity mob, int amount, String cmdName) {
		MobLibrary ml = me.ThaH3lper.com.Spawner.SpawnerListener.ml;
		MobsHandler mobHandler = new MobsHandler(ml);
		for(int count = amount; count >= 0; count--){
			LivingEntity add = mobHandler.SpawnAPI(cmdName, mob.getLocation(), 1);
			SpawnerPlace spawner = me.ThaH3lper.com.EventListener.getSpawner(mob);
			spawner.getMobsList().add(add);
		}
		
	}

}
