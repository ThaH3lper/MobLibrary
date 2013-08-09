package me.ThaH3lper.com.Skills;

import java.util.List;
import java.util.Random;

import me.ThaH3lper.com.Effects.FireWorkEffect;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Teleport extends Skill
{
	private int radius;
	
	public Teleport(double chance, int radius)
	{
		super(chance);
		this.radius = radius;
	}
	
	private FireWorkEffect fplayer = new FireWorkEffect();
	
	public void playSkill(LivingEntity entity) throws IllegalArgumentException, Exception
	{
		List<Player> list = SkillHandler.getPlayers(radius, entity);
		if(!list.isEmpty())
		{
			Random ran = new Random();
			Player p = list.get(ran.nextInt(list.size()));
			fplayer.playFirework(p.getWorld(), p.getLocation(), FireworkEffect.builder().withColor(Color.PURPLE).flicker(true).withFade(Color.FUCHSIA).with(Type.BURST).build());
			fplayer.playFirework(entity.getWorld(), entity.getLocation(), FireworkEffect.builder().withColor(Color.PURPLE).flicker(true).with(Type.BURST).build());
			entity.teleport(p.getLocation());
		}
	}
}
