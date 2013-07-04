package me.ThaH3lper.com.Skills;

import java.util.List;
import java.util.Random;

import me.ThaH3lper.com.Effects.FireWorkEffect;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

public class Potion {
	public static FireWorkEffect fplayer = new FireWorkEffect();
	public static void playSkill(LivingEntity entity, int r, PotionEffect pe) throws IllegalArgumentException, Exception
	{
		List<Player> list = SkillHandler.getPlayers(r, entity);
		if(!list.isEmpty())
		{
			fplayer.playFirework(entity.getWorld(), entity.getLocation(), FireworkEffect.builder().withColor(Color.ORANGE).withColor(Color.LIME).with(Type.BALL).build());
			fplayer.playFirework(entity.getWorld(), entity.getLocation(), FireworkEffect.builder().withColor(Color.BLACK).with(Type.BALL).build());
			for(Player p : list)
			{
				p.addPotionEffect(pe);
				fplayer.playFirework(p.getWorld(), p.getLocation(), FireworkEffect.builder().withColor(Color.ORANGE).withColor(Color.LIME).with(Type.BURST).build());
			}
		}
	}
}
