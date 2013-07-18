package me.ThaH3lper.com.Skills;

import java.util.List;

import me.ThaH3lper.com.Effects.FireWorkEffect;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Potion extends Skill
{
	private FireWorkEffect fplayer = new FireWorkEffect();
	private int r;
	private PotionEffect pe;
	
	public Potion(double chance, int r, PotionEffect pe)
	{
		super(chance);
		this.r = r;
		this.pe = pe;
	}
	
	public void playSkill(LivingEntity entity) throws IllegalArgumentException, Exception
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
