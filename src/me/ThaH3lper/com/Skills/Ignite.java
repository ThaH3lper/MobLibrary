package me.ThaH3lper.com.Skills;

import java.util.List;

import me.ThaH3lper.com.Effects.FireWorkEffect;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Ignite {
		public static FireWorkEffect fplayer = new FireWorkEffect();
		public static void playSkill(LivingEntity entity, int r, int dur) throws IllegalArgumentException, Exception
		{
			List<Player> list = SkillHandler.getPlayers(r, entity);
			if(!list.isEmpty())
			{
				fplayer.playFirework(entity.getWorld(), entity.getLocation(), FireworkEffect.builder().withColor(Color.YELLOW).with(Type.BURST).build());
				for(Player p : list)
				{
					p.setFireTicks(dur);
					fplayer.playFirework(p.getWorld(), p.getLocation(), FireworkEffect.builder().withColor(Color.ORANGE).withColor(Color.RED).with(Type.BURST).build());

				}
			}
		}
	}