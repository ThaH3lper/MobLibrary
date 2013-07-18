package me.ThaH3lper.com.Skills;

import java.util.List;

import me.ThaH3lper.com.Effects.FireWorkEffect;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Toss {
	public static FireWorkEffect fplayer = new FireWorkEffect();
	public static void playSkill(LivingEntity entity, int radius) throws IllegalArgumentException, Exception
	{
		List<Player> list = SkillHandler.getPlayers(radius, entity);
		if(!list.isEmpty())
		{
			fplayer.playFirework(entity.getWorld(), entity.getLocation(), FireworkEffect.builder().withColor(Color.BLUE).withFade(Color.BLACK).with(Type.BALL_LARGE).build());
			for(Player p : list)
			{
				Vector current = p.getLocation().getDirection();
				current.setX(current.getX() * -1);
				current.setZ(current.getZ() * -1);
				current.setY(13);
				p.setVelocity(current);
			}
		}
	}
}
