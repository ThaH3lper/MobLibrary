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
	public static void playSkill(LivingEntity entity, int radius, int power) throws IllegalArgumentException, Exception
	{
		List<Player> list = SkillHandler.getPlayers(radius, entity);
		fplayer.playFirework(entity.getWorld(), entity.getLocation(), FireworkEffect.builder().withColor(Color.BLUE).withFade(Color.BLACK).with(Type.BALL_LARGE).build());
		if(!list.isEmpty())
		{
			for(Player p : list)
			{
                float hForce = 20 / 10.0F;
                float vForce = 15 / 10.0F;
	            Vector v = p.getLocation().toVector().subtract(entity.getLocation().toVector());
                v.setY(5);
                v.normalize();
                v.multiply(hForce*power);
                v.setY(vForce*power);
	            p.setVelocity(v);

			}
		}
	}
}
