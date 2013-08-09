package me.ThaH3lper.com.Skills;

import java.util.List;

import me.ThaH3lper.com.Effects.FireWorkEffect;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class DragIn extends Skill
{
	private int radius;
	
	public DragIn(double chance, int radius)
	{
		super(chance);
		this.radius = radius;
	}
	
	private FireWorkEffect fplayer = new FireWorkEffect();
	
	public void playSkill(LivingEntity entity) throws IllegalArgumentException, Exception
	{
		List<Player> list = SkillHandler.getPlayers(radius, entity);
		if(list.isEmpty())
			return;

		for(Player p : list)
		{
			fplayer.playFirework(p.getWorld(), p.getLocation(), FireworkEffect.builder().withColor(Color.BLUE).withFade(Color.FUCHSIA).with(Type.BURST).build());
			p.teleport(entity.getLocation());
		}
		fplayer.playFirework(entity.getWorld(), entity.getLocation(), FireworkEffect.builder().withColor(Color.BLUE).withFade(Color.FUCHSIA).with(Type.BURST).build());
	}
}
