package me.ThaH3lper.com.Skills;

import java.util.List;

import me.ThaH3lper.com.Effects.FireWorkEffect;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Ignite extends Skill
{
	private int radius;
	private int duration;
	
	public Ignite(double chance, int radius, int duration)
	{
		super(chance);
		this.radius = radius;
		this.duration = duration;
	}
	
	public FireWorkEffect fplayer = new FireWorkEffect();
	public void playSkill(LivingEntity entity) throws IllegalArgumentException, Exception
	{
		List<Player> list = SkillHandler.getPlayers(radius, entity);
		if(list.isEmpty())
			return;
		fplayer.playFirework(entity.getWorld(), entity.getLocation(), FireworkEffect.builder().withColor(Color.YELLOW).with(Type.BURST).build());
		for(Player p : list)
		{
			p.setFireTicks(duration);
			fplayer.playFirework(p.getWorld(), p.getLocation(), FireworkEffect.builder().withColor(Color.ORANGE).withColor(Color.RED).with(Type.BURST).flicker(true).build());
		}
	}
}