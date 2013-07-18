package me.ThaH3lper.com.Skills;

import java.util.List;

import me.ThaH3lper.com.Effects.FireWorkEffect;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class DragIn
{
	private static FireWorkEffect fplayer = new FireWorkEffect();
	
	public static void playSkill(LivingEntity entity, int r) throws IllegalArgumentException, Exception
	{
		List<Player> list = SkillHandler.getPlayers(r, entity);
		if(!list.isEmpty())
		{
			for(Player p : list)
			{
				fplayer.playFirework(p.getWorld(), p.getLocation(), FireworkEffect.builder().withColor(Color.BLUE).withFade(Color.FUCHSIA).with(Type.BURST).build());
				p.teleport(entity.getLocation());
			}
			fplayer.playFirework(entity.getWorld(), entity.getLocation(), FireworkEffect.builder().withColor(Color.BLUE).withFade(Color.FUCHSIA).with(Type.BURST).build());
		}
	}
}
