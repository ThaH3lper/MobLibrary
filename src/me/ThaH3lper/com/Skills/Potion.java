package me.ThaH3lper.com.Skills;

import java.util.List;

import me.ThaH3lper.com.Effects.FireWorkEffect;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Potion extends Skill
{
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
		FireWorkEffect fplayer = new FireWorkEffect();
		List<Player> list = SkillHandler.getPlayers(r, entity);
		if(!list.isEmpty())
		{
			fplayer.playFirework(entity.getWorld(), entity.getLocation(), FireworkEffect.builder().withColor(getPotionColor(pe)).with(Type.BALL_LARGE).build());
			for(Player p : list)
			{
				p.addPotionEffect(pe);
				fplayer.playFirework(p.getWorld(), p.getLocation(), getPotionFirework(pe));
			}
		}
	}
	public FireworkEffect getPotionFirework(PotionEffect pe)
	{
		if(pe.getType() == PotionEffectType.POISON)
		{
			FireworkEffect potion = FireworkEffect.builder().withColor(Color.GREEN).withColor(Color.LIME).with(Type.BURST).withFlicker().withTrail().build();
			return potion;
		}
		else if(pe.getType() == PotionEffectType.WITHER)
		{
			FireworkEffect potion = FireworkEffect.builder().withColor(Color.GRAY).withColor(Color.BLACK).with(Type.CREEPER).withFade(Color.BLACK).build();
			return potion;
		}
		else if(pe.getType() == PotionEffectType.BLINDNESS)
		{
			FireworkEffect potion = FireworkEffect.builder().withColor(Color.BLACK).withColor(Color.BLACK).with(Type.STAR).withFade(Color.BLACK).flicker(true).trail(true).build();
			return potion;
		}
		else if(pe.getType() == PotionEffectType.CONFUSION)
		{
			FireworkEffect potion = FireworkEffect.builder().withColor(Color.GREEN).withColor(Color.BLACK).with(Type.CREEPER).withFade(Color.LIME).build();
			return potion;
		}
		else if(pe.getType() == PotionEffectType.WEAKNESS)
		{
			FireworkEffect potion = FireworkEffect.builder().withColor(Color.BLACK).withColor(Color.BLUE).with(Type.BURST).flicker(true).trail(true).withFade(Color.NAVY).build();
			return potion;
		}
		else if(pe.getType() == PotionEffectType.HARM)
		{
			FireworkEffect potion = FireworkEffect.builder().withColor(Color.RED).withColor(Color.BLUE).with(Type.STAR).withFade(Color.LIME).build();
			return potion;
		}
		else{
			FireworkEffect potion = FireworkEffect.builder().withColor(Color.GRAY).with(Type.BURST).build();
			return potion;
		}
	}
	public Color getPotionColor(PotionEffect pe)
	{
		if(pe.getType() == PotionEffectType.POISON)
		{
			Color potion = Color.GREEN;
			return potion;
		}
		else if(pe.getType() == PotionEffectType.WITHER)
		{
			Color potion = Color.BLACK;
			return potion;
		}
		else if(pe.getType() == PotionEffectType.BLINDNESS)
		{
			Color potion = Color.GRAY;
			return potion;
		}
		else if(pe.getType() == PotionEffectType.CONFUSION)
		{
			Color potion = Color.LIME;
			return potion;
		}
		else if(pe.getType() == PotionEffectType.WEAKNESS)
		{
			Color potion = Color.NAVY;
			return potion;
		}
		else if(pe.getType() == PotionEffectType.HARM)
		{
			Color potion = Color.RED;
			return potion;
		}
		else
		{
			Color potion = Color.GRAY;
			return potion;
		}
	}
}
