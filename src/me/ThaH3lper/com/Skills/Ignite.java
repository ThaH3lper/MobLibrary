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
		private FireWorkEffect fplayer = new FireWorkEffect();
		private int r;
		private int dur;
		
		public Ignite(double chance, int r, int dur)
		{
			super(chance);
			this.r = r;
			this.dur = dur;
		}
		
		public void playSkill(LivingEntity entity) throws IllegalArgumentException, Exception
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