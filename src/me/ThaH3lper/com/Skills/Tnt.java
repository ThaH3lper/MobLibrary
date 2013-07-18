package me.ThaH3lper.com.Skills;

import java.util.List;

import me.ThaH3lper.com.Effects.FireWorkEffect;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Tnt extends Skill
{
	private FireWorkEffect fplayer = new FireWorkEffect();
	private int radius;
	
	public Tnt(double chance, int radius)
	{
		super(chance);
		this.radius = radius;
	}
	
	public void playSkill(LivingEntity entity) throws IllegalArgumentException, Exception
	{
		List<Player> list = SkillHandler.getPlayers(radius, entity);
		if(!list.isEmpty())
		{
			fplayer.playFirework(entity.getWorld(), entity.getLocation(), FireworkEffect.builder().withColor(Color.RED).withFade(Color.WHITE).with(Type.BALL_LARGE).build());
			for(Player p : list)
			{
				p.getWorld().spawnEntity(p.getLocation(), EntityType.PRIMED_TNT);
				fplayer.playFirework(p.getWorld(), p.getLocation(), FireworkEffect.builder().withColor(Color.RED).withFade(Color.WHITE).with(Type.STAR).build());
			}
		}
	}
}
